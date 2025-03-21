package my.hector_tolobolo.gems_mod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Speed_gem extends Base_gem {
    public static boolean use_attack = false;

    public Speed_gem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND && !world.isClient && !use_attack) {
            user.sendMessage(Text.of("HASTE"));
            user.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.HASTE, 200, 3));
            use_attack = true;
            Base_gem.CountDown("100", use_attack);
        }


        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && !world.isClient) {
            if (player.getOffHandStack() == stack) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.SPEED, 60, 1, true, false));
            }
        }


    }
}
