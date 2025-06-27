package com.euphony.folkcraft_vanilla.mixin;

import com.euphony.folkcraft_vanilla.common.init.FCItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void checkTotemDeathProtection(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            cir.setReturnValue(false);
            cir.cancel();
        } else {
            ItemStack itemStack = null;

            for(InteractionHand interactionHand : InteractionHand.values()) {
                ItemStack itemStack2 = livingEntity.getItemInHand(interactionHand);
                if (itemStack2.is(FCItems.SOUL_RETALIATION_TOTEM)) {
                    itemStack = itemStack2.copy();
                    itemStack2.shrink(1);
                    break;
                }
            }

            if (itemStack != null) {
                if (livingEntity instanceof ServerPlayer serverPlayer) {
                    serverPlayer.awardStat(Stats.ITEM_USED.get(FCItems.SOUL_RETALIATION_TOTEM.get()));
                    CriteriaTriggers.USED_TOTEM.trigger(serverPlayer, itemStack);
                    livingEntity.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
                }

                livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 4.0F, false, Level.ExplosionInteraction.MOB);

                livingEntity.setHealth(4.0F);
                livingEntity.removeAllEffects();
                livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 2));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 2));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1));
                livingEntity.level().broadcastEntityEvent(livingEntity, (byte)35);
            }

            cir.setReturnValue(itemStack != null);
            cir.cancel();
        }
    }
}
