package org.karn.karnslib.particle.shape;

import org.karn.karnslib.util.Misc;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineRotation {
    public static List<Map<String, Double>> LineRotation(Vec3 pos1, Vec2 rot, double length, int numDots) {
        Vec3 pos2 = Misc.getLocalPos(pos1,rot,new Vec3(0,0,length));
        //return Line2Point(pos1.x,pos1.y,pos1.z, pos2.x,pos2.y,pos2.z, numDots);
        List line = Line2Dot.Line2Point(pos1, pos2, numDots);
        return line;
    }

    public static List<Map<String, Double>> Line2Point(double x1,double y1, double z1, double x2,double y2, double z2, int numDots) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        for (int i = 0; i < numDots; i++) {
            double t = (double) i / (numDots - 1);
            double linex = (1 - t) * x1 + t * x2;
            double liney = (1 - t) * y1 + t * y2;
            double linez = (1 - t) * z1 + t * z2;
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", linex);
            dot.put("y", liney);
            dot.put("z", linez);
            DotArray.add(dot);
        }
        return DotArray;
    }
}
