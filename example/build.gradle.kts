plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven(url = "https://maven.pkg.github.com/hfhbd/*") {
        credentials {
            username = System.getProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
            password = System.getProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    explicitApi()

    sourceSets {
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(projects.bootstrapCompose)
                implementation(npm("bootstrap", "5.0.2"))
            }
        }
    }
}
