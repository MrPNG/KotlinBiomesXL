package me.mrpingu.kotlinbiomesxl.world.biome

import me.mrpingu.kotlinbiomesxl.ModRegistry
import me.mrpingu.kotlinbiomesxl.world.gen.feature.SnowTopLayerFeature
import me.mrpingu.kotlinbiomesxl.world.gen.surfacebuilder.GlacierSurfaceBuilder
import net.fabricmc.fabric.api.biomes.v1.*
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes.*
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry.*
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig

object ModBiomes: ModRegistry<Biome>(BIOME) {
	
	fun registerAll() {
		registerSurfaceBuilders(
			"glacier" to GlacierSurfaceBuilder
		)
		
		registerFeatures(
			"snow_top_layer" to SnowTopLayerFeature
		)
		
		register(
			"glacier" to GlacierBiome,
			"glacier_shore" to GlacierShoreBiome,
			"meadow" to MeadowBiome
		)
		
		addContinentalBiome(GlacierBiome, SNOWY, 1000.0)
		addContinentalBiome(MeadowBiome, TEMPERATE, 1000.0)
		
		addShoreBiome(GlacierBiome, GlacierShoreBiome, 65536.0)
		
		setRiverBiome(GlacierBiome, null)
		setRiverBiome(GlacierShoreBiome, null)
		
		addSpawnBiomes(
			GlacierBiome,
			MeadowBiome
		)
	}
	
	private fun addSpawnBiomes(vararg biomes: Biome) = biomes.forEach(FabricBiomes::addSpawnBiome)
	
	private fun registerFeatures(vararg features: Pair<String, Feature<out FeatureConfig>>) =
		features.forEach { (name, feature) -> register(FEATURE, Identifier("kotlinbiomesxl", name), feature) }
	
	private fun registerSurfaceBuilders(vararg surfaceBuilders: Pair<String, SurfaceBuilder<out SurfaceConfig>>) =
		surfaceBuilders.forEach { (name, surfaceBuilder) -> register(SURFACE_BUILDER, Identifier("kotlinbiomesxl", name), surfaceBuilder) }
}
