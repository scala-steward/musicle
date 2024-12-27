package Game

case class GameType (maxGuesses: Int, stageSprites: Seq[Int], songs: Seq[Song])

object GameType:
  val StandardGame: GameType = GameType(
    maxGuesses = 5,
    stageSprites = List(1000, 3000, 6000, 9000, 16000),
    songs = SongLibrary.loadSongs()
  )
