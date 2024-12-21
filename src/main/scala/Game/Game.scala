package Game

import Audio.AudioController

class Game(val actualSong: Song, val songs: List[Song], audioController: AudioController):
  val maxGuesses                          = 5
  var guesses: List[Option[Song]] = List()
  private val stageSprites: List[Int] = List(
    1000, 2000, 4000, 8000, 16000,
  )

  def loadStage(): Unit =
    audioController.setSnippet(actualSong.startOffset, actualSong.startOffset + stageSprites(currentStage()))

  def playCurrentStage(): Unit =
    audioController.play()

  def playFullSong(): Unit =
    audioController.setSnippet(0, 500_000)

  def currentStage(): Int = guesses.length

  def isGuessed(guessedSong: Song): Boolean =
    guesses.contains(guessedSong)

  def guessStage(guessedSong: Song): Boolean =
    guesses = guesses :+ Some(guessedSong)

    actualSong == guessedSong

  def skipStage(): Unit =
    guesses = guesses :+ None
