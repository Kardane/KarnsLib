package org.karn.karnslib.hitbox;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Filter;

import java.util.List;

public class CubicHitbox {
    public static List<Entity> getEntities(Level level, Vec3 pos1, Vec3 pos2){
        AABB box = new AABB(pos1,pos2);
        return level.getEntities(null,box);
    }

    public static List<LivingEntity> getLivingEntities(Level level, Vec3 pos1, Vec3 pos2){
        return (List<LivingEntity>) getEntities(level,pos1,pos2).stream().filter(e -> Filter.isLiving(e));
    }

    public static List<Player> getPlayers(Level level, Vec3 pos1, Vec3 pos2){
        return (List<Player>) getLivingEntities(level,pos1,pos2).stream().filter(entity -> Filter.isPlayer(entity));
    }
}
