ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "watermark",
    libraryDependencies ++= Seq(
      "com.sksamuel.scrimage"  % "scrimage-core"    % "4.0.24",
      "com.sksamuel.scrimage"  % "scrimage-filters" % "4.0.24",
      "com.sksamuel.scrimage" %% "scrimage-scala"   % "4.0.24",
    ),
  )
