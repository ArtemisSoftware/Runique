plugins {
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "com.artemissoftware.run.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.run.domain)

    implementation(libs.timber)
}