plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.newsapplication"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.newsapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Compose Navigation
    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.45")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.4.3")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")

    // Paging 3
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")

    // Room
    val roomVersion = "2.5.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    //Logging
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
}