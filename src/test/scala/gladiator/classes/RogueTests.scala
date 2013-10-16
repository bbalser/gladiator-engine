package gladiator.classes

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import gladiator.{Ability, Attack, Character}

class RogueTests extends FunSuite with ShouldMatchers {

  test("rogues do triple damage on critical hits") {
    val attacker = Character(name = "cain", characterClass = Rogue)
    val defender = Character(name = "abel")
    val attack = Attack(attacker, defender, 20)
    attack.damage should be (3)
  }

  test("rogues ignore dexterity modifier bonus to armor class when attacking") {
    val attacker = Character(name = "cain", characterClass = Rogue)
    val defender = Character(name = "abel", abilities = Map(Ability.Dexterity -> Ability(12)))
    val attack = Attack(attacker, defender, 10)
    attack.armorClass should be (10)
  }

  test("rogues ignore dexterity but only if positive") {
    val attacker = Character(name = "cain", characterClass = Rogue)
    val defender = Character(name = "abel", abilities = Map(Ability.Dexterity -> Ability(1)))
    val attack = Attack(attacker, defender, 10)
    attack.armorClass should be (5)
  }

  test("rogues should return dexterity modifier - strength modifier") {
    val attacker = Character(name = "cain", characterClass = Rogue, abilities = Map(Ability.Dexterity -> Ability(12), Ability.Strength -> Ability(5)))
    val defender = Character(name = "abel")
    val attack = Attack(attacker, defender, 10)
    attack.attackRoll should be (11)
  }


}
