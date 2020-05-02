package me.mrpingu.kotlinbiomesxl.world.biome

import net.minecraft.entity.EntityCategory.*
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.AmbientEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.world.biome.Biome

abstract class ModBiome(settings: Biome.Settings): Biome(settings) {
	
	fun <T: AmbientEntity> addAmbient(type: EntityType<T>, weight: Int, minGroupSize: Int, maxGroupSize: Int) = addSpawn(AMBIENT, SpawnEntry(type, weight, minGroupSize, maxGroupSize))
	fun <T: PassiveEntity> addCreature(type: EntityType<T>, weight: Int, minGroupSize: Int, maxGroupSize: Int) = addSpawn(CREATURE, SpawnEntry(type, weight, minGroupSize, maxGroupSize))
	fun <T: MobEntity> addMonster(type: EntityType<T>, weight: Int, minGroupSize: Int, maxGroupSize: Int) = addSpawn(MONSTER, SpawnEntry(type, weight, minGroupSize, maxGroupSize))
}