name := "calculation-service"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

sbtVersion := "0.13.9"

enablePlugins(TomcatPlugin)

val akkaVersion = "2.4.8"
val springBootVersion = "1.3.6.RELEASE"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-web" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-tomcat" % springBootVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "org.webjars" % "bootstrap" % "3.1.1",
//  "org.thymeleaf" % "thymeleaf-spring4" % "2.1.2.RELEASE",
  "org.hibernate" % "hibernate-validator" % "5.0.2.Final",
  "nz.net.ultraq.thymeleaf" % "thymeleaf-layout-dialect" % "1.2.1"
)

val tomcatVersion = "8.0.30"

libraryDependencies ++= Seq(
  "org.apache.tomcat.embed" % "tomcat-embed-core"         % tomcatVersion % "container",
  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % tomcatVersion % "container",
  "org.apache.tomcat.embed" % "tomcat-embed-jasper"       % tomcatVersion % "container"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "2.2.6" % Test
)


    
