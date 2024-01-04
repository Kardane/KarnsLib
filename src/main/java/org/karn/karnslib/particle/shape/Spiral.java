package org.karn.karnslib.particle.shape;

import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spiral {
    public static List<Map<String, Double>> SpiralDots(Vec3 center, double radius, double height, int numRotations, int numDots) {
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
            DotArray.add(dot);
        }
        return DotArray;
    }
}
