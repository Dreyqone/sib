import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.20-RC"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    id("io.gitlab.arturbosch.detekt") version "1.21.0-RC1"
}

group = "ru.netology"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
allproject {
  apply from: "$rootDir/detekt.gradle"
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
dependencies {
    classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0-RC1")
  }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-jwt:1.1.1.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.tika:tika-parsers:1.25")
    implementation("com.google.firebase:firebase-admin:7.0.1")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.4"
}
