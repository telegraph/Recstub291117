import sbt.Keys._
import sbt._

object Dependencies {

  val ServiceDependencies: Seq[Setting[_]] = Seq(
    libraryDependencies ++= Seq(
      "org.scalatest"          %% "scalatest"     % "3.0.3",
      "com.github.tomakehurst" %  "wiremock"      % "2.7.1",
      "uk.co.telegraph.qe"     %% "smartstub"     % "0.9.0"
    )
  )
}
