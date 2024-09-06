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

dependencies {

    //Data Tdt
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.jsoup:jsoup:1.14.3")

    //Management of Images
    implementation("io.coil-kt:coil:2.6.0")

    //Management of the Database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //WebView
    implementation("androidx.webkit:webkit:1.10.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
