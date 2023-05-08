plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.20"
    id("org.jetbrains.kotlin.plugin.spring") version "1.8.20"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.8.20"
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
    implementation("org.springframework.boot:spring-boot-starter-cache")

    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
    // implementation("io.springfox:springfox-swagger2:3.0.0")
    // https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui
    implementation("io.springfox:springfox-swagger-ui:3.0.0")


    // https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.5")
    //runtimeOnly("com.h2database:h2:2.1.210")

    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    implementation("org.flywaydb:flyway-mysql")
    implementation("org.flywaydb:flyway-core")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.6")

    // https://mvnrepository.com/artifact/org.flywaydb.flyway-test-extensions/flyway-test
    //testImplementation("org.flywaydb.flyway-test-extensions:flyway-test:9.5.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
