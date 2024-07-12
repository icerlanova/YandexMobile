plugins {
    id("java")
    id("io.qameta.allure") version "2.9.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val allureVersion = "2.19.0"
val junitVersion = "5.8.2"
val slf4jVersion = "1.7.32"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("com.github.romankh3:image-comparison:4.4.0")
    implementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("com.codeborne:selenide:5.25.0")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("io.appium:java-client:7.6.0")
    implementation("org.aeonbits.owner:owner:1.0.12")
    implementation("io.rest-assured:rest-assured:4.4.0")
    implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    runtimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("failed")
    }
}
