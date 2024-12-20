import Game.*
import Audio.{ AudioController, YoutubeEmbed }
import Controls.SearchFieldControl
import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
  game.loadStage()

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
  // Initialize guess slot Vars
  val slots = (0 until game.maxGuesses).map(_ => Var(GuessSlot(None, false, false))).toList
  guessSlotVars = slots // Update the global state (if needed)

  val initialSlots = guessSlotVars.map(guessElement)

  mainTag(
    YoutubeEmbed.component(game.actualSong.sourcePath, finishedGame),
    h1("AUROLE: V1.0"),
    ul(cls := "guess-container",
      initialSlots.map(li(_))
    ),
    //progressBar(),
    skipButton(),
    playButton(),
    SearchFieldControl.component(songLibrary.songs, game.isGuessed, songListElement),
  )

def guessSong(song: Option[Song]): Unit =
  // Pre-guess
  val thisStageIndex = game.currentStage()

  // Guess
  val correct = song match {
    case Some(s) => game.guessStage(s)
    case None =>
      game.skipStage()
      false
  }

  // Post-guess
  finishedGame.set(correct || game.currentStage() == game.maxGuesses)
  guessSlotVars(thisStageIndex).set(GuessSlot(song, song.isEmpty, correct))

  game.loadStage()
  if correct then audioController.setSnippet(0, 500_000) // Play whole song

  game.playCurrentStage()

def skipButton(): HtmlElement =
  button(
    "Skip",
    cls := "grey-button",
    onClick --> { _ => guessSong(None) },
  )

def playButton(): HtmlElement =
  button("Play", onClick --> { _ => game.playCurrentStage() })

def guessElement(guessSlot: Var[GuessSlot]): HtmlElement =
  div(
    cls <-- guessSlot.signal.map { slot =>
      List("guess-row" -> true, "guess-box" -> true, "correct-guess" -> slot.correct)
    },
    children <-- guessSlot.signal.map(slot =>
      slot.song match {
        case Some(s) =>
          Seq(
            span(cls := "guess-title", s.title),
            span(cls := "guess-album", s.album.toString),
          )
        case None =>
          if slot.skipped then Seq(span(cls := "guess-title", "- Skipped -"))
          else Seq()
      },
    ),
  )

def songListElement(song: Song): HtmlElement =
  li(cls := "song", p(song.toString), onClick --> { _ => guessSong(Some(song)) })

case class GuessSlot(song: Option[Song], skipped: Boolean, correct: Boolean)

/*
val progressbar: Var[Float] = Var(0)

def progressBar(): HtmlElement =
  /*js.timers.setInterval(50) {
    progressbar.set((audio.seek() / audio.duration()).toFloat)
  }*/

  div(
    cls := "progressbar-container",
    div(cls := "progressbar", styleAttr <-- progressbar.signal.map(p => s"width: ${p * 100}%;")),
  )
 */
