plugins {
	`kotlin-jvm`
	`fabric-loom`
	shadowJar
}

group = "me.mrpingu"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	mavenLocal()
	
	maven("https://dl.bintray.com/kotlin/kotlin-dev/")
	maven("http://maven.fabricmc.net/")
}

dependencies {
	implementation(kotlin("stdlib-jdk8", kotlinVersion))
	implementation(kotlin("reflect", kotlinVersion))
	
	minecraft("com.mojang", "minecraft", minecraftVersion)
	mappings("net.fabricmc", "yarn", mappingsVersion)
	
	modImplementation("net.fabricmc", "fabric-loader", fabricLoaderVersion)
	modImplementation("net.fabricmc", "fabric-language-kotlin", fabricKotlinVersion)
	modImplementation("net.fabricmc.fabric-api", "fabric-api", fabricApiVersion)
}

tasks.compileKotlin {
	kotlinOptions {
		jvmTarget = "1.8"
		languageVersion = "1.4"
		
		suppressWarnings = true
		freeCompilerArgs += listOf("-Xinline-classes", "-Xuse-experimental=kotlin.Experimental")
	}
}

tasks.processResources {
	neverUpToDate
	
	eachFile {
		if (path == "fabric.mod.json") {
			replaceTokens(
				"mod.id" to project.name.toLowerCase(),
				"mod.version" to project.version,
				"mod.name" to project.name,
				"mod.description" to "${project.version} (built at $timestamp for Fabric $fabricApiVersion based on Minecraft $minecraftVersion)",
				"mod.author" to "MrPNG_",
				"mod.entrypoint" to "${project.group}.${project.name.toLowerCase()}.${project.name}"
			)
		}
	}
}

tasks.shadowJar {
	archiveClassifier.set("")
	archiveVersion.set("")
}
