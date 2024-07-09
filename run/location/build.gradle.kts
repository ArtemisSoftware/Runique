plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.artemissoftware.run.location"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.run.domain)

    implementation(libs.androidx.core.ktx)
    //
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}