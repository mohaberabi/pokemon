plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.hilt)
    alias(libs.plugins.safe.args)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.mohaberabi.pokemon"
    compileSdk = 35


    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    defaultConfig {
        applicationId = "com.mohaberabi.pokemon"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.hilt)
    implementation(libs.androidx.palette.ktx)
    ksp(libs.hiltCompiler)
    ksp(libs.room.compiler)
    implementation("androidx.room:room-ktx:2.6.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.lifecycleViewModel)
    implementation(libs.lifecycleRuntime)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation(libs.androidx.fragment.ktx.v161)
    implementation(libs.navUI)
    implementation(libs.navFragment)
    implementation(libs.coil)

}
room {
    schemaDirectory("$projectDir/schemas")
}
