package org.karn.karnslib.hitbox;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Filter;

import java.util.List;

public class SphereHitbox {
    public static List<Entity> getEntities(Level level, Vec3 pos1, double radius){
        List<Entity> list = CubicHitbox.getEntities(level,new Vec3(pos1.x -radius,pos1.y-radius,pos1.z-radius),new Vec3(pos1.x+radius,pos1.y+radius,pos1.z+radius));
        return list.stream().filter(entity -> Math.sqrt(entity.distanceToSqr(pos1)) < radius).toList();
    }

    public static List<LivingEntity> getLivingEntities(Level level, Vec3 pos1, double radius){
        return (List<LivingEntity>) getEntities(level,pos1,radius).stream().filter(entity -> Filter.isLiving(entity));
    }

    public static List<Player> getPlayers(Level level, Vec3 pos1, double radius){
        return (List<Player>) getEntities(level,pos1,radius).stream().filter(entity -> Filter.isPlayer(entity));
    }
}
