package Game

import scala.util.Random

val rand = new Random()

object SongPicker:
  def TodaySong(songLibrary: SongLibrary): Song =
    songLibrary.songs(rand.nextInt(songLibrary.songs.length))
