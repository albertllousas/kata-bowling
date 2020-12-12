import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
    application
}

repositories {
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.14")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.14")
    testImplementation("org.amshove.kluent:kluent:1.35")

}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.freeCompilerArgs += arrayOf("-Xinline-classes")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xinline-classes")
    }
}

tasks.apply {
    test {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}
