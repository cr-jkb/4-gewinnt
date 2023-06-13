import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class StressTests extends Simulation {

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

  setUp( //StressTests exceeding the capacity limit: increasing Active Users to 10000 in Steps of 100 in 60 seconds
    /*rScenario.inject(rampUsersPerSec(100).to(10000).during(60.seconds)),
    wScenario.inject(rampUsersPerSec(100).to(10000).during(60.seconds))*/
	wScenario.inject(rampUsersPerSec(100).to(500).during(60.seconds)),
	rScenario.inject(rampUsersPerSec(100).to(500).during(60.seconds))
  ) 
    .protocols(httpProtocol)
}
//293387&293387 Users broke Java Heap: java.lang.OutOfMemoryError: Java heap space; shutting down JVM since 'akka.jvm-exit-on-fatal-error' is enabled for ActorSystem[GatlingSystem]

/*
---- Restarting Users ----------------------------------------------------------
[#--------------------------                                               ]  1%
          waiting: 21074  / active: 11410  / done: 516
---- Leaving Users -------------------------------------------------------------
[#--------------------------                                               ]  1%
          waiting: 21101  / active: 11384  / done: 515
================================================================================

java.lang.OutOfMemoryError: Java heap space


---- Restarting Users ----------------------------------------------------------
[##---------------------------------------------                           ]  2%
          waiting: 6571   / active: 10908  / done: 521
---- Leaving Users -------------------------------------------------------------
[##---------------------------------------------                           ]  2%
          waiting: 6622   / active: 10858  / done: 520
================================================================================

java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid14368.hprof ...*/