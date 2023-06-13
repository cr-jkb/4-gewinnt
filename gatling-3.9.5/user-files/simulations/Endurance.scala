import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EnduranceTests extends Simulation {

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
    .repeat(100, "n") {
      exec(
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
    }

  private val wScenario = scenario("Leaving Users")
    .repeat(100, "n") {
      exec(
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
    }

  setUp( //Endurance of 100/50 Users at once each 10 Seconds * 100 times  (10.000 Users on 3 runs is 30.000 dbReads // 5.000 & 15.000 dbWrites = TOTAL 45k)
    rScenario.inject(atOnceUsers(100)),
    wScenario.inject(atOnceUsers(50))
  )
    .protocols(httpProtocol)
}
// Example of first run: 11:48 for (~1720s = 28,7min) finished in 2003s at 12:21 (33,4min)