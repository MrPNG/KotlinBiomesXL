package me.mrpingu.kotlinbiomesxl.world.gen.surfacebuilder

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks.*
import net.minecraft.util.math.BlockPos.*
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig
import java.util.*

object GlacierSurfaceBuilder: SurfaceBuilder<TernarySurfaceConfig>(TernarySurfaceConfig::deserialize) {
	
	override fun generate(
		random: Random,
		chunk: Chunk,
		biome: Biome,
		x: Int,
		z: Int,
		y: Int,
		depth: Double,
		defaultBlock: BlockState,
		defaultFluid: BlockState,
		seaLevel: Int,
		seed: Long,
		surfaceConfig: TernarySurfaceConfig
	) {
		DEFAULT.generate(random, chunk, biome, x, z, y, depth, defaultBlock, defaultFluid, seaLevel, seed, surfaceConfig)
		
		if (chunk.getBlockState(Mutable(x % 16, y - 1, z % 16)).block == SNOW_BLOCK)
			chunk.setBlockState(Mutable(x % 16, y - 2, z % 16), PACKED_ICE.defaultState, false)
	}
}