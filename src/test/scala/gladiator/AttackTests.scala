package gladiator

import org.mockito.Mockito._

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class AttackTests extends FunSuite with ShouldMatchers with MockitoSugar {

  test("isHit should return true if roll meets defender's armorClass") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")
    val roll = 10

    Attack(cain,abel,roll).isHit should be (true)
  }

  test("isHit should return true if roll beats defender's armorClass") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")
    val roll = 11

    Attack(cain,abel,roll).isHit should be (true)
  }

  test("isHit should return false if roll less than defender's armorClass") {
    val cain = Character(name = "Cain")
    val abel = Character(name = "Abel")
    val roll = 9

    Attack(cain,abel,roll).isHit should be (false)
  }

  test("damage should return 1 hit point") {
    val attack = Attack(Character(name = "one"), Character(name = "two"), 10)
    attack.damage should be (1)
  }

  test("strength should be added to attack roll and damage dealt") {
    val attack = Attack(Character(name = "one", abilities = Map(Ability.Strength -> Ability(12))), Character(name = "two"), 9)
    attack.isHit should be (true)
    attack.damage should be (2)
  }

  test("strength should be doubled and added to attack roll and damage dealt when critical hit") {
    val attack = Attack(Character(name = "one", abilities = Map(Ability.Strength -> Ability(12))), Character(name = "two"), 20)
    attack.isHit should be (true)
    attack.damage should be (4)
  }

  test("natural 20 always hits regardless of armor class") {
    val defenderMock = mock[Character]
    doReturn(25).when(defenderMock).armorClass
    val attack = Attack(Character(name = "one"), defenderMock, 20)
    attack.isHit should be (true)
  }

  test("minimum damage is always 1") {
    val defenderMock = mock[Character]
    doReturn(1).when(defenderMock).armorClass
    val attack = Attack(Character(name = "one", abilities = Map(Ability.Strength -> Ability(1))), defenderMock, 10)
    attack.damage should be (1)
  }

  test("minimum damage is always 1 even for critical") {
    val defenderMock = mock[Character]
    doReturn(1).when(defenderMock).armorClass
    val attack = Attack(Character(name = "one", abilities = Map(Ability.Strength -> Ability(1))), defenderMock, 20)
    attack.damage should be (1)
  }

  test("dexterity modifier should be added to defender's armor class") {
    val attacker = Character(name = "one")
    val defender = Character(name = "two", abilities = Map(Ability.Dexterity -> Ability(12)))
    val attack = Attack(attacker, defender, 10)
    attack.isHit should be (false)
  }

  test("1 is added to attack roll for every even level of attacking character") {
    val cain = Character(name = "cain", experiencePoints = Character.Levels(2))
    val abel = Character(name = "abel")
    val attack = Attack(cain, abel, 9)
    attack.attackRoll should be (10)

    val newCain = cain.setExperiencePoints(Character.Levels(5))
    val attack2 = Attack(newCain, abel, 8)
    attack2.attackRoll should be (10)
  }



}
