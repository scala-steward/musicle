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
var guessSlotVars: List[Var[GuessSlot]] = List()

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
  // Initialize guess slot Vars
  val slots = (0 until 5).map(_ => Var(GuessSlot(""))).toList
  guessSlotVars = slots // Update the global state (if needed)

  val initialSlots = guessSlotVars.map(guessElement)

  mainTag(
    h1("Hello Musicle! V1.0"),
    ul(cls := "guess-container",
      initialSlots.map(li(_))
    ),
    playButton(),
    searchField(),
    songEmbed(audioSourceVar),
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

def guessElement(guessSlot: Var[GuessSlot]): HtmlElement =
  input(cls := List("guess", "guess-box"),
    readOnly := true,
    value <-- guessSlot.signal.map(_.text)
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
                  dom.console.log(game.guessSong(song))
                  guessSlotVars(0).set(GuessSlot(song.toString))
                }
              )
            )
        }
      }
    )
  )

case class GuessSlot(text: String)
