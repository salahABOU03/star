plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.star"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.star"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("com.android.support:recyclerview-v7:28.0.0")
    //circle image
    implementation ("de.hdodenhof:circleimageview:3.0.1")
    //glide
    implementation ("com.github.bumptech.glide:glide:4.8.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.8.0")
}