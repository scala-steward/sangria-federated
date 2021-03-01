// About
ThisBuild / organization := "org.sangria-graphql"
ThisBuild / startYear := Some(2021)
ThisBuild / organizationHomepage := Some(url("https://github.com/sangria-graphql"))
ThisBuild / developers := List(
  Developer("xsoufiane", "Soufiane Maguerra", "", url("https://github.com/xsoufiane")),
  Developer("yanns", "Yann Simon", "", url("https://github.com/yanns")))
ThisBuild / scmInfo := Some(
  ScmInfo(
    browseUrl = url("https://github.com/sangria-graphql/sangria-federated.git"),
    connection = "scm:git:git@github.com:sangria-graphql/sangria-federated.git"
  ))

// Build
ThisBuild / crossScalaVersions := Seq("2.12.13", "2.13.5")
ThisBuild / scalaVersion := crossScalaVersions.value.last
ThisBuild / githubWorkflowPublishTargetBranches := List()
ThisBuild / githubWorkflowBuildPreamble ++= List(
  WorkflowStep.Sbt(List("scalafmtCheckAll"), name = Some("Check formatting"))
)

// scalacOptions, javacOptions
ThisBuild / scalacOptions ++= Seq("-deprecation", "-feature")
ThisBuild / scalacOptions += "-target:jvm-1.8"
ThisBuild / javacOptions ++= Seq("-source", "8", "-target", "8")

// Publishing
lazy val publishSettings = Seq(
  releaseCrossBuild := true,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  releaseVcsSign := true,
  publishMavenStyle := true,
  publishArtifact in Test := false,
  pomIncludeRepository := (_ => false),
  publishTo := Some(
    if (isSnapshot.value)
      "snapshots".at("https://oss.sonatype.org/content/repositories/snapshots")
    else
      "releases".at("https://oss.sonatype.org/service/local/staging/deploy/maven2"))
)

// sangria-federated
lazy val root = (project in file("."))
  .withId("sangria-federated")
  .settings(publishSettings)
  .settings(
    name := "sangria-federated",
    description := "Sangria federated",
    homepage := Some(url("http://sangria-graphql.org")),
    licenses := Seq(
      "Apache License, ASL Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    libraryDependencies ++= Seq(
      "org.sangria-graphql" %% "sangria" % "2.1.0",
      "org.scalatest" %% "scalatest" % "3.2.2" % Test,
      "io.circe" %% "circe-generic" % "0.13.0" % Test,
      "io.circe" %% "circe-parser" % "0.13.0" % Test,
      "org.sangria-graphql" %% "sangria-circe" % "1.3.1" % Test
    )
  )

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// Example
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

val serviceDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "io.circe" %% "circe-generic" % "0.13.0"
)

// GraphQL Services
lazy val review = (project in file("example/review"))
  .dependsOn(root, common)
  .settings(
    publish / skip := true,
    libraryDependencies ++= serviceDependencies
  )

lazy val state = (project in file("example/state"))
  .dependsOn(root, common)
  .settings(
    publish / skip := true,
    libraryDependencies ++= serviceDependencies
  )

// libs
lazy val common = (project in file("example/common")).settings(
  publish / skip := true,
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "2.2.0",
    "org.http4s" %% "http4s-blaze-server" % "0.21.8",
    "org.http4s" %% "http4s-circe" % "0.21.8",
    "org.http4s" %% "http4s-dsl" % "0.21.8",
    "io.circe" %% "circe-optics" % "0.13.0",
    "org.sangria-graphql" %% "sangria" % "2.1.0",
    "org.sangria-graphql" %% "sangria-circe" % "1.3.1"
  )
)
