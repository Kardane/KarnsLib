package org.karn.karnslib.particle.shape;

import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spiral {
    public static List<Map<String, Double>> SpiralDots(Vec3 center, double radius, double height, int numRotations, int numDots){
        return SpiralDots(center, radius, height, numRotations, numDots, new Vec3(0,0,0));
    }
    public static List<Map<String, Double>> SpiralDots(Vec3 center, double radius, double height, int numRotations, int numDots, Vec3 movement) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        double angleStep = 2 * Math.PI * numRotations / numDots;
        for (int i = 0; i < numDots; i++) {
            double angle = i * angleStep;
            double spiralx = center.x + radius * Math.cos(angle);
            double spiralz = center.z + radius * Math.sin(angle);
            double spiraly = center.y + height * (i / (double) numDots);
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", spiralx);
            dot.put("y", spiraly);
            dot.put("z", spiralz);
            dot.put("dx",movement.x);
            dot.put("dy",movement.y);
            dot.put("dz",movement.z);
            DotArray.add(dot);
        }
        return DotArray;
    }

    public static List<Map<String, Double>> SpiralDots(Vec3 center, double radius, double height, int numRotations, int numDots, double centerMovement) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        double angleStep = 2 * Math.PI * numRotations / numDots;
        for (int i = 0; i < numDots; i++) {
            double angle = i * angleStep;
            double spiralx = center.x + radius * Math.cos(angle);
            double spiralz = center.z + radius * Math.sin(angle);
            double spiraly = center.y + height * (i / (double) numDots);
            Vec3 movement = Misc.getUnitVector(new Vec3(spiralx,spiraly,spiralz),new Vec3(center.x,spiraly,center.z));
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", spiralx);
            dot.put("y", spiraly);
            dot.put("z", spiralz);
            dot.put("dx",movement.x*centerMovement);
            dot.put("dy",movement.y*centerMovement);
            dot.put("dz",movement.z*centerMovement);
            DotArray.add(dot);
        }
        return DotArray;
    }
}
