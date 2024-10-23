name := """hydra"""
organization := "com.hydra"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"

libraryDependencies += javaJpa

// https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
libraryDependencies += "org.hibernate.orm" % "hibernate-core" % "6.6.1.Final"


// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
libraryDependencies += "com.mysql" % "mysql-connector-j" % "9.1.0"


// https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
libraryDependencies += "jakarta.persistence" % "jakarta.persistence-api" % "3.2.0"

// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.24.1"

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"

// https://mvnrepository.com/artifact/org.playframework/play-java-jpa
libraryDependencies += "org.playframework" %% "play-java-jpa" % "3.0.5"











