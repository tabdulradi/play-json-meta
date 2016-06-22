val buildSettings = Defaults.coreDefaultSettings ++ Seq(
  organization := "com.abdulradi",
  version := "0.1.0",
  scalaVersion := "2.11.8",
  resolvers += Resolver.sonatypeRepo("snapshots"),
  resolvers += Resolver.sonatypeRepo("releases"),
  scalacOptions ++= Seq(),
  addCompilerPlugin("org.scalamacros" % "paradise_2.11.8" % "3.0.0-M1")
)

lazy val macros = project.in(file("./macros")).settings(
  buildSettings ++ Seq(
    libraryDependencies ++= Seq(
      "org.scalameta" %% "scalameta" % "1.0.0",
      "com.typesafe.play" %% "play-json" % "2.5.4"
    ),
    name := "macros"
  )
)


lazy val root = project.in(file(".")).settings(
  buildSettings
).dependsOn(macros)
