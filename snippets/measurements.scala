import java.util.Date
import scala.util.Random

case class Measurement(timestamp: Long, dimension: String, value: Double)

object Measurements {
  /**
   * Generates a number of random measurements
   * @param amount  The amount of measurements to generate
   */
  def generate(amount: Int): Seq[Measurement] = {
    val currentTimestamp = new Date().getTime
    val rnd = new Random(currentTimestamp)

    for {
      i <- List.range(0, amount)
    } yield Measurement(currentTimestamp + i, "temperature", 20 * rnd.nextDouble())
  }
}
