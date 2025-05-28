plugins {
    id("java")
    application
}

group = "dev.nheggoe"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

application {
    // Define the main class for the application.
    mainClass = "dev.nheggoe.tictactoe.Launcher"
}

defaultTasks("run")