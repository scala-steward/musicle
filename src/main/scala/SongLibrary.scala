class SongLibrary:
  var songs: List[Song] = loadSongs()

  def loadSongs(): List[Song] =
    List(
      Song("Ghost Town", "The Specials", ""),
    )
