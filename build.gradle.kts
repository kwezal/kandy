apply(plugin = "com.jfrog.bintray")
apply(plugin = "maven-publish")

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
        classpath("org.jetbrains.dokka:dokka-android-gradle-plugin:${Versions.dokka}")
    }
}

allprojects {
    group = Publishing.groupId
    version = Versions.kandy
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("assembleAllKandyModules") {
    group = "publishing"

    val modules = subprojects.filter { it.name.startsWith(Publishing.artifactPrefix) }

    modules.forEach {
        // Remove prefix
        val id = it.name.substring(Publishing.artifactPrefix.length)

        if (Module.byId(id)?.hasDebugVariant == true) {
            dependsOn("${it.name}:assembleDebug")
        }
        dependsOn("${it.name}:assembleRelease")
    }
}

tasks.register("publishAllKandyModules") {
    group = "publishing"

    dependsOn(tasks["assembleAllKandyModules"])
    tasks["assembleAllKandyModules"].finalizedBy(tasks["publishToMavenLocal"])
}
