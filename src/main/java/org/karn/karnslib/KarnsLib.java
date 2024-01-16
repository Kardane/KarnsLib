package org.karn.karnslib;

import org.karn.karnslib.command.HitboxCmd;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.karn.karnslib.command.MotionCmd;
import org.karn.karnslib.command.ParticleCmd;

public class KarnsLib implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, ignored1) -> {
            HitboxCmd.register(dispatcher);
            ParticleCmd.register(dispatcher,commandRegistryAccess);
            MotionCmd.register(dispatcher);
        });
        System.out.println("Mod Online!");
    }
}
