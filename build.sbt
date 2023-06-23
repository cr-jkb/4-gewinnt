val scala3Version = "3.2.2"

lazy val settings = Seq(
  scalaVersion := scala3Version,
  // jacocoCoverallsServiceName := "github-actions",
  // jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
  // jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
  // jacocoCoverallsRepoToken := sys.env.get("REPO_TOKEN")
)

lazy val root = project
  .in(file("."))
  .dependsOn()
  .settings(
    name := "4-Gewinnt + Microservice (CJ, YH)",
    version := "1",
    settings
  )
  //.enablePlugins(JacocoCoverallsPlugin)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "4Gewinnt Core"
    settings
  )

lazy val fileIOService = project
  .in(file("fileIOService"))
  .settings(
    name := "FileIO Microservice",
    settings
  )
