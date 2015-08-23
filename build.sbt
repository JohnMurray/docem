name := """docem"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

Revolver.settings

routesGenerator := InjectedRoutesGenerator

val jacksonVersion = "2.5.3"

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core"        %  "jackson-core"             % jacksonVersion,
  "com.fasterxml.jackson.core"        %  "jackson-annotations"      % jacksonVersion,
  "com.fasterxml.jackson.core"        %  "jackson-databind"         % jacksonVersion,
  "com.fasterxml.jackson.dataformat"  %  "jackson-dataformat-yaml"  % jacksonVersion,
  "org.scaldi"                       %%  "scaldi-play"              % "0.5.8",
  "org.specs2"                       %%  "specs2-core"              % "3.6.4"            % "test",
  "org.specs2"                       %%  "specs2-junit"             % "3.6.4"            % "test"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
