plugins {
	java
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.jetbrains.kotlin.jvm") version "1.8.20"
	id ("org.jetbrains.kotlin.plugin.allopen") version "1.8.20"
	id ("org.jetbrains.kotlin.plugin.spring") version "1.8.20"
	id ("org.jetbrains.kotlin.plugin.jpa") version "1.8.20"
}

group = "com.demoprojekt"
version = "0.0.1-SNAPSHOT"
version = "19"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2:2.1.210")
	implementation("org.flywaydb:flyway-core")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation(kotlin("stdlib-jdk8"))
}


tasks.withType<Test> {
	useJUnitPlatform()
}
