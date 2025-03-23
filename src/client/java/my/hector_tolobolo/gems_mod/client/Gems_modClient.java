package my.hector_tolobolo.gems_mod.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class Gems_modClient implements ClientModInitializer {


    private static DrawContext context;

    public static void Timer(String timerString) {
        context.drawText(MinecraftClient.getInstance().textRenderer,
                Text.literal(timerString),
                10, 10, 0xFFFFFF, true);
    }

    @Override
    public void onInitializeClient() {
    }
}
