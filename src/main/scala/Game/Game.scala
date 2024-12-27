package Game

import Audio.AudioController
import com.raquo.laminar.api.L.Var

class Game(val actualSong: Song, val gameType: GameType, audioController: AudioController):
  val guesses: Var[List[Guess]] = Var(List())

  def finished: Boolean = isGuessed(actualSong)

  def loadStage(): Unit =
    audioController.setSong(actualSong.sourcePath)
    audioController.setSnippet(actualSong.startOffset, actualSong.startOffset + gameType.stageSprites(currentStage))

  def playCurrentStage(): Unit =
    audioController.play()

  def playFullSong(): Unit =
    audioController.setSnippet(0, 500_000)

  def currentStage: Int = guesses.now().length

  def isGuessed(guessedSong: Song): Boolean =
    guesses.now().exists(_.song.contains(guessedSong))

  def guessStage(guessedSong: Song): Boolean =
    guesses.update(_ :+ Guess(Some(guessedSong)))
    actualSong == guessedSong

  def skipStage(): Unit = guesses.update(_ :+ Guess(None))

case class Guess(song: Option[Song])
