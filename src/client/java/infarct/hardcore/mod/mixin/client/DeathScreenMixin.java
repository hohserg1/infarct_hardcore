package infarct.hardcore.mod.mixin.client;

import infarct.hardcore.mod.Infarct_hardcoreClient;
import java.util.List;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin {

    @Final
    @Mutable
    @Shadow
    private boolean isHardcore;
    @Shadow
    private int ticksSinceDeath;

    @Shadow
    @Final
    private List<ButtonWidget> buttons;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(@Nullable Text message, boolean isHardcore, CallbackInfo ci) {
        if (Infarct_hardcoreClient.hardcoreDisguise) {
            this.isHardcore = true;
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        if (Infarct_hardcoreClient.hardcoreDisguise) {
            if (this.ticksSinceDeath > 20 * 7) {
                Text text = Text.translatable("deathScreen.respawn");
                this.buttons.get(0).setMessage(text);
                Infarct_hardcoreClient.hardcoreDisguise = false;
            }
        }
    }
}
