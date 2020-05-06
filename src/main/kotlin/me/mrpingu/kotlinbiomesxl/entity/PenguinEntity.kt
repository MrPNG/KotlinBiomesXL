package me.mrpingu.kotlinbiomesxl.entity

import net.minecraft.entity.EntityPose
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.passive.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

class PenguinEntity(entityType: EntityType<out PenguinEntity>, world: World): AnimalEntity(entityType, world) {
	
	init {
		goalSelector.add(0, SwimGoal(this))
		goalSelector.add(1, EscapeDangerGoal(this, 0.25))
		goalSelector.add(2, AnimalMateGoal(this, 1.0))
		goalSelector.add(4, FollowParentGoal(this, 0.2))
		goalSelector.add(5, WanderAroundFarGoal(this, 0.15))
		goalSelector.add(6, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
		goalSelector.add(7, LookAroundGoal(this))
	}
	
	override fun createChild(passiveEntity: PassiveEntity) = this
}