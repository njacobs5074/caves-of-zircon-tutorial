import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val zirconVersion: String by project
val amethystVersion: String by project
val slf4jVersion: String by project
val junitVersion: String by project
val mockitoVersion: String by project
val assertjVersion: String by project

plugins {
  kotlin("jvm") version "1.7.20"
  id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
  mavenLocal()
  mavenCentral()
  jcenter()
  maven("https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
  implementation("org.slf4j:slf4j-api:$slf4jVersion")
  implementation("org.slf4j:slf4j-simple:$slf4jVersion")

  implementation("org.hexworks.zircon:zircon.jvm.swing:$zirconVersion")
  implementation("org.hexworks.zircon:zircon.core-jvm:$zirconVersion")
  implementation("org.hexworks.amethyst:amethyst.core-jvm:$amethystVersion")

  testImplementation("junit:junit:$junitVersion")
  testImplementation("org.mockito:mockito-all:$mockitoVersion")
  testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks {
  named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    archiveVersion.set("")
    mergeServiceFiles()
    manifest {
      attributes(mapOf("Main-Class" to "com.example.MainKt"))
    }
  }
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}

val jar by tasks.getting(Jar::class) {
  manifest {
    attributes["Main-Class"] = "com.example.MainKt"
  }
}


