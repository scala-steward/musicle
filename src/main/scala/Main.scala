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
    h1("Hello Musicle! V1.0"),
    ul(cls := "guess-container",
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
      li(guessElement()),
    ),
    playButton(),
    searchField(),
    songEmbed(audioSourceVar)
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

def guessElement(): HtmlElement =
  input(cls := List("guess", "guess-box"),
    readOnly := true,
  )

def searchField(): HtmlElement =
  val searchQueryVar = Var("")

  div(
    cls := "container",
    input(
      cls := List("guess-input", "guess-box"),
      typ := "text",
      placeholder := "Runaway...",
      inContext { thisNode =>
        onInput.mapTo(thisNode.ref.value) --> searchQueryVar
      }
    ),
    ul(cls := "searched-songs",
      children <-- searchQueryVar.signal.map { query =>
        query.trim match {
          case "" => Nil
          case _ => game.songLibrary.songs
            .filter(_.title.toLowerCase.contains(query.toLowerCase))
            .take(5)
            .map(song =>
              li(cls := "song",
                p(song.title),
                onClick --> { _ => ??? }
              )
            )
        }
      }
    )
  )
