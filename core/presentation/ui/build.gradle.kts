plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.artemissoftware.core.presentation.ui"
}

dependencies {

    implementation(libs.androidx.ui)

    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}