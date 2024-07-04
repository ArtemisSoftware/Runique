plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.artemissoftware.core.data"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.database)
}