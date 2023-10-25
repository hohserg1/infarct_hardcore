package infarct.hardcore.mod.mixin.client;

import infarct.hardcore.mod.Infarct_hardcoreClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "requestRespawn", at = @At("HEAD"))
    public void turnDisguiseOff(CallbackInfo ci) {
        Infarct_hardcoreClient.hardcoreDisguise = false;
    }

}
