package net.minecraft.world.item.enchantment.providers;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public interface VanillaEnchantmentProviders {

    ResourceKey<EnchantmentProvider> MOB_SPAWN_EQUIPMENT = create("mob_spawn_equipment");
    ResourceKey<EnchantmentProvider> PILLAGER_SPAWN_CROSSBOW = create("pillager_spawn_crossbow");
    ResourceKey<EnchantmentProvider> RAID_PILLAGER_POST_WAVE_3 = create("raid/pillager_post_wave_3");
    ResourceKey<EnchantmentProvider> RAID_PILLAGER_POST_WAVE_5 = create("raid/pillager_post_wave_5");
    ResourceKey<EnchantmentProvider> RAID_VINDICATOR = create("raid/vindicator");
    ResourceKey<EnchantmentProvider> RAID_VINDICATOR_POST_WAVE_5 = create("raid/vindicator_post_wave_5");
    ResourceKey<EnchantmentProvider> ENDERMAN_LOOT_DROP = create("enderman_loot_drop");

    static void bootstrap(BootstrapContext<EnchantmentProvider> bootstrapcontext) {
        HolderGetter<Enchantment> holdergetter = bootstrapcontext.lookup(Registries.ENCHANTMENT);

        bootstrapcontext.register(VanillaEnchantmentProviders.MOB_SPAWN_EQUIPMENT, new EnchantmentsByCostWithDifficulty(holdergetter.getOrThrow(EnchantmentTags.ON_MOB_SPAWN_EQUIPMENT), 5, 17));
        bootstrapcontext.register(VanillaEnchantmentProviders.PILLAGER_SPAWN_CROSSBOW, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.PIERCING), ConstantInt.of(1)));
        bootstrapcontext.register(VanillaEnchantmentProviders.RAID_PILLAGER_POST_WAVE_3, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.QUICK_CHARGE), ConstantInt.of(1)));
        bootstrapcontext.register(VanillaEnchantmentProviders.RAID_PILLAGER_POST_WAVE_5, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.QUICK_CHARGE), ConstantInt.of(2)));
        bootstrapcontext.register(VanillaEnchantmentProviders.RAID_VINDICATOR, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.SHARPNESS), ConstantInt.of(1)));
        bootstrapcontext.register(VanillaEnchantmentProviders.RAID_VINDICATOR_POST_WAVE_5, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.SHARPNESS), ConstantInt.of(2)));
        bootstrapcontext.register(VanillaEnchantmentProviders.ENDERMAN_LOOT_DROP, new SingleEnchantment(holdergetter.getOrThrow(Enchantments.SILK_TOUCH), ConstantInt.of(1)));
    }

    static ResourceKey<EnchantmentProvider> create(String s) {
        return ResourceKey.create(Registries.ENCHANTMENT_PROVIDER, MinecraftKey.withDefaultNamespace(s));
    }
}
