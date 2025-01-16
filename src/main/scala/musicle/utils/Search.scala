package musicle.utils

object SearchUtil:
  def search[A](database: Seq[A], query: String, exclusionFilter: A => Boolean): Seq[A] =
    database
      .filter(result => result.toString.toLowerCase.contains(query.toLowerCase))
      .filterNot(exclusionFilter)
