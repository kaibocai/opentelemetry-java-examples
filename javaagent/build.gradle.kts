import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.17"
    id("io.spring.dependency-management") version "1.1.4"
}

description = "OpenTelemetry Example for Java Agent"
val moduleName by extra { "io.opentelemetry.examples.javagent" }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

val agent = configurations.create("agent")

dependencies {
    implementation("io.opentelemetry:opentelemetry-api")

    //spring modules
    implementation("org.springframework.boot:spring-boot-starter-web")

    agent("io.opentelemetry.javaagent:opentelemetry-javaagent:1.32.0")
}

val copyAgent = tasks.register<Copy>("copyAgent") {
    from(agent.singleFile)
    into(layout.buildDirectory.dir("agent"))
    rename("opentelemetry-javaagent-.*\\.jar", "opentelemetry-javaagent.jar")
}


tasks.named<BootJar>("bootJar") {
    dependsOn(copyAgent)

    archiveFileName = "app.jar"
}