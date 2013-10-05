package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class DieTests extends FunSuite with ShouldMatchers {

  test("Die.d20 should return a random number") {
    val random = new RandomFacade {
      override def next = 10
    }

    Die.d20(random) should be (10)
  }

}
