package me.mrpingu.kotlinbiomesxl.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.*
import net.minecraft.entity.EntityGroup.*
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.EntityAttributes.*
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes.*
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class GoodmiteEntity(entityType: EntityType<out GoodmiteEntity>, world: World): AnimalEntity(entityType, world) {
	
	override fun initAttributes() {
		super.initAttributes()
		
		getAttributeInstance(MAX_HEALTH).baseValue = 8.0
		getAttributeInstance(MOVEMENT_SPEED).baseValue = 0.25
	}
	
	override fun initGoals() {
		goalSelector.add(1, SwimGoal(this))
		goalSelector.add(2, WanderAroundFarGoal(this, 1.0))
		goalSelector.add(3, LookAtEntityGoal(this, PlayerEntity::class.java, 8f))
		goalSelector.add(4, LookAroundGoal(this))
	}
	
	override fun canClimb() = false
	
	override fun createChild(passiveEntity: PassiveEntity): PassiveEntity = this
	
	override fun getActiveEyeHeight(entityPose: EntityPose, entityDimensions: EntityDimensions) = 0.1f
	
	override fun getAmbientSound(): SoundEvent = ENTITY_ENDERMITE_AMBIENT
	
	override fun getDeathSound(): SoundEvent = ENTITY_ENDERMITE_DEATH
	
	override fun getGroup(): EntityGroup = DEFAULT
	
	override fun getHeightOffset() = 0.1
	
	override fun getHurtSound(damageSource: DamageSource): SoundEvent = ENTITY_ENDERMITE_HURT
	
	override fun playStepSound(blockPos: BlockPos, blockState: BlockState) = playSound(ENTITY_ENDERMITE_STEP, 0.15f, 1f)
	
	override fun setYaw(yaw: Float) {
		this.yaw = yaw
		
		super.setYaw(yaw)
	}
	
	override fun tick() {
		bodyYaw = yaw
		
		super.tick()
	}
	
	override fun tickMovement() {
		super.tickMovement()
		
		if (world.isClient) {
			repeat(2) {
				world.addParticle(PORTAL, getParticleX(0.5), randomBodyY, getParticleZ(0.5), (random.nextDouble() - 0.5) * 2.0, -random.nextDouble(), (random.nextDouble() - 0.5) * 2.0)
			}
		}
	}
}
