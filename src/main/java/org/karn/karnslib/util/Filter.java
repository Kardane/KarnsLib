package org.karn.karnslib.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static boolean isLiving(Entity entity){
        if(entity instanceof LivingEntity)
            return true;
        else
            return false;
    }

    public static boolean isPlayer(Entity entity){
        if(entity instanceof Player)
            return true;
        else
            return false;
    }

    public static List<?> removeDuplicates(List<List> lists){
        List<Object> mergedList = new ArrayList<>();
        for (List list :lists) {
            for(var element:list){
                mergedList.add(element);
            }
        }
        return mergedList.stream().distinct().toList();
    }
}
