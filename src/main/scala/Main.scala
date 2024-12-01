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
    headerTag(
      h1("Musicle")
    ),
    gameComponent(),
    footerTag(
      p("Created with <3 by Kresten")
    ),
  )

def gameComponent(): HtmlElement =
  mainTag(
    h1("Hello Musicle!"),
    h2(msg),
    searchField(),
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

val animals = List(
  "Cat", "Dog", "Elephant", "Fish", "Gorilla",
  "Monkey", "Turtle", "Whale", "Alligator",
  "Donkey", "Horse"
)

val searchQueryVar = Var("")

def searchField(): HtmlElement =
  div(
    cls := "container",
    input(
      cls := "guess-input",
      typ := "text",
      placeholder := "Runaway...",
      inContext { thisNode =>
        onInput.mapTo(thisNode.ref.value) --> searchQueryVar
      }
    ),
    ul(cls := "searched-songs",
      children <-- searchQueryVar.signal.map { query =>
        game.songLibrary.songs
          .filter(_.title.toLowerCase.contains(query.toLowerCase))
          .map(animal => li(cls := "animals", h2(animal.title)))
      }
    )
  )


def msg = "I was compiled by Scala 3. :)"
