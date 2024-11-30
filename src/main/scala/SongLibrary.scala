class SongLibrary:
  var songs: List[Song] = loadSongs()

  def loadSongs(): List[Song] =
    List(
      Song("Ghost Town", "The Specials", "THE_SPECIALS_Ghost_Town_1981.ogg"),
    )
