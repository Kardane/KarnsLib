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

    public static void DotArrayDraw(ServerLevel world, ParticleOptions Particle, List<Map<String, Double>> array){
        DotArrayDraw(world,Particle,array,false,array.size());
    }

    public static void DotArrayDraw(ServerLevel world, ParticleOptions Particle, List<Map<String, Double>> array, boolean force){
        DotArrayDraw(world,Particle,array,force,array.size());
    }

    public static void DotArrayDraw(ServerLevel world, ParticleOptions Particle, List<Map<String, Double>> array, boolean force, Integer step) {
        for (int i = 0; i < step; i++) {
            Map<String, Double> Dot = array.get(0);
            DotDraw(world, Particle, Dot.get("x"), Dot.get("y"), Dot.get("z"), force ,new Vec3(Dot.get("dx"),Dot.get("dy"),Dot.get("dz")));
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

    public static void DotDraw(ServerLevel world, ParticleOptions Particle, double x, double y, double z, boolean force) {
        DotDraw(world,Particle,x,y,z,force,new Vec3(0,0,0));
    }

    public static void DotDraw(ServerLevel world, ParticleOptions Particle, double x, double y, double z, boolean force, Vec3 movement) {
        for(int j = 0; j < world.players().size(); ++j) {
            ServerPlayer player = world.players().get(j);
            world.sendParticles(player, Particle, force, x, y, z, 0, movement.x, movement.y, movement.z,1);
        }

    }
}
