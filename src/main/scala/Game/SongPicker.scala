package Game

import java.time.*
import scala.util.Random

object SongPicker:
  def DateSong(date: LocalDate, songLibrary: SongLibrary): Song =
    val rand = new Random(date.hashCode)
    songLibrary.songs(rand.nextInt(songLibrary.songs.length))
