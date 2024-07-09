plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.artemissoftware.core.presentation.designsystem"
}

dependencies {


    implementation(libs.androidx.ui.graphics)
    api(libs.androidx.material3)

    //
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}