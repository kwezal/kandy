plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

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
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.compileSdk)

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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")

    // Splitties
    implementation("com.louiscad.splitties:splitties-views-dsl:${Versions.splitties}")
    implementation("com.louiscad.splitties:splitties-views-dsl-appcompat:${Versions.splitties}")

    // Kandy dev
    "localImplementation"(project(":kandydialogs"))
    "localImplementation"(project(":kandylistviews"))
    "localImplementation"(project(":kandylogs"))

    // Kandy prod
    "publishedImplementation"("com.kwezal.kandy:dialogs:${Versions.kandy}@aar")
    "publishedImplementation"("com.kwezal.kandy:listviews:${Versions.kandy}@aar")
            { isTransitive = true } // Includes RecyclerView dependency
    "publishedDebugImplementation"("com.kwezal.kandy:logs-debug:${Versions.kandy}@aar")
    "publishedReleaseImplementation"("com.kwezal.kandy:logs-release:${Versions.kandy}@aar")
}
