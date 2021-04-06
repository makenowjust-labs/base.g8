Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / organization := "$organization$"

ThisBuild / scalaVersion := "$scalaVersion$"
ThisBuild / scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-deprecation",
  "-Wunused"
)

// Scalafix config:
ThisBuild / scalafixScalaBinaryVersion := "2.13"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"
ThisBuild / scalafixDependencies += "com.github.vovapolu" %% "scaluzzi" % "0.1.18"

lazy val root = project
  .in(file("."))
  .settings(
    name := "$name$",
    console / initialCommands := """
      |import $package$._
      """.stripMargin,
    Compile / console / scalacOptions -= "-Wunused",
    Test / console / scalacOptions -= "-Wunused",
    // Set URL mapping of scala standard API for Scaladoc.
    apiMappings ++= scalaInstance.value.libraryJars
      .filter(file => file.getName.startsWith("scala-library") && file.getName.endsWith(".jar"))
      .map(_ -> url(s"http://www.scala-lang.org/api/\${scalaVersion.value}/"))
      .toMap,
    // Settings for test:
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.23" % Test,
    testFrameworks += new TestFramework("munit.Framework")
  )

