package identitytheft.noxpanvils.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {
	@Shadow @Final private DataSlot cost;

	@Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
	private void noxpanvils$canTakeOutput(Player pPlayer, boolean pHasStack, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
	}
	@Inject(method = "onTake", at = @At("HEAD"))
	private void noxpanvils$onTakeOutput(Player pPlayer, ItemStack pStack, CallbackInfo ci) {
		cost.set(0);
	}

	@Inject(method = "createResult", at = @At("TAIL"))
	private void noxpanvils$updateResult(CallbackInfo ci) {
		cost.set(0);
	}

	@Redirect(method = {"createResult"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getBaseRepairCost()I"))
	private int noxpanvils$getOrDefault(ItemStack instance) {
		return 0;
	}

	@Redirect(method = {"createResult"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;get()I"))
	private int noxpanvils$getLevelCost(DataSlot instance) {
		return 0;
	}

	@Inject(method = {"calculateIncreasedRepairCost"}, at = {@At("HEAD")}, cancellable = true)
	private static void noxpanvils$getNextCost(int cost, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(0);
	}

	@ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
	private int noxpanvils$maxValue(int input) {
		return Integer.MAX_VALUE;
	}
}
