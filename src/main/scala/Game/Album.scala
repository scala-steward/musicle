package Game

object Album:
  opaque type Album = String

  def apply(title: String): Album = title

  extension (x: Album) def toString: String = x
