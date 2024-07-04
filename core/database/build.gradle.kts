plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.artemissoftware.core.database"
}

dependencies {
    implementation(projects.core.domain)
}