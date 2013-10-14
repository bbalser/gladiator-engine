package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import gladiator.Ability.Strength

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

  test("A character can attack another character and a hit will remove not hitpoints from defender if not a hit") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")

    val (newCain, newAbel, attack) = cain.attack(abel, 9)
    newAbel.hitPoints should be (5)
  }

  test("A character can attack another character and a hit will remove hitpoints from defender") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")

    val (newCain, newAbel, attack) = cain.attack(abel, 10)
    newAbel.hitPoints should be (4)
  }

  test("When an attacker successfully attacks a defender and roll's a natural 20, double damage is dealt") {
    val cain = new Character(name = "Cain")
    val abel = new Character(name = "Abel")
    val (newCain, newAbel, attack) = cain.attack(abel, 20)
    newAbel.hitPoints should be (3)
  }

  test("When character hitPoints are zero, character is dead") {
    val cain = new Character(name = "Cain", hitPoints = 0)
    cain.isAlive should be (false)
  }

  test("When character hitPoints are greater than zero, character is alive") {
    val cain = new Character(name = "Cain", hitPoints = 1)
    cain.isAlive should be (true)
  }

  test("A Character has abilities") {
    val cain = new Character(name = "Cain", abilities = Map(Ability.Strength -> Ability(8), Ability.Dexterity -> Ability(12)))
    cain.ability(Ability.Strength) should be (Ability(8))
  }

  test("A Character has default abilities of 10") {
    val cain = new Character(name = "Cain")
    cain.ability(Ability.Strength) should be (Ability())
  }

}
