plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.miya_pm7_plus"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.miya_pm_7"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Compose BOM untuk konsistensi versi
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))

    implementation("androidx.navigation:navigation-compose:2.7.3")

    implementation("androidx.compose.material3:material3:1.2.1")

    // Material3 & Material Components
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material:material-icons-extended")

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.9.0")

    // Core KTX
    implementation("androidx.core:core-ktx:1.12.0")

    // Coil untuk Compose
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Debug tooling
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
