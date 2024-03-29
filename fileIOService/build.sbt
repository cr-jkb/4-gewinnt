val scala3Version = "3.2.2"

lazy val settings = Seq(
  scalaVersion := scala3Version,
  libraryDependencies ++= Seq(
    "com.github.sbt" % "junit-interface" % "0.13.2" % Test,
    "org.scalactic" %% "scalactic" % "3.2.10",
    "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  ),
  libraryDependencies +=
    "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
  libraryDependencies +=
    "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  libraryDependencies +=
    "com.typesafe.akka" %% "akka-http" % "10.5.1",
  libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
    .cross(CrossVersion.for3Use2_13),
  libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.5",
  /* libraryDependencies += ("com.google.inject" % "guice" % "5.1.0"), */
  libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.1.1"),
  libraryDependencies += ("com.google.inject.extensions" % "guice-assistedinject" % "5.1.0"),
  /*libraryDependencies += ("com.google.inject" % "guice" % "4.2.3"), // incompatible with  jdk17*/
  libraryDependencies += ("org.scala-lang.modules" %% "scala-xml" % "2.1.0"),
  libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.10.0-RC7"),
  libraryDependencies += ("com.typesafe.slick" %% "slick" % "3.3.3")
    .cross(CrossVersion.for3Use2_13),
  libraryDependencies += "org.postgresql" % "postgresql" % "42.6.0",
  libraryDependencies += ("org.mongodb.scala" %% "mongo-scala-driver" % "4.9.0")
    .cross(CrossVersion.for3Use2_13),
  jacocoReportSettings := JacocoReportSettings(
    "Jacoco Coverage Report",
    None,
    JacocoThresholds(),
    Seq(
      JacocoReportFormats.ScalaHTML,
      JacocoReportFormats.XML
    ), // note XML formatter
    "utf-8"
  ),
  jacocoCoverallsServiceName := "github-actions",
  jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
  jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
  jacocoCoverallsRepoToken := sys.env.get("REPO_TOKEN")
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "FileIO Service",
    version := "1",
    settings
  )
  .enablePlugins(JacocoCoverallsPlugin)
