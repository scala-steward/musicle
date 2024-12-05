import Game.*
import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom
import typings.howler.mod.*
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.compiletime.ops.float

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

val audioSourceVar: Var[String] = Var("")
val songLibrary: SongLibrary = SongLibrary(SongLibrary.loadSongs())
val game: Game = Game(songLibrary.songs.head)
var guessSlotVars: List[Var[GuessSlot]] = List()

val audio = new Howl(
    js.Dynamic.literal(
      src = js.Array(game.actualSong.sourcePath),
      sprite = js.Dictionary(
        "blast" -> js.Array(5000, 7000),
        "blast2" -> js.Array(1000, 2000),
      ),
    ).asInstanceOf[typings.howler.mod.HowlOptions]
  )

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
    progressBar(),
    playButton(),
    searchField(),
    songEmbed(audioSourceVar),
  )

val audioVar = Var[Option[dom.html.Audio]](None) // Store the audio element reference reactively

def songEmbed(songSrc: Var[String]): HtmlElement =
  audioTag(
    idAttr := "music",
    onMountCallback(ctx => {
      // Capture the audio element on mount
      val audioElement = ctx.thisNode.ref.asInstanceOf[dom.html.Audio]
      audioVar.set(Some(audioElement))
    }),
    sourceTag(
      src <-- songSrc.signal,
      `type` := "audio/mpeg",
    )
  )

def playButton(): HtmlElement =
  audio.load()

  button("Play",
    onClick --> { _ => audio.play() }
  )

def guessElement(guessSlot: Var[GuessSlot]): HtmlElement =
  input(cls := List("guess", "guess-box"),
    readOnly := true,
    value <-- guessSlot.signal.map(_.text)
  )

val progressbar: Var[Float] = Var(0)

def progressBar(): HtmlElement =
  js.timers.setInterval(50) {
    progressbar.set((audio.seek() / audio.duration()).toFloat)
  }

  div(cls := "progressbar-container",
    div(cls := "progressbar",
        styleAttr <-- progressbar.signal.map(p => s"width: ${p * 100}%;")
    )
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
            .filter(song => song.toString.toLowerCase.contains(query.toLowerCase) && !game.isGuessed(song))
            .take(5)
            .map(song =>
              li(cls := "song",
                p(song.toString),
                onClick --> { _ =>
                  // Update states
                  selectedSong.set(song)
                  searchQueryVar.set(song.toString)

                  val guessSlotIndex = game.currentGuessSlotIndex()
                  dom.console.log(guessSlotIndex)
                  guessSlotVars(guessSlotIndex).set(GuessSlot(song.toString))

                  game.guessSong(song)
                }
              )
            )
        }
      }
    )
  )

case class GuessSlot(text: String)
