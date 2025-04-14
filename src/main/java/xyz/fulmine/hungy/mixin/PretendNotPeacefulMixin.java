package xyz.fulmine.hungy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class PretendNotPeacefulMixin {
    @WrapOperation(method = "tickMovement()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty hungy$pretendItsNotPeaceful(World instance, Operation<Difficulty> original) {
        if (instance.getDifficulty() == Difficulty.PEACEFUL) {
            return Difficulty.NORMAL;
        }
        return original.call(instance);
    }
}
