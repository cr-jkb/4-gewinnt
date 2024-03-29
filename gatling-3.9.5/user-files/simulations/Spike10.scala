import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SpikeTests extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://localhost:8080/fileio")
    .inferHtmlResources()
    .disableAutoReferer
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader(
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"
    )

  private val additionalHeaders = Map(
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Proxy-Connection" -> "keep-alive"
  )

  private val rScenario = scenario("Restarting Users")
    .exec(
      http("l_run_1 (restart)")
        .get("/load")
        .headers(additionalHeaders)
    )
    .pause(10)
    .exec(
      http("l_run_2 (restart)")
        .get("/load")
        .headers(additionalHeaders)
    )
    .pause(10)
    .exec(
      http("l_run_3 (restart)")
        .get("/load")
        .headers(additionalHeaders)
    )

  private val wScenario = scenario("Leaving Users")
    .exec(
      http("w_run_1 (leave)")
        .post("/save")
        .body(ElFileBody("game.json"))
        .headers(additionalHeaders)
    )
    .pause(10)
    .exec(
      http("w_run_2 (leave)")
        .post("/save")
        .body(ElFileBody("game.json"))
        .headers(additionalHeaders)
    )
    .pause(10)
    .exec(
      http("w_run_3 (leave)")
        .post("/save")
        .body(ElFileBody("game.json"))
        .headers(additionalHeaders)
    )

  setUp(
    wScenario.inject(atOnceUsers(1000)),
    rScenario.inject(atOnceUsers(500))
  )
    .protocols(httpProtocol)
}
