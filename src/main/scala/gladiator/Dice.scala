package gladiator

import scala.util.Random


object Dice {
  def d20() = Random.nextInt(20) + 1
}
