plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Version.compileSdk)
    buildToolsVersion = Version.buildTools

    signingConfigs {
        create("config") {
            keyAlias = "key"
            keyPassword = file("../key-password.txt").readText()
            storeFile = file("../keystore.jks")
            storePassword = file("../store-password.txt").readText()
        }
    }

    defaultConfig {
        applicationId = "com.kwezal.kandy"
        minSdkVersion(Version.minSdk)
        targetSdkVersion(Version.compileSdk)

        signingConfig = signingConfigs.getByName("config")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions("librarySource")
    productFlavors {
        create("local") {
            setDimension("librarySource")
        }

        create("published") {
            setDimension("librarySource")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

configurations {
    create("publishedDebugImplementation")
    create("publishedReleaseImplementation")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}")
    implementation("androidx.core:core-ktx:${Version.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Version.appCompat}")

    // Splitties
    implementation("com.louiscad.splitties:splitties-views-dsl:${Version.splitties}")
    implementation("com.louiscad.splitties:splitties-views-dsl-appcompat:${Version.splitties}")

    // Kandy dev
    "localImplementation"(project(":kandydialogs"))
    "localImplementation"(project(":kandylistviews"))
    "localImplementation"(project(":kandylogs"))

    // Kandy prod
    "publishedImplementation"("com.kwezal.kandy:dialogs:${Version.kandy}@aar")
    "publishedImplementation"("com.kwezal.kandy:listviews:${Version.kandy}@aar")
            { isTransitive = true } // Includes RecyclerView dependency
    "publishedDebugImplementation"("com.kwezal.kandy:logs-debug:${Version.kandy}@aar")
    "publishedReleaseImplementation"("com.kwezal.kandy:logs-release:${Version.kandy}@aar")
}
