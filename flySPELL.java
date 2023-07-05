package com.example.an_addon.glyphs;

import com.hollingsworth.arsnouveau.api.spell.*;
import com.hollingsworth.arsnouveau.common.spell.augment.AugmentAmplify;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;

import java.util.Set;

import static com.example.an_addon.ExampleANAddon.prefix;

public class TestEffect extends AbstractEffect {

    public static TestEffect INSTANCE = new TestEffect(prefix("glyph_test"), "Test");

    public TestEffect(ResourceLocation tag, String description) {
        super(tag, description);
    }

    @Override
    public int getDefaultManaCost() {
        return 100;
    }

    @Override
    public void onResolve(HitResult rayTraceResult, Level world, @Nonnull LivingEntity shooter, SpellStats spellStats, SpellContext spellContext, SpellResolver resolver) {
        super.onResolve(rayTraceResult, world, shooter, spellStats, spellContext, resolver);

        System.out.println("Hello from my resolve!");
        applyPotion(mob, player, ENTHRALLED.get(), spellStats);
        player.addEffect(new MobEffectInstance(ModPotions.FLIGHT_EFFECT.get(), 60 * 20));
    }


    @Nonnull
    @Override
    public Set<AbstractAugment> getCompatibleAugments() {
        return augmentSetOf(EXTEND_TIM.INSTANCE);
    }

    @Override
    public int getBaseDuration() {
        return POTION_TIME == null ? 30 : POTION_TIME.get();
    }

    @Override
    public int getExtendTimeDuration() {
        return EXTEND_TIME == null ? 8 : EXTEND_TIME.get();
    }
}