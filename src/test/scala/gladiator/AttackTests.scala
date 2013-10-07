package gladiator

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

class AttackTests extends FunSuite with ShouldMatchers {

  test("isHit should return true if roll meets defender's armorClass") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")
    val roll = 10

    Attack(cain,abel,roll).isHit should be (true)
  }

}
