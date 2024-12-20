package Controls

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

object ProgressbarControl:
  val progressbar: Var[Float] = Var(0)

  def component(): HtmlElement =
    /*js.timers.setInterval(50) {
      progressbar.set((audio.seek() / audio.duration()).toFloat)
    }*/

    div(
      cls := "progressbar-container",
      div(cls := "progressbar", styleAttr <-- progressbar.signal.map(p => s"width: ${p * 100}%;")),
    )
