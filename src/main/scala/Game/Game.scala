package Game

import com.raquo.laminar.api.L.Var

case class Game(actualSong: Song, gameType: GameType, guesses: List[Guess]):
  def currentStage: Int = guesses.length

  def finished: Boolean = won || currentStage == gameType.maxGuesses

  def won: Boolean = isGuessed(actualSong)

  def isGuessed(guessedSong: Song): Boolean =
    guesses.exists(_.song.contains(guessedSong))

  def guessStage(guessedSong: Song): Game =
    copy(guesses = guesses :+ Guess(Some(guessedSong)))

  def skipStage(): Game =
    copy(guesses = guesses :+ Guess(None))

case class Guess(song: Option[Song])
