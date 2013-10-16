package gladiator.classes

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import gladiator.{Attack, Character}

class FighterTests extends FunSuite with ShouldMatchers {

  test("attackRollModifier should increase attack roll for every odd level") {
    val defender = Character(name = "defender")
    val list = List( (1, 1), (2, 1), (3, 2), (4, 2), (5, 3), (6, 3) )
    list.foreach { case (level, modifier)=>
      val fighter = Character(name = "Bubba", experiencePoints = Character.Levels(level))
      Fighter.attackRollModifier(fighter, defender)  should be (modifier)
    }
  }

  test("attack based on attackModification") {
    val defender = Character(name = "defender")

    (1 to 20).foreach { x =>
      val fighter = Character(name = "fighter", characterClass = Fighter, experiencePoints = Character.Levels(x))
      val attack = Attack(fighter, defender, 10)
      attack.attackRoll should be (10 + x)
    }

  }

  test("hitPointModifier should add 5 hitpoints per level") {
    (1 to 20).foreach { x =>
      val character = Character(name = "character", experiencePoints = Character.Levels(x))
      Fighter.hitPointModifier(character) should be (5 * character.level)
    }
  }

  test("hitPointModifier should cause 10 points per character level total") {
    (1 to 20).foreach { x =>
      val character = Character(name = "character", experiencePoints = Character.Levels(x), characterClass = Fighter)
      character.maxHitPoints should be (10 * character.level)
    }
  }


}
