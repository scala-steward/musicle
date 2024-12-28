import com.raquo.airstream.web.WebStorageVar
import com.raquo.laminar.api.L.{ *, given }
import io.bullet.borer.Json

import java.nio.charset.StandardCharsets
import scala.util.Success

object Persistence:
  def getWebsiteState: WebStorageVar[WebsiteState] =
    WebStorageVar
      .localStorage(key = "websiteState", syncOwner = Some(unsafeWindowOwner))
      .withCodec[WebsiteState](
        encode = foo => Json.encode(foo).toUtf8String,
        decode = str => Json.decode(str.getBytes(StandardCharsets.UTF_8)).to[WebsiteState].valueTry,
        default = Success(WebsiteState(Map.empty)),
      )
