package infarct.hardcore.mod;

import java.util.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.*;
import net.minecraft.client.*;
import net.minecraft.text.*;

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
                        if (currentHP < 3) {
                            if (rand.nextInt(100) < 50) {
                                hardcoreDisguise = true;
                                sendWarning(client);
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