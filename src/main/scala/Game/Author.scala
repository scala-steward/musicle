package Game

object Author:
  opaque type Author = String

  def apply(author: String): Author = author
