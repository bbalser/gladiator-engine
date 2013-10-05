package gladiator

import scala.util.Random

/*
  A facade to isolate the "external" library Random from our application.
  In this case, the main purpose is to simplify testing, but in general
  a facade also reduces the impact of changes in the external library.
  A facade is the edge of a world map with a note: "Here be dragons".

  I've seen two schools of thought about facades.  Some would say that
  the facade should wrap only what the external library does.  Since
  you can't effectively test the facade you don't want any new behavior
  in its implementation.

  I tend toward the second opinion, that the facade is for the application
  and it should be designed with the lowest-level behavior appropriate
  to the application.  So I included the limitation of 1 to 20 in the
  facade.  If, however, the mapping from the library to the application
  is at all complicated, I'll fall back to the first point of view.
 */

class RandomFacade {

  def next:Int = Random.nextInt(20) + 1

}
