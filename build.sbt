name := "calculation-service"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream" % "2.4.4",

  "org.springframework.boot" % "spring-boot-starter" % "1.3.5.RELEASE"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-testkit" % "2.4.4" % Test
)


    
