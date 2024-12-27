package Audio

import Game.Game

trait AudioController:
  def setSnippet(startMs: Int, endMs: Int): Unit

  def setSong(source: String): Unit

  def play(): Unit

  def pause(): Unit

  def loadFullSong(game: Game): Unit =
    setSong(game.actualSong.sourcePath)
    setSnippet(0, 500_000)

  def loadCurrentStage(game: Game): Unit =
    setSong(game.actualSong.sourcePath)
    setSnippet(game.actualSong.startOffset, game.actualSong.startOffset + game.gameType.stageSprites(game.currentStage))
