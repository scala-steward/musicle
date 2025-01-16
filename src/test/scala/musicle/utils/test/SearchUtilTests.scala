package musicle.utils.test

import musicle.game.Game
import org.scalatest.flatspec.AnyFlatSpec
import musicle.utils.SearchUtil

class SearchUtilTests extends AnyFlatSpec {
  "Accurate search" should "only return accurate result" in {
    val accurateResult = "test"

    val db = List("abc", "cba", "bac", accurateResult)

    val results = SearchUtil.search(db, "test", _ => false)
    assert(results.length === 1)
    assert(results.head === accurateResult)
  }
}
