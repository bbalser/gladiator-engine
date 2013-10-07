package gladiator

class Attack(val attacker: Character, val defender: Character) {



}

object Attack {
  def apply(attacker: Character, defender: Character) = new Attack(attacker, defender)
}
