import Game.*
import com.raquo.laminar.api.L.{ *, given }
import com.raquo.laminar.api.L.*
import io.bullet.borer.{ Codec, Decoder, Encoder }
import io.bullet.borer.derivation.MapBasedCodecs.*

import java.time.LocalDate
import java.time.format.DateTimeFormatter

case class WebsiteState(gameData: Map[LocalDate, Game]):
  def updateGame(date: LocalDate, value: Game): WebsiteState =
    copy(gameData = gameData.updated(date, value))

given localDateCodec: Codec[LocalDate] = Codec(
  Encoder[String].contramap(_.format(DateTimeFormatter.ISO_LOCAL_DATE)),
  Decoder[String].map(LocalDate.parse(_, DateTimeFormatter.ISO_LOCAL_DATE)),
)

given guessCodec: Codec[Guess]               = deriveCodec
given albumCodec: Codec[Album]               = deriveCodec
given authorCodec: Codec[Author]             = deriveCodec
given songCodec: Codec[Song]                 = deriveCodec
given gameTypeCodec: Codec[GameType]         = deriveCodec
given gameCodec: Codec[Game]                 = deriveCodec
given websiteStateCodec: Codec[WebsiteState] = deriveCodec
