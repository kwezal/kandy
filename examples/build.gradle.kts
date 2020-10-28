plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion = Versions.buildTools

    defaultConfig {
        applicationId = "com.kwezal.kandy"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.compileSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    implementation("androidx.core:core-ktx:${Versions.coreKtx}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")

    // Splitties
    implementation("com.louiscad.splitties:splitties-views-dsl:${Versions.splitties}")
    implementation("com.louiscad.splitties:splitties-views-dsl-appcompat:${Versions.splitties}")

    // Kandy dev
//    implementation(project(":kandylistviews"))
//    implementation(project(":kandydialogs"))

    // Kandy prod
    implementation("com.kwezal.kandy:listviews:${Versions.kandy}@aar")
            { isTransitive = true } // Includes RecyclerView dependency
    implementation("com.kwezal.kandy:dialogs:${Versions.kandy}@aar")
}
