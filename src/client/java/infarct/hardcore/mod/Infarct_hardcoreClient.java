package infarct.hardcore.mod;

import java.util.Random;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

public class Infarct_hardcoreClient implements ClientModInitializer {

    public static boolean hardcoreDisguise = false;

    private float prevHP = 0;

    private Random rand = new Random();

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!hardcoreDisguise) {
                if (client.player != null) {
                    float currentHP = client.player.getHealth();
                    if (prevHP > currentHP) {
                        if (!client.world.getLevelProperties().isHardcore()) {
                            if (currentHP <= 3.5) {
                                if (rand.nextInt(100) < 50) {
                                    hardcoreDisguise = true;
                                    sendWarning(client);
                                }
                            }
                        }
                    }
                    prevHP = currentHP;
                }
            }
        });
    }

    private void sendWarning(MinecraftClient client) {
        client.player.sendMessage(MutableText.of(new TranslatableTextContent("infarct_hardcore.warning", null, new Object[0])));
    }
}