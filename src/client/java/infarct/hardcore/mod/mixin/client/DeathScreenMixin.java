package infarct.hardcore.mod.mixin.client;

import infarct.hardcore.mod.Infarct_hardcoreClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin {
    @Final
    @Mutable
    @Shadow private boolean isHardcore;
    @Shadow private int ticksSinceDeath;

    @Shadow
    private List<Drawable> drawables;

    @Shadow protected abstract void init();

    @Shadow @Final private List<ButtonWidget> buttons;
    @Unique
    private boolean realHardCore = false;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(@Nullable Text message, boolean isHardcore, CallbackInfo ci) {
        this.isHardcore = isHardcore || Infarct_hardcoreClient.hardcoreDisguise;
        realHardCore = isHardcore;

    }


    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.buttons.get(0).active = !(Infarct_hardcoreClient.hardcoreDisguise && !realHardCore);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        if (this.ticksSinceDeath > 20 * 7) {
            isHardcore = realHardCore;
            init();
            // this.drawables TODO
        }
    }
}
