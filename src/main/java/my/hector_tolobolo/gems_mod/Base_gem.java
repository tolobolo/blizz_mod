package my.hector_tolobolo.gems_mod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.Timer;
import java.util.TimerTask;

import static my.hector_tolobolo.gems_mod.Gems_mod.LOGGER;
import static my.hector_tolobolo.gems_mod.Gems_mod.STRENGTH_GEM;


// health system
public class Base_gem extends Item {
    public static int health = 2;


    public Base_gem(Settings settings) {
        super(settings);
    }

    public static void control_HEALTH() {
        LOGGER.info("control health");
        if (health < 0) {
            health = 0;
            // sette destroy variabelen
        }
        // change image

    }

    public static void register() {

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            ItemStack stack = new ItemStack(STRENGTH_GEM);
            NbtCompound nbt = stack.getOrCreateNbt();
            LOGGER.error("nbt {}", nbt);
            nbt.putInt("CustomModelData", health);
            stack.setNbt(nbt);
            LOGGER.error("{} HEALTH {}", nbt, health);

            newPlayer.getInventory().insertStack(stack);
        });
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof PlayerEntity player) {
                health -= 1;
                control_HEALTH();
                player.sendMessage(Text.of("du er død " + health));
            }


        });
    }

    public static void CountDown(String secs, boolean use_attack) {
        int delay = 1200;
        int period = 1000;
        Timer timer = new Timer();
        int interval = Integer.parseInt(secs);
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        String timerString = String.valueOf(setInterval(interval, timer));

                    }
                },
                delay,
                period
        );
        use_attack = false;
    }

    private static final Integer setInterval(int interval, Timer timer) {
        if (interval == 1) timer.cancel();
        return --interval;
    }

}
