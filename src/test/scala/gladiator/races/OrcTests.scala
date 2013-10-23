package gladiator.races

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import gladiator.{Ability, Character}

class OrcTests extends FunSuite with ShouldMatchers {

  test("Orcs have racial trait of +4 to strength") {
    val orc = Character(name = "orc", race = Orc)
    orc.ability(Ability.Strength) should be (Ability(14))
  }

  test("Orcs have racial trait of -2 to intelligence") {
    val orc = Character(name = "orc", race = Orc)
    orc.ability(Ability.Intelligence) should be (Ability(8))
  }

  test("Orcs have racial trait of -2 to wisdom") {
    val orc = Character(name = "orc", race = Orc)
    orc.ability(Ability.Wisdom) should be (Ability(8))
  }

  test("Orcs have racial trait of -2 to charisma") {
    val orc = Character(name = "orc", race = Orc)
    orc.ability(Ability.Charisma) should be (Ability(8))
  }

  test("Orcs have racial trait of +2 to armor class") {
    val orc = Character(name = "orc", race = Orc)
    orc.armorClass should be (12)
  }

}
