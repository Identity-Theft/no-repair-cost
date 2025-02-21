package identitytheft.noxpanvils.mixin;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
	@Redirect(method = {"renderLabels"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"))
	public MutableComponent noxpanvils$costText(String pKey, Object[] pArgs) {
		return null;
	}
}
