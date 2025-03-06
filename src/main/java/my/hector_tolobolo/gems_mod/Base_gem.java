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


    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof PlayerEntity player) {
                player.sendMessage(Text.of("du er død"));

            }

            ItemStack gem = new ItemStack(STRENGTH_GEM);

            //set keepinventory
            NbtCompound tag = new NbtCompound();
            tag.putBoolean("Unbrekable", true);
            tag.putBoolean("keep", true);
            gem.setNbt(tag);

            if (entity instanceof PlayerEntity player && !player.getInventory().contains(gem)){
                player.getInventory().insertStack(gem);
            }
        });
    }
}
