package org.karn.karnslib.particle.shape;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line2Dot {
    public static List<Map<String, Double>> Line2Point(Vec3 pos1, Vec3 pos2, int numDots) {
        return Line2Point(pos1,pos2,numDots,new Vec3(0,0,0));
    }

    public static List<Map<String, Double>> Line2Point(Vec3 pos1, Vec3 pos2, int numDots, Vec3 movement) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        for (int i = 0; i < numDots; i++) {
            double t = (double) i / (numDots - 1);
            double linex = (1 - t) * pos1.x + t * pos2.x;
            double liney = (1 - t) * pos1.y + t * pos2.y;
            double linez = (1 - t) * pos1.z + t * pos2.z;
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", linex);
            dot.put("y", liney);
            dot.put("z", linez);
            dot.put("dx",movement.x);
            dot.put("dy",movement.y);
            dot.put("dz",movement.z);
            DotArray.add(dot);
        }
        return DotArray;
    }
}
