package me.mrpingu.kotlinbiomesxl.world.biome

import me.mrpingu.kotlinbiomesxl.world.gen.feature.SnowTopLayerFeature
import me.mrpingu.kotlinbiomesxl.world.gen.surfacebuilder.GlacierSurfaceBuilder
import net.minecraft.block.Blocks.*
import net.minecraft.entity.EntityType.*
import net.minecraft.world.biome.Biome.Category.*
import net.minecraft.world.biome.DefaultBiomeFeatures.*
import net.minecraft.world.gen.GenerationStep.Feature.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.Feature.*
import net.minecraft.world.gen.feature.FeatureConfig.*
import net.minecraft.world.gen.feature.MineshaftFeature.*
import net.minecraft.world.gen.feature.MineshaftFeature.Type.*
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig

object GlacierShoreBiome: ModBiome(
	Settings()
		.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, TernarySurfaceConfig(ICE.defaultState, STONE.defaultState, GRAVEL.defaultState))
		.precipitation(Precipitation.SNOW)
		.category(ICY)
		.depth(0.5f)
		.scale(0.3f)
		.temperature(0f)
		.downfall(0.5f)
		.waterColor(0x3f76e4)
		.waterFogColor(0x050533)
		.parent(null)
) {
	
	init {
		addFeature(TOP_LAYER_MODIFICATION, SnowTopLayerFeature.configure(DEFAULT))
		
		addLandCarvers(this)
		addDefaultDisks(this)
		addDefaultOres(this)
		addMineables(this)
		addDungeons(this)
		
		addStructureFeature(MINESHAFT.configure(MineshaftFeatureConfig(0.004, NORMAL)))
		addStructureFeature(BURIED_TREASURE.configure(BuriedTreasureFeatureConfig(0.01f)))
		addStructureFeature(SHIPWRECK.configure(ShipwreckFeatureConfig(true)))
		
		addAmbient(BAT, 10, 8, 8)
		
		addMonster(CREEPER, 100, 4, 4)
		addMonster(ENDERMAN, 10, 1, 4)
		addMonster(SKELETON, 20, 4, 4)
		addMonster(SLIME, 100, 4, 4)
		addMonster(SPIDER, 100, 4, 4)
		addMonster(STRAY, 80, 4, 4)
		addMonster(WITCH, 5, 1, 1)
		addMonster(ZOMBIE, 100, 4, 4)
	}
}
