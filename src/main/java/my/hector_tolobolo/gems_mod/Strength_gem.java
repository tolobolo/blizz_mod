package my.hector_tolobolo.gems_mod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.text.Text;

public class Strength_gem extends Base_gem {
    public Strength_gem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND && !world.isClient)  {
            user.sendMessage(Text.of("strong"));
            user.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.STRENGTH, 200, 3));
        }


        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && !world.isClient) {
            if (player.getOffHandStack() == stack) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.STRENGTH, 60, 1, true, false));
            }
        }


    }
}