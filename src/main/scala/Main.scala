import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom

@main def hello(): Unit =
  // Laminar initialization
  renderOnDomContentLoaded(dom.document.querySelector("#app"), appElement())

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
    )
  )

def guessInput(): HtmlElement =
  input(cls := "guess-input",
  )

def msg = "I was compiled by Scala 3. :)"
