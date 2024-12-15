import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom
import org.scalajs.dom.html

object YoutubeEmbed:
  private val youtubeVideoSource: Var[String] = Var("VoGilr7ediw")
  val youtubeStart: Var[Int]                  = Var(0)
  val youtubeEnd: Var[Int]                    = Var(1)

  private def sendPostMessage(iframe: dom.HTMLIFrameElement, command: String): Unit =
    iframe.contentWindow.postMessage(command, "*")

  def component(): HtmlElement =
    iframe(
      idAttr := "ytplayer",
      src <-- youtubeVideoSource.signal
        .combineWithFn(youtubeStart.signal, youtubeEnd.signal) { (videoSource, start, end) =>
          s"https://www.youtube-nocookie.com/embed/$videoSource?enablejsapi=1&start=$start&end=$end"
        },
    )

  def play() =
    val iframe = dom.document.getElementById("ytplayer").asInstanceOf[dom.HTMLIFrameElement]
    dom.console.log(iframe)
    sendPostMessage(iframe, """{ "event": "command", "func": "playVideo" }""")

  def pause() =
    val iframe = dom.document.getElementById("ytplayer").asInstanceOf[dom.HTMLIFrameElement]
    sendPostMessage(iframe, """{ "event": "command", "func": "pauseVideo" }""")
