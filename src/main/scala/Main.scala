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
  button("Play",
    onClick --> { _ =>
      audioSourceVar.set(songLibrary.songs(0).sourcePath)
      audioVar.now().foreach(_.play())
    }
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
