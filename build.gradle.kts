import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.20"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("io.ktor:ktor-client-cio-jvm:2.3.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("io.ktor:ktor-client-auth:2.3.2")
    implementation("io.ktor:ktor-server-forwarded-header:2.3.2")
    implementation("io.ktor:ktor-server-default-headers:2.3.2")
    implementation ("io.ktor:ktor-client-core:2.3.2")
    implementation ("io.ktor:ktor-client-okhttp:2.3.2")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2") // for JVM platform
    implementation ("org.apache.poi:poi-ooxml:4.1.1")
    //required only for jdk 9 or above
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo1"
            packageVersion = "1.0.0"
        }
    }
}
