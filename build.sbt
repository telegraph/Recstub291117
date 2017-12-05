import Dependencies._

resolvers += "mvn-artifacts" atS3 "s3://mvn-artifacts/release"

lazy val root = (project in file(".")).
  settings( 
    inThisBuild(List(
      organization := "com.telegraph.stub.Recommendation",
      scalaVersion := "2.11.8",
      version      := "0.1.19"
    )),
    name := "RecommendationsStub"
,
    ServiceDependencies
  )

mainClass := Some("com.telegraph.stub.Recommendation.MyStub")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}


publishTo := {
  if( isSnapshot.value ){
    Some("mvn-tmg-publisher" atS3 "s3://s3-eu-west-1.amazonaws.com/mvn-artifacts/snapshot")
  }else{
    Some("mvn-tmg-publisher" atS3 "s3://s3-eu-west-1.amazonaws.com/mvn-artifacts/release")
  }
}
