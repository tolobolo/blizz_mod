package my.hector_tolobolo.gems_mod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import static my.hector_tolobolo.gems_mod.Gems_mod.LOGGER;
import static my.hector_tolobolo.gems_mod.Gems_mod.STRENGTH_GEM;


// health system
public class Base_gem extends Item {
    public static int HEALTH = 2;

    public Base_gem(Settings settings) {
        super(settings);
    }

    public static void control_HEALTH() {
        LOGGER.info("control health");
        if (HEALTH < 0) {
            HEALTH = 0;
            // sette destroy variabelen
        }
        // change image

    }

    public static void register() {

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {

            if (entity instanceof PlayerEntity player) {
                HEALTH -= 1;
                int health = (int) HEALTH;
                control_HEALTH();
                player.sendMessage(Text.of("du er død " + HEALTH));
                for (int i = 0; i > player.getInventory().size(); i++) {
                    if (player.getInventory().getStack(i).getItem() == STRENGTH_GEM) {

                        LOGGER.error("HAVE GEM");
                        NbtCompound nbt = player.getInventory().getStack(i).getOrCreateNbt();
                        LOGGER.error("nbt {}", nbt);
                        nbt.putInt("custom_health_data", health);
                        LOGGER.error("{} HEALTH {}", nbt, health);
                        player.getInventory().getStack(i).setNbt(nbt);
                        LOGGER.error("STACK {}", player.getInventory().getStack(i));
                        LOGGER.error("NBT FINAL {}", nbt);


                    }
                }
            }
        });

    }


}
