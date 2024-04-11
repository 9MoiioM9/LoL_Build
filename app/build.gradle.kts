import com.android.build.gradle.internal.packaging.defaultExcludes

plugins {
    id("com.android.application")
}

android {
    namespace = "com.lol_build"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lol_build"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
/*
configurations.all {
    exclude("com.google.guava", "listenablefuture")
}

 */

dependencies {
    //Add Riot librairies from Riot API Librairies
    //implementation(files("libs/orianna-android-4.0.0-rc9-jar-with-dependencies.jar"))

    implementation("com.google.code.gson:gson:2.10")
    implementation("io.coil-kt:coil:2.6.0")
    implementation("org.jsoup:jsoup:1.14.3")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Management of the Database Room used
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
}
