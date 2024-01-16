package org.karn.karnslib.motion;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleCharge {
    public static void startCharge(Entity entity, Vec3 direction, double range,Vec3 origin){
        if(Mth.sqrt((float) entity.distanceToSqr(origin)) > range){
            stopMotion(entity);
        } else {
            applyMotion(entity,direction);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    startCharge(entity,direction,range,origin);
                }
            };
            timer.schedule(task, 50);
        }
    }

    public static void stopMotion(Entity entity){
        entity.setDeltaMovement(0,0,0);
        if(entity instanceof Player) entity.hurtMarked = true;
    }

    public static void applyMotion(Entity entity, Vec3 motion){
        entity.setDeltaMovement(motion);
        if(entity instanceof Player) entity.hurtMarked = true;
    }
}
