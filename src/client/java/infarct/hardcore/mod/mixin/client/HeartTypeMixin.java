package infarct.hardcore.mod.mixin.client;

import infarct.hardcore.mod.*;
import net.minecraft.client.gui.hud.InGameHud.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(HeartType.class)
public class HeartTypeMixin {

    @ModifyVariable(method = "getTexture", index = 1, at = @At("HEAD"), argsOnly = true)
    private boolean hardcore(boolean original) {
        if (Infarct_hardcoreClient.hardcoreDisguise) {
            return true;
        } else {
            return original;
        }
    }
}