package com.example.startermod;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

@EventBusSubscriber(
        modid = StarterMod.MODID,
        value = Dist.CLIENT
)
public final class visualeffects {
    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
    }
}