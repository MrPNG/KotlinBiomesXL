import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec

inline val PluginDependenciesSpec.`kotlin-jvm` get() = kotlin("jvm") version kotlinVersion
inline val PluginDependenciesSpec.`fabric-loom` get() = id("fabric-loom") version "0.2.7-SNAPSHOT"
inline val PluginDependenciesSpec.shadowJar get() = id("com.github.johnrengelman.shadow") version shadowJarVersion
