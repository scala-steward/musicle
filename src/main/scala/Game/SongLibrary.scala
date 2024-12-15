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

      // Infection Of A Different Kind – Step 1
      Song("Queendom", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "-PIC0wb8jgY"),
      Song("Forgotten Love", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "Pm0GiTTAXUc"),
      Song("Gentle Earthquakes", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "El_N-2V9iv8"),
      Song("All Is Soft Inside", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "CRbrZtR8DOg"),
      Song("It Happened Quiet", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "2wd7hOSPPP0"),
      Song("Churchyard", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "KOhjQvUI3Qo"),
      Song("Soft Universe", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "daHIgxDSLMY"),
      Song("Infections Of A Different Kind", Album("Infection Of A Different Kind – Step 1"), Author("AURORA"), "7EmdiaUX-wI"),

      // A Different Kind Of Human – Step 2
      Song("The River", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "pz5FzFL2L7w"),
      Song("Animal", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "eMM1FC3Tbe0"),
      Song("Dance On The Moon", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "2GPh59K8_ys"),
      Song("Daydreamer", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "soCJTCElcbk"),
      Song("Hunger", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "TgAjyR3_2yM"),
      Song("Soulless Creatures", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "EiWUBdyDk70"),
      Song("In Bottles", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "1okNR0Dzyv8"),
      Song("A Different Kind Of Human", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "WuPh0Ozd4EE"),
      Song("Apple Tree", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "fgjId8R5RL8"),
      Song("The Seed", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "JJTjc7iRNY8"),
      Song("Mothership", Album("A Different Kind Of Human – Step 2"), Author("AURORA"), "IP1Qhq8kQBY"),

      // What Happened To The Heart?
      Song("Echo Of My Shadow", Album("What Happended To The Heart?"), Author("AURORA"), "kkPYHeSU_y4"),
    )
