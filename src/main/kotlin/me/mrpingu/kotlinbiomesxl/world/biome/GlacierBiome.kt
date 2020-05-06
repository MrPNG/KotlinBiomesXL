package me.mrpingu.kotlinbiomesxl.world.biome

import me.mrpingu.kotlinbiomesxl.entity.ModEntities.PENGUIN
import me.mrpingu.kotlinbiomesxl.world.gen.feature.SnowTopLayerFeature
import me.mrpingu.kotlinbiomesxl.world.gen.surfacebuilder.GlacierSurfaceBuilder
import net.minecraft.block.Blocks.*
import net.minecraft.entity.EntityType.*
import net.minecraft.world.biome.Biome.Category.*
import net.minecraft.world.biome.DefaultBiomeFeatures.*
import net.minecraft.world.gen.GenerationStep.Feature.*
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig
import net.minecraft.world.gen.decorator.Decorator.*
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.FeatureConfig.*
import net.minecraft.world.gen.feature.MineshaftFeature.*
import net.minecraft.world.gen.feature.SingleStateFeatureConfig
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig

object GlacierBiome: ModBiome(
	Settings()
		.configureSurfaceBuilder(GlacierSurfaceBuilder, TernarySurfaceConfig(SNOW_BLOCK.defaultState, STONE.defaultState, GRAVEL.defaultState))
		.precipitation(Precipitation.SNOW)
		.category(ICY)
		.depth(1.25f)
		.scale(0.4f)
		.temperature(0f)
		.downfall(0.5f)
		.waterColor(0x3f76e4)
		.waterFogColor(0x050533)
		.parent(null)
) {
	
	init {
		addFeature(TOP_LAYER_MODIFICATION, SnowTopLayerFeature.configure(DEFAULT))
		addFeature(LOCAL_MODIFICATIONS, LAKE.configure(SingleStateFeatureConfig(WATER.defaultState)).createDecoratedFeature(WATER_LAKE.configure(ChanceDecoratorConfig(4))))
		addFeature(VEGETAL_DECORATION, SPRING_FEATURE.configure(WATER_SPRING_CONFIG).createDecoratedFeature(COUNT_BIASED_RANGE.configure(RangeDecoratorConfig(50, 8, 8, 256))))
		
		addLandCarvers(this)
		addDefaultDisks(this)
		addDefaultOres(this)
		addMineables(this)
		addDungeons(this)
		
		addStructureFeature(STRONGHOLD.configure(DEFAULT))
		
		addAmbient(BAT, 10, 8, 8)
		
		//addCreature(PENGUIN, 1, 4, 8)
		
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
