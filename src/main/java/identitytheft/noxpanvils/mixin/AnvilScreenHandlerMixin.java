package identitytheft.noxpanvils.mixin;

import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
	@Shadow @Final private Property levelCost;

	@Inject(method = "canTakeOutput", at = @At("HEAD"), cancellable = true)
	private void noxpanvils$canTakeOutput(PlayerEntity player, boolean present, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
	}
	@Inject(method = "onTakeOutput", at = @At("HEAD"))
	private void noxpanvils$onTakeOutput(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
		levelCost.set(0);
	}

	@Inject(method = "updateResult", at = @At("TAIL"))
	private void noxpanvils$updateResult(CallbackInfo ci) {
		levelCost.set(0);
	}

	@Redirect(method = {"updateResult"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getOrDefault(Lnet/minecraft/component/ComponentType;Ljava/lang/Object;)Ljava/lang/Object;"))
	private Object noxpanvils$getOrDefault(ItemStack instance, ComponentType componentType, Object o) {
		return 0;
	}

	@Redirect(method = {"updateResult"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;get()I"))
	private int noxpanvils$getLevelCost(Property instance) {
		return 0;
	}

	@Inject(method = {"getNextCost"}, at = {@At("HEAD")}, cancellable = true)
	private static void noxpanvils$getNextCost(int cost, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(0);
	}

    @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40, ordinal = 2))
    private int noxpanvils$maxValue(int input) {
        return Integer.MAX_VALUE;
    }
}