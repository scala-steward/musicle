package Game

import Game.Song

class SongLibrary:
  var songs: List[Song] = loadSongs()

  def loadSongs(): List[Song] =
    List(
      Song("Ghost Town", "The Specials", "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("The Blade", "Aurora", "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Runaway", "Aurora", "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Everything Matters (Feat. Pomme)", "Aurora", "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Little Boy In The Grass", "Aurora", "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Thank U", "Aurora", "THE_SPECIALS_Ghost_Town_1981.ogg"),
    )
