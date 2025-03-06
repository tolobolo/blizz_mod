package my.hector_tolobolo.gems_mod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import static my.hector_tolobolo.gems_mod.Gems_mod.STRENGTH_GEM;

// health system
public class Base_gem extends Item {
    public Base_gem(Settings settings) {
        super(settings);
    }
    public static int HEALTH = 3;

    public static void control_HEALTH(){
        if (HEALTH < 0) {
            HEALTH = 0;
            //change image and sette destroy variabelen
        }else {
            return;
        }
    }

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof PlayerEntity player) {
                HEALTH -= 1;
                control_HEALTH();
                player.sendMessage(Text.of("du er død " + HEALTH ));

            }
        });
    }
}
