package gladiator.classes

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import gladiator.{Ability, Attack, Character}

class MonkTests extends FunSuite with ShouldMatchers {

  test("hitPointModifier should return 1 * level") {
    (1 to 20).foreach { x =>
      val character = Character(name = "character", experiencePoints = Character.Levels(x))
      Monk.hitPointModifier(character) should be (1 * character.level)
    }
  }

  test("monk should gain 6 hit points per level") {
    (1 to 20).foreach { x =>
      val character = Character(name = "character", experiencePoints = Character.Levels(x), characterClass = Monk)
      character.maxHitPoints should be (6 * x)
    }
  }

  test("monk has base damage of 3 instead of 1") {
    val monk = Character(name = "monk", characterClass = Monk)
    val defender = Character(name = "defender")
    val attack = Attack(monk, defender, 10)
    attack.damage should be (3)
  }

  test("monk also adds wisdom modifier to armorClass") {
    val monk = Character(name = "monk", characterClass = Monk, abilities = Map(Ability.Wisdom -> Ability(12), Ability.Dexterity -> Ability(12)))
    monk.armorClass should be (12)
  }

  test("monk only adds wisdom modifier to armorClass when positive") {
    val monk = Character(name = "monk", characterClass = Monk, abilities = Map(Ability.Wisdom -> Ability(5)))
    monk.armorClass should be (10)
  }

  test("A Monk's attack roll is increased by 1 every 3rd level") {
    val defender = Character(name = "defender")
    val expected = List(0, 1, 2, 3, 3, 5, 5, 6, 7, 8)
    (1 to 10).foreach { x =>
      val monk = Character(name = "monk", characterClass = Monk, experiencePoints = Character.Levels(x))
      val attack = Attack(monk, defender, 0)
      attack.attackRoll should be (expected(x-1))
    }
  }


}
