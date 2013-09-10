package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

class CharacterTests extends FunSuite with ShouldMatchers {

  test("A Character can get and set a name") {
    val character = new Character("Bob the Wombat")
    character.name should be ("Bob the Wombat")
  }

}
