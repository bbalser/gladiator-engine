package gladiator.races

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite
import gladiator.{Attack, Ability, Character}

class DwarfTests extends FunSuite with ShouldMatchers {


  test("Dwarf has racial bonuses") {
    val dwarf = Character(name = "Bruenor", race = Dwarf)
    dwarf.ability(Ability.Constitution) should be (Ability(12))
    dwarf.ability(Ability.Charisma) should be (Ability(8))
  }

  test("Dwarves will gain double Constitution modifier per level") {
    (1 to 20).foreach { x =>
      val dwarf = Character(name = "Bruenor", race = Dwarf, experiencePoints = Character.Levels(x))
      dwarf.maxHitPoints should be ((5 + 2) * x)
    }
  }

  test("Dwaves will not gain double Constituion modifier per level when modifier is negative") {
    (1 to 20).foreach { x =>
      val dwarf = Character(name = "dwarf", race = Dwarf, experiencePoints = Character.Levels(x), abilities = Map(Ability.Constitution -> Ability(6)))
      dwarf.maxHitPoints should be ((5 - 1) * x)
    }
  }

  test("Dwarves get +2 to attacks and damage when attacking Orcs") {
    val dwarf = Character(name = "Bruenor", race = Dwarf)
    val orc = Character(name = "Gragg", race = Orc)
    val attack1 = Attack(dwarf, orc, 10)
    attack1.attackRoll should be (12)

    val nonOrc = Character(name = "Gregg")
    val attack2 = Attack(dwarf, nonOrc, 10)
    attack2.attackRoll should be (10)
  }



}
