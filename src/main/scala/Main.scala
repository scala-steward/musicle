import Audio.{ AudioController, YoutubeEmbed }
import com.raquo.laminar.api.L.{ *, given }
import org.scalajs.dom
import Game.*

import java.time.*
import scala.collection.mutable

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())
  setGameDate(LocalDate.now())

// Game-dependencies
val gameByDate: mutable.Map[LocalDate, Game] = scala.collection.mutable.Map()
val youtubeEmbed: YoutubeEmbed               = YoutubeEmbed()
val songLibrary: SongLibrary                 = SongLibrary(SongLibrary.loadSongs())

// Game
val game: Var[Game]          = Var(loadGameByDate(LocalDate.now()))
val gameControl: GameControl = GameControl(game, youtubeEmbed)

// Other
val currentDate: Var[LocalDate] = Var(LocalDate.now())

def loadGameByDate(date: LocalDate): Game =
  if gameByDate.contains(date) then gameByDate(date)
  else
    val newGame = Game(SongPicker.TodaySong(songLibrary), songLibrary.songs, youtubeEmbed)
    gameByDate.update(date, newGame)
    newGame

def setGameDate(date: LocalDate): Unit =
  currentDate.set(date)
  game.set(loadGameByDate(date))
  gameControl.reload()
  dom.console.log(gameByDate)

def appElement(): HtmlElement =
  div(
    headerTag(
      h1("AURORDLE"),
      h3(text <-- currentDate.signal.map(date => date.toString)),
    ),
    mainTag(
      gameControl.component(),
      button("<-", onClick --> { _ => setGameDate(currentDate.now().minusDays(1)) }),
      button("->", onClick --> { _ => setGameDate(currentDate.now().plusDays(1)) }),
    ),
    footerTag(
      p("Created with <3 by Kresten"),
    ),
  )
