package musicle.audio

import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom
import com.raquo.laminar.codecs.*

val allowAttr: HtmlProp[String, String] = htmlProp("allow", StringAsIsCodec)

class YoutubeEmbed extends AudioController:
  private val youtubeVideoSource: Var[String] = Var("VoGilr7ediw")
  private val youtubeStart: Var[Int]          = Var(0)
  private val youtubeEnd: Var[Int]            = Var(1)
  val videoHidden: Var[Boolean]               = Var(true)


  private def sendPostMessage(iframe: dom.HTMLIFrameElement, command: String): Unit =
    iframe.contentWindow.postMessage(command, "*")

  def component(): HtmlElement =
    iframe(
      idAttr := "ytplayer",
      allowAttr := "autoplay",
      hidden <-- videoHidden.signal,
      src <-- youtubeVideoSource.signal
        .combineWithFn(youtubeStart.signal, youtubeEnd.signal) { (videoSource, start, end) =>
          s"https://www.youtube-nocookie.com/embed/$videoSource?enablejsapi=1&start=$start&end=$end"
        },
    )

  def setSong(source: String): Unit = youtubeVideoSource.set(source)

  def setSnippet(startMs: Int, endMs: Int): Unit =
    // Transaction?
    val startSec = math.ceil(startMs / 1000).toInt
    val endSec   = math.ceil(endMs / 1000).toInt

    dom.console.log(s"Snippet: $startSec - $endSec")
    youtubeStart.set(startSec)
    youtubeEnd.set(endSec)

  def play(): Unit =
    val iframe = dom.document.getElementById("ytplayer").asInstanceOf[dom.HTMLIFrameElement]
    sendPostMessage(iframe, """{ "event": "command", "func": "playVideo" }""")

  def pause(): Unit =
    val iframe = dom.document.getElementById("ytplayer").asInstanceOf[dom.HTMLIFrameElement]
    sendPostMessage(iframe, """{ "event": "command", "func": "pauseVideo" }""")
