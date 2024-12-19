package Game

case class Song(title: String, startOffset: Integer, album: Album.Album, author: Author.Author, sourcePath: String):
  override def toString: String = s"$title ($album)"
