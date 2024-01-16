package org.karn.karnslib.particle.shape;

import org.karn.karnslib.util.Misc;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Map;

public class LineRotation {
    public static List<Map<String, Double>> LineRotation(Vec3 pos1, Vec2 rot, double length, int numDots) {
        Vec3 pos2 = Misc.getLocalPos(pos1,rot,new Vec3(0,0,length));
        List line = Line2Dot.Line2Point(pos1, pos2, numDots);
        return line;
    }

    public static List<Map<String, Double>> LineRotation(Vec3 pos1, Vec2 rot, double length, int numDots, Vec3 movement) {
        Vec3 pos2 = Misc.getLocalPos(pos1,rot,new Vec3(0,0,length));
        List line = Line2Dot.Line2Point(pos1, pos2, numDots, movement);
        return line;
    }
}
