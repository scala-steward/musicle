package Game

import Game.Song

case class SongLibrary(songs: List[Song])

object SongLibrary:
  def loadSongs(): List[Song] =
    List(
      // Singles
      Song("Thank U", Album("Goodnight Songs For Rebel Girls"), Author("AURORA"), "E39j5Mg7X-0"),

      // Running With The Wolves (EP)
      Song("Runaway", Album("Running with the Wolves (EP)"), Author("AURORA"), "BRpS93I8_Jk"),
      Song("Running With The Wolves", Album("Running with the Wolves (EP)"), Author("AURORA"), "2M94IMhk6o0"),
      Song("In Boxes", Album("Running with the Wolves (EP)"), Author("AURORA"), "blRQ8GUcNJ0"),
      Song("Little Boy In The Grass", Album("Running with the Wolves (EP)"), Author("AURORA"), "2MTsfnntwWc"),

      // The Gods We Can Touch
      Song("The Forbidden Fruits of Eden", Album("The Gods We Can Touch"), Author("AURORA"), "wItPEYN5aJw"),
      Song("Everything Matters (Feat. Pomme)", Album("The Gods We Can Touch"), Author("AURORA"), "oa7JwwdiA98"),
      Song("Giving In to the Love", Album("The Gods We Can Touch"), Author("AURORA"), "mcTszeIl-SE"),
      Song("Cure For Me", Album("The Gods We Can Touch"), Author("AURORA"), "VoGilr7ediw"),
      Song("You Keep Me Crawling", Album("The Gods We Can Touch"), Author("AURORA"), "p3AMcbXX4O0"),
      Song("Exist For Love", Album("The Gods We Can Touch"), Author("AURORA"), "rAMX3yk5voI"),
      Song("You Keep Me Crawling", Album("The Gods We Can Touch"), Author("AURORA"), "p3AMcbXX4O0"),
      Song("Heathens", Album("The Gods We Can Touch"), Author("AURORA"), "U36rDsd6ARs"),
      Song("The Innocent", Album("The Gods We Can Touch"), Author("AURORA"), "5eoqQBJOw4A"),
      Song("Exhale Inhale", Album("The Gods We Can Touch"), Author("AURORA"), "5jICJdWnH6o"),
      Song("A Temporary High", Album("The Gods We Can Touch"), Author("AURORA"), "7_4JqVAK5WE"),
      Song("A Dangerous Thing", Album("The Gods We Can Touch"), Author("AURORA"), "6KmWlzUz_54"),
      Song("Artemis", Album("The Gods We Can Touch"), Author("AURORA"), "qpx1wwlQf3U"),
      Song("Blood In the Wine", Album("The Gods We Can Touch"), Author("AURORA"), "sKSupqVcVJw"),
      Song("This Could Be a Dream", Album("The Gods We Can Touch"), Author("AURORA"), "DbiB1AtCA9k"),
      Song("A Little Place Called the Moon", Album("The Gods We Can Touch"), Author("AURORA"), "QdLNfkFWF0g"),

      // What Happened To The Heart?
      Song("Echo Of My Shadow", Album("What Happended To The Heart?"), Author("AURORA"), "kkPYHeSU_y4"),
    )
