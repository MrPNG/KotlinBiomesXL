package me.mrpingu.kotlinbiomesxl.world.gen.feature

import net.minecraft.block.Blocks.*
import net.minecraft.block.SnowyBlock.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction.*
import net.minecraft.util.math.Vec3i
import net.minecraft.world.Heightmap.Type.*
import net.minecraft.world.IWorld
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig
import net.minecraft.world.gen.feature.*
import java.util.*
import java.util.function.Function

object SnowTopLayerFeature: Feature<DefaultFeatureConfig>(Function { FeatureConfig.DEFAULT }) {
	
	override fun generate(world: IWorld, chunkGenerator: ChunkGenerator<out ChunkGeneratorConfig>, random: Random, blockPos: BlockPos, defaultFeatureConfig: DefaultFeatureConfig): Boolean {
		val block = BlockPos.Mutable()
		val blockBelow = BlockPos.Mutable()
		
		for (x in 0 until 16) {
			for (z in 0 until 16) {
				val newX = blockPos.x + x
				val newZ = blockPos.z + z
				val y = world.getTopY(MOTION_BLOCKING, newX, newZ)
				
				block.set(newX, y, newZ)
				blockBelow.set(block as Vec3i).setOffset(DOWN, 1)
				
				val biome = world.getBiome(block)
				if (biome.canSetIce(world, blockBelow, false)) world.setBlockState(blockBelow, ICE.defaultState, 2)
				if (biome.canSetSnow(world, block)) {
					val blockBelowState = world.getBlockState(blockBelow)
					if (blockBelowState.block != SNOW_BLOCK) world.setBlockState(block, SNOW_BLOCK.defaultState, 2)
					if (SNOWY in blockBelowState) world.setBlockState(blockBelow, blockBelowState.with(SNOWY, true), 2)
				}
			}
		}
		return true
	}
}
