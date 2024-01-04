package org.karn.karnslib.hitbox;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Filter;

import java.util.ArrayList;
import java.util.List;

public class LineHitbox {
    public static List<Entity> getEntities(Level level, Vec3 start, Vec3 end, double width, int step){
        double dx = (end.x - start.x) / step;
        double dy = (end.y - start.y) / step;
        double dz = (end.z - start.z) / step;
        List<Entity> entities = new ArrayList<>();
        Vec3 current = start;
        for(var i = 0;i<step;i++){
            current = start.add(dx*i,dy*i,dz*i);
            AABB box = new AABB(current.add(width,width,width),current.add(-width,-width,-width));
            level.getEntities(null,box).forEach(entity -> entities.add(entity));
            //level.getServer().overworld().sendParticles(ParticleTypes.END_ROD,current.x,current.y,current.z,1,0,0,0,0);
        }
        return entities.stream().distinct().toList();
    }

    public static List<LivingEntity> getLivingEntities(Level level, Vec3 start, Vec3 end, double width, int step){
        return (List<LivingEntity>) getEntities(level,start,end,width,step).stream().filter(entity -> Filter.isLiving(entity));
    }

    public static List<Player> getPlayers(Level level, Vec3 start, Vec3 end, double width, int step){
        return (List<Player>) getEntities(level,start,end,width,step).stream().filter(entity -> Filter.isPlayer(entity));
    }
}
