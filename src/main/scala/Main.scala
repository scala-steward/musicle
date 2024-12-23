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

val todayGame: Game = loadGameByDate("today")
val yesterdayGame: Game = Game(songLibrary.songs(0), songLibrary.songs, "yesterday", youtubeEmbed)

val game: Var[Game]            = Var(todayGame)
val gameControl: GameControl   = GameControl(game, youtubeEmbed)

def loadGameByDate(date: String): Game =
  Game(SongPicker.TodaySong(songLibrary), songLibrary.songs, date, youtubeEmbed)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("Musicle"),
    ),
    mainTag(
      gameControl.component(),
      button("Today", onClick --> { _ => game.set(todayGame); game.now().loadStage() }),
      button("Yesterday", onClick --> { _ => game.set(yesterdayGame); game.now().loadStage() }),
    ),
    footerTag(
      p("Created with <3 by Kresten"),
    ),
  )
