package Audio

trait AudioController:
  def setSnippet(startMs: Int, endMs: Int): Unit

  def setSong(source: String): Unit

  def play(): Unit

  def pause(): Unit
