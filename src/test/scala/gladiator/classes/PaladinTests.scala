package gladiator.classes

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

import gladiator.{Attack, Character}

class PaladinTests extends FunSuite with ShouldMatchers {

  test("Paladin hitPointModifier should return 3 per level") {
    (1 to 20).foreach { x =>
      val character = Character(name = "pal", experiencePoints = Character.Levels(x))
      Paladin.hitPointModifier(character) should be (3 * x)
    }
  }

  test("Paladin should get hit points per level") {
    (1 to 20).foreach { x =>
      val character = Character(name = "pal", experiencePoints = Character.Levels(x), characterClass = Paladin)
      character.maxHitPoints should be (8 * x)
    }
  }

  test("Paladin get +2 to attack against evil characters") {
    (1 to 20).foreach { x =>
      val paladin1 = Character(name = "pal", characterClass = Paladin, experiencePoints = Character.Levels(x))
      val defender1 = Character(name = "defender", alignment = Character.Alignment.Neutral)
      val attack1 = Attack(paladin1, defender1, 10)

      val paladin2 = Character(name = "pal", characterClass = Paladin, experiencePoints = Character.Levels(x))
      val defender2 = Character(name = "defender", alignment = Character.Alignment.Evil)
      val attack2 = Attack(paladin2, defender2, 10)

      attack2.attackRoll should be (attack1.attackRoll + 2)
    }
  }

  test("Paladin get +2 to damage against evil characters") {
    (1 to 20).foreach { x =>
      val paladin1 = Character(name = "pal", characterClass = Paladin, experiencePoints = Character.Levels(x))
      val defender1 = Character(name = "defender", alignment = Character.Alignment.Neutral)
      val attack1 = Attack(paladin1, defender1, 10)

      val paladin2 = Character(name = "pal", characterClass = Paladin, experiencePoints = Character.Levels(x))
      val defender2 = Character(name = "defender", alignment = Character.Alignment.Evil)
      val attack2 = Attack(paladin2, defender2, 10)

      attack2.damage should be (attack1.damage + 2)
    }

  }

  test("Paladin does triple damage when critical against evil character") {
    val paladin =  Character(name = "pal", characterClass = Paladin)
    val defender = Character(name = "defender", alignment = Character.Alignment.Evil)
    val attack = Attack(paladin, defender, 20)
    attack.damage should be (9)
  }

  test("Paladin does double damage when critical against non-evil character") {
    val paladin =  Character(name = "pal", characterClass = Paladin)
    val defender = Character(name = "defender", alignment = Character.Alignment.Neutral)
    val attack = Attack(paladin, defender, 20)
    attack.damage should be (2)
  }

  test("Paladin attack roll should increase 1 every level") {
    (1 to 20).foreach { x =>
      val paladin = Character(name = "pal", characterClass = Paladin, experiencePoints = Character.Levels(x))
      val defender = Character(name = "defender")
      val attack = Attack(paladin, defender, 10)
      attack.attackRoll should be (10+x)
    }
  }


}
