package my.hector_tolobolo.gems_mod;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.Timer;
import java.util.TimerTask;

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
                LOGGER.error("before the loop, invetory size {}, player {}", player.getInventory().size(), player);
                for (int i = 0; i < player.getInventory().size(); i++) {
                    Item item = player.getInventory().getStack(i).getItem();
                    LOGGER.error("item {}, i {}, inventory size {}", item, i, player.getInventory().size());
                    if (item.equals(STRENGTH_GEM)) {
                        LOGGER.error("found strength gem");
                        LOGGER.error("HAVE GEM");
                        NbtCompound nbt = item.getDefaultStack().getNbt();
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

    public static void CountDown(String secs, boolean use_attack) {
        int delay = 1200;
        int period = 1000;
        Timer timer = new Timer();
        int interval = Integer.parseInt(secs);
        LOGGER.error(secs);
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        LOGGER.error("hello");
                        setInterval(interval, timer);
                    }
                },
                delay,
                period
        );
        use_attack = false;
    }

    private static final int setInterval(int interval, Timer timer) {
        if (interval == 1) timer.cancel();
        return --interval;
    }

}
