package my.hector_tolobolo.gems_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;

import java.util.Stack;

import static my.hector_tolobolo.gems_mod.Base_gem.HEALTH;


public class Gems_mod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("gems_mod");

    public static final Item STRENGTH_GEM = new Strength_gem(new FabricItemSettings().maxCount(1));
    public static final Item SPEED_GEM = new Speed_gem(new FabricItemSettings().maxCount(1));
    public static ServerPlayerEntity player;
    public static final String NBT_KEY = "received_strength_gem";
    private static Boolean check_gem = false;

    @Override
    public void onInitialize() {
        LOGGER.info("mod is running");

        ServerPlayConnectionEvents.JOIN.register((ServerPlayNetworkHandler var1, PacketSender var2, MinecraftServer var3) -> {
            check_gem = true;
            LOGGER.error("this runs now");
            LOGGER.error(String.valueOf(player +" "+check_gem));

        });

        Base_gem.register();
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "strength_gem"+HEALTH), STRENGTH_GEM);
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "speed_gem"), SPEED_GEM);

        LOGGER.error("hello");
        does_player_have_gem();
    }

    private void does_player_have_gem() {
        if (!check_gem){
            return;
        }
        check_gem = false;
        LOGGER.error("CHECK GEM "+ check_gem);
        ItemStack stack = new ItemStack(STRENGTH_GEM);
        for (int i = 0; i > player.getInventory().size(); i++){
            if (player.getInventory().getStack(i) == stack){
                LOGGER.error("HAVE GEM");
                 return;

            }
        }
        LOGGER.error("player does not have gem, give gem to player");
        player.getInventory().insertStack(stack);
    }
}




