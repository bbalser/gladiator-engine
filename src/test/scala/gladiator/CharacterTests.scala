package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class CharacterTests extends FunSuite with ShouldMatchers {

  test("A Character can get and set a name") {
    val character = new Character(name = "Bob the Wombat")
    character.name should be ("Bob the Wombat")
  }

  test("A Character can get and set an alignment") {
    val character = new Character(name = "John", alignment = Character.Alignment.Good)
    character.alignment should be (Character.Alignment.Good)
  }

  test("A Character's armorClass should default to 10") {
    val character = new Character(name = "Bobby")
    character.armorClass should be (10)
  }

  test("A character's hit points should default to 5") {
    val character = new Character(name = "Rumplestiltskin")
    character.hitPoints should be (5)
  }

  test("A character can attack another character") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")

    val attack = cain.attack(abel)
    attack.attacker should be (cain)
    attack.defender should be (abel)
  }

}
