package net.minecraft.world.item.enchantment;

import net.minecraft.tags.TagsItem;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;

public class DensityEnchantment extends Enchantment {

    public DensityEnchantment() {
        super(Enchantment.definition(TagsItem.MACE_ENCHANTABLE, 10, 5, Enchantment.dynamicCost(1, 11), Enchantment.dynamicCost(21, 11), 1, FeatureFlagSet.of(FeatureFlags.UPDATE_1_21), EnumItemSlot.MAINHAND));
    }

    public static float calculateDamageAddition(int i, float f) {
        return f * (float) i;
    }
}
