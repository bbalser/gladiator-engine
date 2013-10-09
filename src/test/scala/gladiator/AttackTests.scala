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

  test("isHit should return true if roll beats defender's armorClass") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")
    val roll = 11

    Attack(cain,abel,roll).isHit should be (true)
  }

  test("isHit should return false if roll less than defender's armorClass") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")
    val roll = 9

    Attack(cain,abel,roll).isHit should be (false)
  }

  test("damage should return 1 hit point") {
    val attack = Attack(new Character(name = "one"), new Character(name = "two"), 10)
    attack.damage should be (1)
  }


}
