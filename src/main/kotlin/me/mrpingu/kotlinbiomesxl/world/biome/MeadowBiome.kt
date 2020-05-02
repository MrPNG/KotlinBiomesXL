package me.mrpingu.kotlinbiomesxl.world.biome

import me.mrpingu.kotlinbiomesxl.extension.WeightedBlockStateProvider
import net.minecraft.block.Blocks.*
import net.minecraft.entity.EntityType.*
import net.minecraft.world.biome.Biome.Category.*
import net.minecraft.world.biome.Biome.Precipitation.*
import net.minecraft.world.biome.DefaultBiomeFeatures.*
import net.minecraft.world.gen.GenerationStep.Feature.*
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.decorator.Decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.FeatureConfig.*
import net.minecraft.world.gen.feature.MineshaftFeature.*
import net.minecraft.world.gen.feature.MineshaftFeature.Type.*
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig.Builder
import net.minecraft.world.gen.placer.SimpleBlockPlacer
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder

object MeadowBiome: ModBiome(
	Settings()
		.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
		.precipitation(RAIN)
		.category(PLAINS)
		.depth(0.125f)
		.scale(0.05f)
		.temperature(0.8f)
		.downfall(0.8f)
		.waterColor(0x3f76e4)
		.waterFogColor(0x050533)
		.parent(null)
) {
	
	private val flowerBlockStateProvider = WeightedBlockStateProvider(
		DANDELION.defaultState to 1,
		OXEYE_DAISY.defaultState to 1,
		ALLIUM.defaultState to 1,
		CORNFLOWER.defaultState to 1
	)
	private val flowerConfig = (Builder(flowerBlockStateProvider, SimpleBlockPlacer())).tries(64).build()
	
	init {
		addFeature(LOCAL_MODIFICATIONS, LAKE.configure(SingleStateFeatureConfig(WATER.defaultState)).createDecoratedFeature(WATER_LAKE.configure(ChanceDecoratorConfig(4))))
		addFeature(VEGETAL_DECORATION, SPRING_FEATURE.configure(WATER_SPRING_CONFIG).createDecoratedFeature(COUNT_BIASED_RANGE.configure(RangeDecoratorConfig(50, 8, 8, 256))))
		
		addLandCarvers(this)
		addDefaultDisks(this)
		addDefaultOres(this)
		addMineables(this)
		addDungeons(this)
		
		addStructureFeature(VILLAGE.configure(VillageFeatureConfig("village/plains/town_centers", 6)))
		addStructureFeature(PILLAGER_OUTPOST.configure(DEFAULT))
		addStructureFeature(MINESHAFT.configure(MineshaftFeatureConfig(0.004, NORMAL)))
		addStructureFeature(STRONGHOLD.configure(DEFAULT))
		
		addFeature(VEGETAL_DECORATION, RANDOM_PATCH.configure(GRASS_CONFIG).createDecoratedFeature(COUNT_HEIGHTMAP_DOUBLE.configure(CountDecoratorConfig(16))))
		addFeature(VEGETAL_DECORATION, FLOWER.configure(flowerConfig).createDecoratedFeature(COUNT_HEIGHTMAP_32.configure(CountDecoratorConfig(16))))
		
		addAmbient(BAT, 10, 8, 8)
		
		addCreature(CHICKEN, 10, 4, 4)
		addCreature(COW, 8, 4, 4)
		addCreature(DONKEY, 1, 1, 3)
		addCreature(HORSE, 5, 2, 6)
		addCreature(PIG, 10, 4, 4)
		addCreature(SHEEP, 12, 4, 4)
		
		addMonster(CREEPER, 100, 4, 4)
		addMonster(ENDERMAN, 10, 1, 4)
		addMonster(SKELETON, 100, 4, 4)
		addMonster(SLIME, 100, 4, 4)
		addMonster(SPIDER, 100, 4, 4)
		addMonster(WITCH, 5, 1, 1)
		addMonster(ZOMBIE, 95, 4, 4)
		addMonster(ZOMBIE_VILLAGER, 5, 1, 1)
	}
}
