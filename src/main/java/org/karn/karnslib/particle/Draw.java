package org.karn.karnslib.particle;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Draw {
    public static void DotArrayDraw(ServerLevel world, ParticleOptions Particle, List<Map<String, Double>> array, boolean force, Integer step) {
        for (int i = 0; i < step; i++) {
            Map<String, Double> Dot = array.get(0);
            DotDraw(world, Particle, Dot.get("x"), Dot.get("y"), Dot.get("z"),force);
            array.remove(0);
            if (array.size() < 1) {
                return;
            }
        }
        if (array.size() > 0) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    DotArrayDraw(world, Particle, array, force,step);
                }
            };
            int delay = 50; // 0.05초 뒤에 실행
            timer.schedule(task, delay);
        }
    }

    //vector method1
    public static void DotDraw(ServerLevel world, ParticleOptions Particle, Vec3 pos) {
        DotDraw(world, Particle, pos.x, pos.y, pos.z, false);
    }

    //vector method2
    public static void DotDraw(ServerLevel world, ParticleOptions Particle, Vec3 pos, boolean force) {
        DotDraw(world, Particle, pos.x, pos.y, pos.z, true);
    }

    //xyz method1
    public static void DotDraw(ServerLevel world, ParticleOptions Particle, double x, double y, double z) {
        DotDraw(world, Particle, x, y, z, false);
    }

    //xyz method2
    public static void DotDraw(ServerLevel world, ParticleOptions Particle, double x, double y, double z, boolean force) {
        for(int j = 0; j < world.players().size(); ++j) {
            ServerPlayer player = world.players().get(j);
            world.sendParticles(player, Particle, force, x, y, z, 0, 0, 0, 0, 1);
        }

    }
}
