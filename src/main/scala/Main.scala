import Audio.{ AudioController, YoutubeEmbed }
import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom
import Game.*

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
  game.now().loadStage()

val songLibrary: SongLibrary         = SongLibrary(SongLibrary.loadSongs())
val game: Var[Game]                  = Var(loadGameByDate("today"))
val gameControl: GameControl         = GameControl(game)
val audioController: AudioController = YoutubeEmbed

def loadGameByDate(date: String): Game =
  Game(SongPicker.TodaySong(songLibrary), songLibrary.songs, audioController)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("Musicle"),
    ),
    gameControl.component(),
    footerTag(
      p("Created with <3 by Kresten"),
    ),
  )
