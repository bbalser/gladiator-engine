package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import scala.reflect.ClassTag

class AbilityTests extends FunSuite with ShouldMatchers {

  test("default score of any ability should be 10") {
    val ability = Ability()
    ability.score should be (10)
  }

  test("ability lower than 1 should throw an exception") {
    val thrown = intercept[IllegalArgumentException] {
      Ability(0)
    }
    thrown.getMessage should be ("requirement failed: Ability must be 1 or higher")
  }

  test("ability can be 1") {
    val ability = Ability(1)
    ability.score should be (1)
  }

  test("ability score can return modifier") {
    val modifiers = List( (1, -5), (2, -4), (3, -4), (4, -3), (5, -3), (6, -2), (7, -2), (8, -1), (9, -1), (10, 0),
      (11, 0), (12, 1), (13, 1), (14, 2), (15, 2), (16, 3), (17, 3), (18, 4), (19, 4), (20, 5))

    modifiers.foreach { case (s, m) =>
      Ability(s).modifier should be (m)
    }
  }

  test("abilities should be additive") {
    val result = Ability(5) + Ability(2)
    result should be (Ability(7))
  }

}
