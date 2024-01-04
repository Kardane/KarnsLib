package org.karn.karnslib.hitbox;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Filter;

import java.util.ArrayList;
import java.util.List;

public class CylinderHitbox {
    public static List<Entity> getEntities(Level level, Vec3 pos1, double radius, double height){
        List<Entity> list = CubicHitbox.getEntities(level,new Vec3(pos1.x -radius,pos1.y,pos1.z-radius),new Vec3(pos1.x+radius,pos1.y+height,pos1.z+radius));
        List<Entity> cylinder = new ArrayList<Entity>();
        list.forEach(entity -> {
            double dx = entity.getX() - pos1.x;
            double dz = entity.getZ() - pos1.z;
            if(dx*dx+dz*dz <= radius * radius) cylinder.add(entity);
        });
        return cylinder;
    }

    public static List<LivingEntity> getLivingEntities(Level level, Vec3 pos1, double radius, double height){
        return (List<LivingEntity>) getEntities(level,pos1,radius,height).stream().filter(entity -> Filter.isLiving(entity));
    }

    public static List<Player> getPlayers(Level level, Vec3 pos1, double radius, double height){
        return (List<Player>) getEntities(level,pos1,radius,height).stream().filter(entity -> Filter.isLiving(entity));
    }
}
