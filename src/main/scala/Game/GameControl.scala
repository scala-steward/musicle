package Game

import Audio.YoutubeEmbed
import Controls.SearchFieldControl
import com.raquo.laminar.api.L.{*, given}
import Game.*
import _root_.Game.GameControl.guessesToGuessSlots

class GameControl(val game: Var[Game]):
  val finishedGame: Var[Boolean] = Var(false)

  def component(): HtmlElement =
    // Update guessSlotVars whenever the game changes
    mainTag(
      YoutubeEmbed.component(game.now().actualSong.sourcePath, finishedGame),
      h1("AUROLE: V1.0"),
      div(child <-- game.signal.map(currentGame =>
          ul(cls := "guess-container",
            // game2.guesses.signal.map(guesses => guessesToGuessSlots(game2, guesses))
            children <-- currentGame.guesses.signal.map(guessList =>
              guessesToGuessSlots(currentGame, guessList).map(guess => li(guessElement(Var(guess)))
              )
            )
          )
        )
      ),
      //progressBar(),
      skipButton(),
      playButton(),
      SearchFieldControl.component(game.now().songs, game.now().isGuessed, songListElement),
    )

  private def guessSong(song: Option[Song]): Unit =
    // Pre-guess
    val thisStageIndex = game.now().currentStage()

    // Guess
    val correct = song match {
      case Some(s) => game.now().guessStage(s)
      case None =>
        game.now().skipStage()
        false
    }

    // Post-guess
    finishedGame.set(correct || game.now().currentStage() == game.now().maxGuesses)

    game.now().loadStage()
    if correct then game.now().playFullSong()

    game.now().playCurrentStage()

  private def skipButton(): HtmlElement =
    button(
      "Skip",
      cls := "grey-button",
      onClick --> { _ => guessSong(None) },
    )

  private def playButton(): HtmlElement =
    button("Play", onClick --> { _ => game.now().playCurrentStage() })

  private def guessElement(guessSlot: Var[GuessSlot]): HtmlElement =
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

  private def songListElement(song: Song): HtmlElement =
    li(cls := "song", p(song.toString), onClick --> { _ => guessSong(Some(song)) })

object GameControl:
  def guessesToGuessSlots(game: Game, guesses: List[Guess]): List[GuessSlot] =
    guesses.map(guess =>
      GuessSlot(guess.song, guess.song.isEmpty, guess.song.getOrElse(false) == game.actualSong)
    ).padTo(game.maxGuesses, GuessSlot(None, false, false))

case class GuessSlot(song: Option[Song], skipped: Boolean, correct: Boolean)
