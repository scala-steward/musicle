import Game.*
import Audio.{ AudioController, YoutubeEmbed }
import com.raquo.laminar.api.L.{ *, given }
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.compiletime.ops.float

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

val audioController: AudioController    = YoutubeEmbed
val songLibrary: SongLibrary            = SongLibrary(SongLibrary.loadSongs())
val game: Game                          = Game(SongPicker.TodaySong(songLibrary), audioController)
var guessSlotVars: List[Var[GuessSlot]] = List()
val finishedGame: Var[Boolean]          = Var(false)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("Musicle"),
    ),
    gameComponent(),
    footerTag(
      p("Created with <3 by Kresten"),
    ),
  )

def gameComponent(): HtmlElement =
  // audio.load()

  // Initialize guess slot Vars
  val slots = (0 until game.maxGuesses).map(_ => Var(GuessSlot(""))).toList
  guessSlotVars = slots // Update the global state (if needed)

  val initialSlots = guessSlotVars.map(guessElement)

  mainTag(
    YoutubeEmbed.component(game.actualSong.sourcePath, finishedGame),
    h1("AUROLE: V1.0"),
    ul(cls := "guess-container",
      initialSlots.map(li(_))
    ),
    //progressBar(),
    playButton(),
    searchField(),
  )

def playButton(): HtmlElement =
  button("Play", onClick --> { _ => game.playCurrentStage() })

def guessElement(guessSlot: Var[GuessSlot]): HtmlElement =
  input(cls := List("guess", "guess-box"),
    readOnly := true, value <-- guessSlot.signal.map(_.text)
  )

def songListElement(song: Song): HtmlElement =
  li(cls := "song",
    p(song.toString),
    onClick --> { _ =>
      // Pre-guess
      guessSlotVars(game.currentStage()).set(GuessSlot(song.toString))

      // Guess
      val correct = game.guessSong(song)
      dom.console.log(correct)
      finishedGame.set(correct || game.currentStage() == game.maxGuesses)

      // Post-guess
      if correct then audioController.setSnippet(0, 500_000) // Play whole song
      else game.loadStage(song, game.currentStage())

      game.playCurrentStage()
    },
  )

def searchField(): HtmlElement =
  val searchQueryVar = Var("")

  div(
    cls := "container",
    input(
      cls         := List("guess-input", "guess-box"),
      typ         := "text",
      placeholder := "Runaway...",
      value <-- searchQueryVar.signal,
      inContext { thisNode =>
        onInput.mapTo(thisNode.ref.value) --> searchQueryVar
      },
    ),
    ul(
      cls := "searched-songs",
      children <-- searchQueryVar.signal.map { query =>
        query.trim match {
          case "" => Nil
          case _ =>
            songLibrary.songs
              .filter(song => song.toString.toLowerCase.contains(query.toLowerCase) && !game.isGuessed(song))
              .take(5)
              .map(song => songListElement(song))
        }
      },
    ),
  )

case class GuessSlot(text: String)

val progressbar: Var[Float] = Var(0)

def progressBar(): HtmlElement =
  /*js.timers.setInterval(50) {
    progressbar.set((audio.seek() / audio.duration()).toFloat)
  }*/

  div(
    cls := "progressbar-container",
    div(cls := "progressbar", styleAttr <-- progressbar.signal.map(p => s"width: ${p * 100}%;")),
  )
