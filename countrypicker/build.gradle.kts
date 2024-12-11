import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.9.20"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        publishLibraryVariants("release")
        publishLibraryVariantsGroupedByFlavor = true
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    withSourcesJar()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodel)
        }
    }
}

android {
    namespace = "com.wannacall.countrypicker"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        nativeDistributions {
            packageName = "com.wannacall.countrypicker"
            packageVersion = "1.0.0"
        }
    }
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("html-docs")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifact("dokkaHtmlJar") {
                classifier = "javadoc"
            }

            pom {
                name = "WannaCall Country Picker"
                description = "A country code picker for the Compose Multiplatform UI Framework"
                url = "https://github.com/WannaCall/wc-country-picker/"

                licenses {
                    license {
                        name = "GNU General Public License v3.0"
                        url = "https://github.com/WannaCall/wc-country-picker/blob/main/LICENSE"
                        distribution = "repo"
                    }
                }

                developers {
                    developer {
                        name = "WannaCall"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/WannaCall/wc-country-picker.git"
                    developerConnection = "scm:git:ssh://github.com/WannaCall/wc-country-picker.git"
                    url = "https://github.com/WannaCall/wc-country-picker/"
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("sonatypeUsername")?.toString() ?: System.getenv("SONATYPE_USERNAME")
                password = findProperty("sonatypePassword")?.toString() ?: System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}
