plugins {
    id("java")
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.graalvm.buildtools.native") version "0.9.28"
}

description = "OpenTelemetry Example for Spring native images"
val moduleName by extra { "io.opentelemetry.examples.native" }

dependencyManagement {
    imports {
        mavenBom("io.opentelemetry:opentelemetry-bom:1.32.0")
        mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom-alpha:1.32.0-alpha")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("com.h2database:h2")
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
}
