package gladiator

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

/*
  This is an integration test intended for documenting and confirming
  the behavior of the library hidden behind our facade.  There is no
  need to re-run the test unless the library has changed.  This test
  is  quick so it doesn't hurt to run it all the time.  In other cases the
  facade hides a slow operation (e.g. database call) and we wouldn't
  want it in the normal test cycle.
 */
class RandomFacadeTests extends FunSuite with ShouldMatchers {

  test("next does not return any numbers outside of 1 to 20") {
    val r = new RandomFacade
    (1 to 1000).foreach {x =>
      r.next should be >= 1
      r.next should be <= 20
    }
  }

  test("next returns all the numbers in approximately the same distribution") {
    var r = new RandomFacade
    val counts = (1 to 100000).map(n => r.next).groupBy(n => n).map{ case (n, seq) => seq.length}
    var variance = (counts.max - counts.min) / counts.max.toDouble

    counts.toList.length should be (20)
    variance should be < (0.1)
  }


}
