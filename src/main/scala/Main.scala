import Audio.{ AudioController, YoutubeEmbed }
import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom
import Game.*

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
  game.now().loadStage()

val songLibrary: SongLibrary   = SongLibrary(SongLibrary.loadSongs())
val youtubeEmbed: YoutubeEmbed = YoutubeEmbed()
val game: Var[Game]            = Var(loadGameByDate("today"))
val gameControl: GameControl   = GameControl(game, youtubeEmbed)

def loadGameByDate(date: String): Game =
  Game(SongPicker.TodaySong(songLibrary), songLibrary.songs, youtubeEmbed)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("Musicle"),
    ),
    mainTag(
      youtubeEmbed.component(),
      gameControl.component(),
    ),
    footerTag(
      p("Created with <3 by Kresten"),
    ),
  )
