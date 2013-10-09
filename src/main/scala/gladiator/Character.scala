package gladiator

class Character(val name: String,
                val alignment: Character.Alignment = Character.Alignment.Neutral,
                val armorClass:Int = 10,
                val hitPoints: Int = 5) {

  def attack(defender: Character, roll: Int): (Character, Character, Attack) = {
    val attack = Attack(this, defender, roll)
    (this, defender.applyDamageFromAttack(attack), attack)
  }


  private def applyDamageFromAttack(attack: Attack): Character = {
    if (attack.isHit) {
      copy(hitPoints = hitPoints - attack.damage)
    } else {
      this
    }
  }

  private def copy(name: String = this.name,
                   alignment: Character.Alignment = this.alignment,
                   armorClass: Int = this.armorClass,
                   hitPoints: Int = this.hitPoints): Character = {
    new Character(name, alignment, armorClass, hitPoints)
  }

}

object Character {

  class Alignment
  object Alignment {
    case object Good extends Alignment
    case object Neutral extends Alignment
    case object Evil extends Alignment
  }

}