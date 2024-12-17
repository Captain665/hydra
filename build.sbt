name := """hydra"""
organization := "com.hydra"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"

// Dependencies
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate.orm" % "hibernate-core" % "6.6.3.Final",
  "org.hibernate" % "hibernate-annotations" % "3.5.6-Final",
  "com.mysql" % "mysql-connector-j" % "9.1.0",
  "jakarta.persistence" % "jakarta.persistence-api" % "3.2.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.24.1",
  "io.jsonwebtoken" % "jjwt-api" % "0.12.6",
  "io.jsonwebtoken" % "jjwt-impl" % "0.12.6" % "runtime",
  "io.jsonwebtoken" % "jjwt-jackson" % "0.12.6" % "runtime",
  "org.mindrot" % "jbcrypt" % "0.4",
  "org.apache.commons" % "commons-lang3" % "3.17.0",
  "org.asynchttpclient" % "async-http-client" % "3.0.0",
  "org.redisson" % "redisson" % "3.21.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.14.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.14.3",
  "jakarta.validation" % "jakarta.validation-api" % "3.1.0",
  "org.glassfish" % "jakarta.el" % "4.0.2"
).map(_.exclude("javax.validation", "validation-api"))

libraryDependencies ++= Seq(
  "org.playframework" %% "play-java-jpa" % "3.0.5" exclude("javax.validation", "validation-api"),
  "javax.validation" % "validation-api" % "2.0.1.Final" excludeAll(),
  "org.hibernate" % "hibernate-validator" % "8.0.0.Final" exclude("javax.validation", "validation-api")
)

// Dependency overrides
dependencyOverrides += "jakarta.validation" % "jakarta.validation-api" % "3.1.0"







