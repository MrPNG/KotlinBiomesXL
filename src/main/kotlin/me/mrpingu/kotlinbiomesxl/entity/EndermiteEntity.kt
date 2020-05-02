package me.mrpingu.kotlinbiomesxl.entity

import net.minecraft.block.BlockState
import net.minecraft.entity.*
import net.minecraft.entity.EntityGroup.*
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.EntityAttributes.*
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundTag
import net.minecraft.particle.ParticleTypes.*
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.World
import java.util.*

class EndermiteEntity(entityType: EntityType<out EndermiteEntity>, world: World): HostileEntity(entityType, world) {
	
	private var isPlayerSpawned = false
	private var lifeTime = 0
	
	init {
		experiencePoints = 3
	}
	
	override fun initAttributes() {
		super.initAttributes()
		
		getAttributeInstance(ATTACK_DAMAGE).baseValue = 2.0
		getAttributeInstance(MAX_HEALTH).baseValue = 8.0
		getAttributeInstance(MOVEMENT_SPEED).baseValue = 0.25
	}
	
	override fun initGoals() {
		goalSelector.add(1, SwimGoal(this))
		goalSelector.add(2, MeleeAttackGoal(this, 1.0, false))
		goalSelector.add(3, WanderAroundFarGoal(this, 1.0))
		goalSelector.add(7, LookAtEntityGoal(this, PlayerEntity::class.java, 8f))
		goalSelector.add(8, LookAroundGoal(this))
		
		targetSelector.add(1, RevengeGoal(this).setGroupRevenge())
		targetSelector.add(2, FollowTargetGoal(this, PlayerEntity::class.java, true))
	}
	
	override fun canClimb() = false
	
	override fun getActiveEyeHeight(entityPose: EntityPose, entityDimensions: EntityDimensions) = 0.1f
	
	override fun getAmbientSound(): SoundEvent = ENTITY_ENDERMITE_AMBIENT
	
	override fun getDeathSound(): SoundEvent = ENTITY_ENDERMITE_DEATH
	
	override fun getGroup(): EntityGroup = ARTHROPOD
	
	override fun getHeightOffset() = 0.1
	
	override fun getHurtSound(damageSource: DamageSource): SoundEvent = ENTITY_ENDERMITE_HURT
	
	override fun playStepSound(blockPos: BlockPos, blockState: BlockState) = playSound(ENTITY_ENDERMITE_STEP, 0.15f, 1f)
	
	override fun setYaw(f: Float) {
		yaw = f
		
		super.setYaw(f)
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
		} else {
			if (!isPersistent) lifeTime++
			if (lifeTime >= 2400) remove()
		}
	}
	
	override fun readCustomDataFromTag(compoundTag: CompoundTag) {
		super.readCustomDataFromTag(compoundTag)
		
		with(compoundTag) {
			lifeTime = getInt("Lifetime")
			isPlayerSpawned = getBoolean("PlayerSpawned")
		}
	}
	
	override fun writeCustomDataToTag(compoundTag: CompoundTag) {
		super.writeCustomDataToTag(compoundTag)
		
		compoundTag.apply {
			putInt("Lifetime", lifeTime)
			putBoolean("PlayerSpawned", isPlayerSpawned)
		}
	}
	
	companion object {
		
		fun canSpawn(entityType: EntityType<EndermiteEntity>, world: IWorld, spawnType: SpawnType, blockPos: BlockPos, random: Random) =
			canSpawnIgnoreLightLevel(entityType, world, spawnType, blockPos, random)
			&& world.getClosestPlayer(blockPos.x + 0.5, blockPos.y + 0.5, blockPos.z + 0.5, 5.0, true) == null
	}
}
