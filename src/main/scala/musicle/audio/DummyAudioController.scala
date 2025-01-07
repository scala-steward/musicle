package musicle.audio

import org.scalajs.dom

class DummyAudioController extends AudioController:
  override def setSnippet(startMs: Int, endMs: Int): Unit =
    dom.console.log(s"setSnippet, startMs: $startMs, endMs: $endMs")

  override def setSong(source: String): Unit = dom.console.log(s"setSong, source: $source")

  override def play(): Unit = dom.console.log("play")

  override def pause(): Unit = dom.console.log("pause")
