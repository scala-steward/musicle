import com.raquo.laminar.api.L.{*, given}
import com.raquo.laminar.tags.CustomHtmlTag
import org.scalajs.dom

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

val audioSourceVar: Var[String] = Var("")
val game: Game = Game()

def appElement(): HtmlElement =
  div(
    div(cls := "header",
      h1("Musicle")
    ),
    gameComponent(),
  )

def gameComponent(): HtmlElement =
  div(
    h1("Hello Musicle!"),
    h2(msg),
    ul(
      li(guessInput()),
      li(guessInput()),
      li(guessInput()),
      li(guessInput()),
      li(guessInput()),
    ),
    playButton(),
    songEmbed(audioSourceVar)
  )

def guessInput(): HtmlElement =
  input(cls := "guess-input",
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
    onClick --> { _ => audioSourceVar.set(game.songLibrary.songs.head.sourcePath)}
  )


def msg = "I was compiled by Scala 3. :)"
