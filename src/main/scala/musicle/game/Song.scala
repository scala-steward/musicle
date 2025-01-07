package musicle.game

import Game.*

case class Song(title: String, startOffset: Integer, album: Album, author: Author, sourcePath: String):
  override def toString: String = s"$title ($album)"
