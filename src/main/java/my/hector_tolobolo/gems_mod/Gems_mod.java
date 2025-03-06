package my.hector_tolobolo.gems_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gems_mod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("gems_mod");

    public static final Item STRENGTH_GEM = new Strength_gem(new FabricItemSettings().maxCount(1));
    public static final Item SPEED_GEM = new Speed_gem(new FabricItemSettings().maxCount(1));

    @Override
    public void onInitialize() {
        LOGGER.info("mod is running");

        Base_gem.register();
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "strength_gem"), STRENGTH_GEM);
        Registry.register(Registries.ITEM, new Identifier("gems_mod", "speed_gem"), SPEED_GEM);


    }
}
