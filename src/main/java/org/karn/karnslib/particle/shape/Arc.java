package org.karn.karnslib.particle.shape;

import net.minecraft.world.phys.Vec3;
import org.karn.karnslib.util.Misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arc {
    public static List<Map<String, Double>> CircleDots(Vec3 center, double radius, int numDots) {
        return ArcDots(center, radius, numDots, 0, 360, new Vec3(0,0,0));
    }

    public static List<Map<String, Double>> CircleDots(Vec3 center, double radius, int numDots, double centerMove) {
        return ArcDots(center, radius, numDots, 0, 360, centerMove);
    }

    public static List<Map<String, Double>> CircleDots(Vec3 center, double radius, int numDots, Vec3 movement) {
        return ArcDots(center, radius, numDots, 0, 360, new Vec3(movement.x,movement.y,movement.z));
    }

    public static List<Map<String, Double>> ArcDots(Vec3 center, double radius, int numDots, double startAngle, double endAngle) {
        return ArcDots(center,radius,numDots,startAngle,endAngle,new Vec3(0,0,0));
    }

    public static List<Map<String,Double>> ArcDots(Vec3 center,double radius, int numDots, double startAngle, double endAngle, double centerMove){
        List<Map<String, Double>> DotArray = new ArrayList<>();

        double startAngleRad = startAngle * Math.PI / 180;
        double endAngleRad = endAngle * Math.PI / 180;

        double angleIncrement = (endAngleRad - startAngleRad) / (numDots - 1);

        for (int i = 0; i < numDots; i++) {
            double angle = startAngleRad + i * angleIncrement;
            double arcx = center.x + radius * Math.cos(angle);
            double arcy = center.y;
            double arcz = center.z + radius * Math.sin(angle);
            Vec3 movement = Misc.getUnitVector(new Vec3(arcx,arcy,arcz),center);
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", arcx);
            dot.put("y", arcy);
            dot.put("z", arcz);
            dot.put("dx",movement.x*centerMove);
            dot.put("dy",movement.y*centerMove);
            dot.put("dz",movement.z*centerMove);
            DotArray.add(dot);
        }

        return DotArray;
    }

    public static List<Map<String, Double>> ArcDots(Vec3 center, double radius, int numDots, double startAngle, double endAngle, Vec3 movement) {
        List<Map<String, Double>> DotArray = new ArrayList<>();

        double startAngleRad = startAngle * Math.PI / 180;
        double endAngleRad = endAngle * Math.PI / 180;

        double angleIncrement = (endAngleRad - startAngleRad) / (numDots - 1);

        for (int i = 0; i < numDots; i++) {
            double angle = startAngleRad + i * angleIncrement;
            double arcx = center.x + radius * Math.cos(angle);
            double arcy = center.y;
            double arcz = center.z + radius * Math.sin(angle);
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", arcx);
            dot.put("y", arcy);
            dot.put("z", arcz);
            dot.put("dx",movement.x);
            dot.put("dy",movement.y);
            dot.put("dz",movement.z);
            DotArray.add(dot);
        }

        return DotArray;
    }
}
