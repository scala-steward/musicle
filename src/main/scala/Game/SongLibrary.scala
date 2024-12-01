package Game

import Game.Song

class SongLibrary:
  var songs: List[Song] = loadSongs()

  def loadSongs(): List[Song] =
    List(
      Song("Ghost Town", Album("Ghost Town"), Author("The Specials"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("The Blade", Album("What Happended To The Heart?"), Author("AURORA"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Runaway", Album("Running with the Wolves"), Author("AURORA"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Everything Matters (Feat. Pomme)", Album("The Gods We Can Touch"), Author("AURORA"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Little Boy In The Grass", Album("Running with the Wolves"), Author("AURORA"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
      Song("Thank U", Album("Goodnight Songs For Rebel Girls"), Author("AURORA"), "THE_SPECIALS_Ghost_Town_1981.ogg"),
    )
