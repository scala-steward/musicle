package Controls

import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom

object SearchFieldControl:
  def component[A](
      database: Seq[A],
      exclusionFilter: A => Boolean,
      renderListItem: A => HtmlElement,
      handleListItemClick: A => Unit,
  ): HtmlElement =
    val searchQueryVar = Var("")

    div(
      cls := "container",
      input(
        cls         := List("guess-input", "guess-box"),
        typ         := "text",
        placeholder := "Runaway...",
        value <-- searchQueryVar.signal,
        inContext { thisNode =>
          onInput.mapTo(thisNode.ref.value) --> searchQueryVar
        },
      ),
      ul(
        cls := "searched-songs",
        children <-- searchQueryVar.signal.map { query =>
          query.trim match {
            case "" => Seq.empty
            case _ =>
              database
                .filter(result => result.toString.toLowerCase.contains(query.toLowerCase))
                .filterNot(exclusionFilter)
                .take(5)
                .map(renderListItem)
                .map(li(cls := "song", _, handleListItemClick --> { _ => handleListItemClick(song) }))
          }
        },
      ),
    )
