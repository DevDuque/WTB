plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "br.team.wtb"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.team.wtb"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    flavorDimensions.add("version")
    productFlavors {

        create("paid") {
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"
            dimension = "version"
        }

        create("free") {
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    implementation(libs.jbcrypt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
}