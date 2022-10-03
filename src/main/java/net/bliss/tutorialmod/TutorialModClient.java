package net.bliss.tutorialmod;

import net.bliss.tutorialmod.block.ModBlocks;
import net.bliss.tutorialmod.client.ThirstHudOverlay;
import net.bliss.tutorialmod.event.KeyInputHandler;
import net.bliss.tutorialmod.fluid.ModFluids;
import net.bliss.tutorialmod.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EGGPLAND_CROP, RenderLayer.getCutout());
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();

        HudRenderCallback.EVENT.register(new ThirstHudOverlay());

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xA1E038D0
                ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER);
    }
}
