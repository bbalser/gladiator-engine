package gladiator

object Die {

  /*
    The argument is implicit to implement a form of injection.
    DieTests supplies the RandomFacade explicitly, but the main
    application will define it implicitly and let the compiler
    "inject" it.
   */
  def d20(implicit r:RandomFacade) = r.next

}
