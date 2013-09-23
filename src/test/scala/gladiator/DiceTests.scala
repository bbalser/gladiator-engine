package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class DiceTests extends FunSuite with ShouldMatchers {

  test("Dice.20 should create random number between 1 and 20") {
    (1 to 10000).foreach{ x =>
      val result = Dice.d20()
      result should be <= (20)
      result should be >= (1)
    }
  }

}
