package Game

class Game(actualSong: Song):
  private var guesses: List[Song] = List()

  def isGuessed(guessedSong: Song): Boolean =
    guesses.contains(guessedSong)

  def guessSong(guessedSong: Song): Boolean = {
    if actualSong == guessedSong then
      true
    else
      guesses = guesses :+ guessedSong // Append guessedSong to guesses immutably
      false
  }
