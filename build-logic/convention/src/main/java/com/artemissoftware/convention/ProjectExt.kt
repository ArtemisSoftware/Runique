package com.artemissoftware.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.util.Properties

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.getLocalProperty(propertyName: String): String {
    val localPropertiesFile = File(rootDir, "local.properties")

    if (localPropertiesFile.exists()) {
        val properties = Properties().apply { load(localPropertiesFile.inputStream()) }
        return properties.getProperty(propertyName, "")
    }
    return ""
}
