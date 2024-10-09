plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}
val mapsApiKey: String? = project.findProperty("MAPS_API_KEY") as String?

android {
    namespace = "com.example.android_app_demo"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.example.android_app_demo"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders["mapsApiKey"] = mapsApiKey ?: ""

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.security.crypto)
    implementation(libs.google.firebase.messaging.ktx)
    implementation(libs.androidx.webkit)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.avatarview.coil)
    implementation(libs.coil.gif)
    implementation(libs.glide)
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.core.ktx.v180)
    implementation(libs.androidx.appcompat.v150)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-analytics:22.1.2")
    implementation("com.google.firebase:firebase-common-ktx:21.0.0")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")

}