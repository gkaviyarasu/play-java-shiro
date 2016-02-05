
name := "play-java-shiro"

version := "1.1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  evolutions,
  jdbc,
  "com.typesafe.play" %% "anorm"      % "2.4.0",
  "org.mindrot"        % "jbcrypt"    % "0.3m",
  "org.apache.shiro"   % "shiro-core" % "1.2.4",
  "mysql" % "mysql-connector-java" % "5.1.37"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)
routesGenerator := InjectedRoutesGenerator