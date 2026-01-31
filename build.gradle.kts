@file:Suppress("DEPRECATION")


// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra.apply {
        set("lifecycle_version", "2.6.2")
        set("room_version", "2.6.1")
    }

}



plugins {
    id("com.android.application") version "8.6.0" apply false
    id ("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id ("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("com.google.firebase.crashlytics")  version "2.9.5" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false



}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
