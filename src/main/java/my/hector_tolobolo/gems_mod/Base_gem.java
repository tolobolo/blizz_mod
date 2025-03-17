package my.hector_tolobolo.gems_mod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static my.hector_tolobolo.gems_mod.Gems_mod.LOGGER;
import static my.hector_tolobolo.gems_mod.Gems_mod.STRENGTH_GEM;

// health system
public class Base_gem extends Item {
    public Base_gem(Settings settings) {
        super(settings);
    }
    public static int HEALTH = 2;

    public static void control_HEALTH(){
        LOGGER.info("control health");
        if (HEALTH < 0) {
            HEALTH = 0;
            // sette destroy variabelen
        }
            // change image

    }
    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            ItemStack stack = new ItemStack(STRENGTH_GEM);
            NbtCompound nbt = stack.getOrCreateNbt();
            int health = (int) HEALTH;
            if (entity instanceof PlayerEntity player) {
                HEALTH -= 1;
                control_HEALTH();
                player.sendMessage(Text.of("du er død " + HEALTH ));
                LOGGER.error(String.valueOf(nbt));
                nbt.putInt("custom_health_data",health);
                LOGGER.error(String.valueOf(nbt));
                ItemStack stacke = new ItemStack(STRENGTH_GEM);
                NbtCompound nbtt = stacke.getOrCreateNbt();
                LOGGER.error(String.valueOf(nbtt));




            }
        });

    }


}
