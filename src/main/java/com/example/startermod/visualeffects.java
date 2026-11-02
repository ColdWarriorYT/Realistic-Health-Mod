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
        event.registerAboveAll(
                ResourceLocation.fromNamespaceAndPath(StarterMod.MODID, "screen_tint"),
                (gui, delta) -> {
                    int blood = Math.max(0, Math.min(100, playerhealth.health.getOrDefault("Blood", 100)));
                    int maxAlpha = (100 - blood) * 190 / 100;
                    if (maxAlpha == 0) {
                        return;
                    }

                    int width = gui.guiWidth();
                    int height = gui.guiHeight();
                    int depth = Math.min(width, height) / 3;
                    int steps = Math.min(48, depth);

                    for (int i = 0; i < steps; i++) {
                        int start = i * depth / steps;
                        int end = (i + 1) * depth / steps;
                        float fade = 1.0F - (i + 0.5F) / steps;
                        int alpha = (int) (maxAlpha * fade * fade);
                        int black = alpha << 24;

                        gui.fill(0, start, width, end, black);
                        gui.fill(0, height - end, width, height - start, black);
                        gui.fill(start, 0, end, height, black);
                        gui.fill(width - end, 0, width - start, height, black);
                    }
                }
        );
    }
}