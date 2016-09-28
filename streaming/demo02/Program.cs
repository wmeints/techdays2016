using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.ServiceBus.Messaging;
using Serilog;

namespace SamplePublisher
{
    class Program
    {
        static void Main(string[] args)
        {
            Log.Logger = new LoggerConfiguration().WriteTo.Console().CreateLogger();
            
            

            Log.Information("Connecting to Azure Event Hub");

            var eventhub = EventHubClient.CreateFromConnectionString(ConfigurationManager.AppSettings["ServiceBusConnection"]);
            var publisher = new EventPublisher(eventhub, () => new EventData("Sample event " + DateTime.Now.Ticks, new DataContractSerializer(typeof(string))));
            
            Console.CancelKeyPress += (s, e) => publisher.Stop(); 
            publisher.WaitForExit();
        }


        private class EventPublisher
        {
            private readonly EventHubSender _sender;
            private readonly Func<EventData> _producerFunc;
            private readonly CancellationTokenSource _cancellationTokenSource;

            public EventPublisher(EventHubClient client, Func<EventData> producerFunc)
            {
                _producerFunc = producerFunc;
                _cancellationTokenSource = new CancellationTokenSource();

                _sender = client.CreateSender("SamplePublisher");

                new Thread(GenerateMessages).Start();
            }

            private void GenerateMessages()
            {
                while (!_cancellationTokenSource.IsCancellationRequested)
                {
                    Log.Information("Sending event to Azure Event Hub");
                    _sender.Send(_producerFunc());

                    // Wait for one second or until the thread is cancelled.
                    WaitHandle.WaitAll(new[] {_cancellationTokenSource.Token.WaitHandle}, TimeSpan.FromSeconds(1));
                }

                _sender.Close();
            }

            public void Stop()
            {
                Log.Information("Stopping event publisher");
                _cancellationTokenSource.Cancel(false);
            }

            public void WaitForExit()
            {
                _cancellationTokenSource.Token.WaitHandle.WaitOne();
            }
        }
    }


}
