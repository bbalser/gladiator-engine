package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class CharacterTests extends FunSuite with ShouldMatchers {

  test("A Character can get and set a name") {
    val character = Character(name = "Bob the Wombat")
    character.name should be ("Bob the Wombat")
  }

  test("A Character can get and set an alignment") {
    val character = Character(name = "John", alignment = Character.Alignment.Good)
    character.alignment should be (Character.Alignment.Good)
  }

  test("A Character's armorClass should default to 10") {
    val character = Character(name = "Bobby")
    character.armorClass should be (10)
  }

  test("A character's hit points should default to 5") {
    val character = Character(name = "Rumplestiltskin")
    character.currentHitPoints should be (5)
    character.maxHitPoints should be (5)
  }

  test("A character can attack another character and a hit will remove not hitpoints from defender if not a hit") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")

    val (newCain, newAbel, attack) = cain.attack(abel, 9)
    newAbel.currentHitPoints should be (5)
  }

  test("A character can attack another character and a hit will remove hitpoints from defender") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")

    val (newCain, newAbel, attack) = cain.attack(abel, 10)
    newAbel.currentHitPoints should be (4)
  }

  test("When an attacker successfully attacks a defender and roll's a natural 20, double damage is dealt") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")
    val (newCain, newAbel, attack) = cain.attack(abel, 20)
    newAbel.currentHitPoints should be (3)
  }

  test("When character hitPoints are zero, character is dead") {
    val cain = Character(name = "Cain", hitPoints = Some(0))
    cain.isAlive should be (false)
  }

  test("When character hitPoints are greater than zero, character is alive") {
    val cain = Character(name = "Cain", hitPoints = Some(1))
    cain.isAlive should be (true)
  }

  test("A Character has abilities") {
    val cain = Character(name = "Cain", abilities = Map(Ability.Strength -> Ability(8), Ability.Dexterity -> Ability(12)))
    cain.ability(Ability.Strength) should be (Ability(8))
  }

  test("A Character has default abilities of 10") {
    val cain = Character(name = "Cain")
    cain.ability(Ability.Strength) should be (Ability())
  }

  test("A Character should include constitution modifier into maxHitPoints and defaultHitPoints") {
    val cain = Character(name = "Cain", abilities = Map(Ability.Constitution -> Ability(12)))
    cain.currentHitPoints should be (6)
    cain.maxHitPoints should be (6)
  }

  test("A Character maxHitPoints should have a minimum of 1") {
    val cain = Character(name = "cain", abilities = Map(Ability.Constitution -> Ability(1)))
    cain.maxHitPoints should be (1)
  }

  test("A Character has experiencePoints") {
    val cain = Character(name = "cain", experiencePoints = 111)
    cain.experiencePoints should be (111)
  }

  test("A Character has default experience points of 0") {
    val cain = Character(name = "cain")
    cain.experiencePoints should be (0)
  }

  test("A Character can have experience points applied to the character") {
    val cain = Character(name = "cain", experiencePoints = 150)
    val newCain = cain.addExperiencePoints(1000)
    newCain.experiencePoints should be (1150)
  }

  test("A Character's default level is 1") {
    val cain = Character(name = "cain")
    cain.level should be (1)
  }

  test("Test all character levels") {
    val levels = List( (2, 1000), (3, 3000), (4, 6000), (5, 10000), (6, 15000), (7, 21000), (8, 28000), (9, 36000), (10, 45000),
      (11, 55000), (12, 66000), (13, 78000), (14, 91000), (15, 105000), (16, 120000), (17, 136000), (18, 153000), (19, 171000), (20, 190000))
    levels.foreach { case (level, xp) =>
      val cain = Character(name = "cain")
      val newCain = cain.addExperiencePoints(xp - cain.experiencePoints)
      newCain.level should be (level)
    }
  }

  test("A Character can go no hire than level 20") {
    val cain = Character(name = "cain", experiencePoints = 300000)
    cain.level should be (20)
  }

  test("A Character's max hit points increase by 5 plus constitution modifier per level") {
    val cain = Character(name = "cain", abilities = Map(Ability.Constitution -> Ability(14)))
    cain.maxHitPoints should be (7)
    val newCain = cain.addExperiencePoints(10000)
    newCain.maxHitPoints should be (35)
  }

  test("A Character's experience points should be able to be set") {
    val cain = Character(name = "cain", experiencePoints = Character.Levels(9))
    cain.experiencePoints should be (Character.Levels(9))
    val newCain = cain.setExperiencePoints(Character.Levels(2))
    newCain.experiencePoints should be (Character.Levels(2))

  }

}
