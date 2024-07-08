plugins {
    alias(libs.plugins.runique.android.library)
    alias(libs.plugins.runique.android.room)
}

android {
    namespace = "com.artemissoftware.core.database"
}

dependencies {
    implementation(projects.core.domain)
}