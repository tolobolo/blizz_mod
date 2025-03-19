package my.hector_tolobolo.gems_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Gems_mod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("gems_mod");
    public static final Item STRENGTH_GEM = new Strength_gem(new FabricItemSettings().maxCount(1));
    public static final Item SPEED_GEM = new Speed_gem(new FabricItemSettings().maxCount(1));
    public static final String NBT_KEY = "received_strength_gem";


    @Override
    public void onInitialize() {
        LOGGER.info("mod is running");

        ServerPlayConnectionEvents.JOIN.register((ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) -> {

            ServerPlayerEntity player = handler.getPlayer();
            LOGGER.error("player {}", player);
            does_player_have_gem(player);
            //NbtCompound data = player.getPersistentData(); // Get player's persistent data
/*
            if (!data.getBoolean(RECEIVED_ITEM_KEY)) { // Check if item was given before
                giveStarterItem(player);
                data.putBoolean(RECEIVED_ITEM_KEY, true); // Mark as received
            }*/

        });
        Base_gem.register();
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "strength_gem"), STRENGTH_GEM);
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "speed_gem"), SPEED_GEM);
        LOGGER.error("hello");
    }

    private void does_player_have_gem(ServerPlayerEntity player) {


        for (int i = 0; i > player.getInventory().size(); i++) {
            Item item = player.getInventory().getStack(i).getItem();
            if (item == STRENGTH_GEM) {
                LOGGER.error("HAVE GEM {}, {}", item);
                return;
            }
        }
        LOGGER.error("player does not have gem, give gem to player");
        ItemStack stack = new ItemStack(STRENGTH_GEM);
        player.getInventory().insertStack(stack);
    }
}




