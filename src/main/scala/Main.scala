import Game.*
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

val audioSourceVar: Var[String] = Var("")
val songLibrary: SongLibrary = SongLibrary(SongLibrary.loadSongs())
val game: Game = Game(songLibrary.songs.head)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("Musicle")
    ),
    gameComponent(),
    footerTag(
      p("Created with <3 by Kresten")
    ),
  )

def gameComponent(): HtmlElement =
  mainTag(
    h1("Hello Musicle! V1.0"),
    ul(cls := "guess-container",
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
    ),
    playButton(),
    searchField(),
    songEmbed(audioSourceVar)
  )

def songEmbed(songSrc: Var[String]): HtmlElement =
  audioTag(
    idAttr := "music",
    sourceTag(
      src <-- songSrc.signal,
      `type` := "audio/ogg",
    )
  )

def playButton(): HtmlElement =
  button("Play",
    onClick --> { _ => audioSourceVar.set(songLibrary.songs.head.sourcePath)}
  )

def guessElement(): HtmlElement =
  input(cls := List("guess", "guess-box"),
    readOnly := true,
  )

def searchField(): HtmlElement =
  val searchQueryVar = Var("")
  val selectedSong: Var[Song] = Var(songLibrary.songs.head)

  div(
    cls := "container",
    input(
      cls := List("guess-input", "guess-box"),
      typ := "text",
      placeholder := "Runaway...",
      value <-- searchQueryVar.signal,
      inContext { thisNode =>
        onInput.mapTo(thisNode.ref.value) --> searchQueryVar
      }
    ),
    ul(cls := "searched-songs",
      children <-- searchQueryVar.signal.map { query =>
        query.trim match {
          case "" => Nil
          case _ => songLibrary.songs
            .filter { case (song) => song.toString.toLowerCase.contains(query.toLowerCase) }
            .take(5)
            .map(song =>
              li(cls := "song",
                p(song.toString),
                onClick --> { _ =>
                  selectedSong.set(song)
                  searchQueryVar.set(song.toString)
                  dom.console.log(song.toString)
                  game.guessSong(song)
                }
              )
            )
        }
      }
    )
  )
