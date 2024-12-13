name := """hydra"""
organization := "com.hydra"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.15"

libraryDependencies += guice

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"

libraryDependencies += javaJpa

//// https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
libraryDependencies += "org.hibernate.orm" % "hibernate-core" % "6.6.3.Final"
//
//// https://mvnrepository.com/artifact/org.hibernate/hibernate-annotations
libraryDependencies += "org.hibernate" % "hibernate-annotations" % "3.5.6-Final"

// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
libraryDependencies += "com.mysql" % "mysql-connector-j" % "9.1.0"

// https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api
libraryDependencies += "jakarta.persistence" % "jakarta.persistence-api" % "3.2.0"

// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.24.1"

// https://mvnrepository.com/artifact/org.playframework/play-java-jpa
libraryDependencies += "org.playframework" %% "play-java-jpa" % "3.0.5"

// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
libraryDependencies += "io.jsonwebtoken" % "jjwt-api" % "0.12.6"

// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
libraryDependencies += "io.jsonwebtoken" % "jjwt-impl" % "0.12.6" % "runtime"

// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
libraryDependencies += "io.jsonwebtoken" % "jjwt-jackson" % "0.12.6" % "runtime"

// https://mvnrepository.com/artifact/org.mindrot/jbcrypt
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.4"

// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.17.0"

// https://mvnrepository.com/artifact/org.asynchttpclient/async-http-client
libraryDependencies += "org.asynchttpclient" % "async-http-client" % "3.0.0"

libraryDependencies += "org.redisson" % "redisson" % "3.21.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.14.3"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.14.3"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.14.3"

//libraryDependencies += "jakarta.validation" % "jakarta.validation-api" % "3.1.0
//
//libraryDependencies += "org.hibernate.validator" % "hibernate-validator" % "7.0.2.Final"

//// https://mvnrepository.com/artifact/jakarta.el/jakarta.el-api
//libraryDependencies += "jakarta.el" % "jakarta.el-api" % "6.0.1"





