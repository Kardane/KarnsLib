package org.karn.karnslib.util;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Misc {
    public Vec3 getUnitVector(Vec3 vec1, Vec3 vec2){
        Vec3 a = new Vec3(vec2.x-vec1.x,vec2.y-vec1.y,vec2.z-vec1.z);
        double m = Mth.sqrt((float) (a.x*a.x + a.y*a.y + a.z*a.z));
        return new Vec3(-a.x/m,-a.y/m,-a.z/m);
    }
    public Vec3 getLocalPos(Vec3 pos, Vec2 rot, double forward, double up, double right){
        return getLocalPos(pos,rot,new Vec3(forward,up,right));
    }
    public static Vec3 getLocalPos(Vec3 pos, Vec2 rot, Vec3 movement){
        float var4 = Mth.cos((rot.y + 90.0F) * (float) (Math.PI / 180.0));
        float var5 = Mth.sin((rot.y + 90.0F) * (float) (Math.PI / 180.0));
        float var6 = Mth.cos(-rot.x * (float) (Math.PI / 180.0));
        float var7 = Mth.sin(-rot.x * (float) (Math.PI / 180.0));
        float var8 = Mth.cos((-rot.x + 90.0F) * (float) (Math.PI / 180.0));
        float var9 = Mth.sin((-rot.x + 90.0F) * (float) (Math.PI / 180.0));
        Vec3 var10 = new Vec3((double)(var4 * var6), (double)var7, (double)(var5 * var6));
        Vec3 var11 = new Vec3((double)(var4 * var8), (double)var9, (double)(var5 * var8));
        Vec3 var12 = var10.cross(var11).scale(-1.0);
        double var13 = var10.x * movement.z + var11.x * movement.y + var12.x * movement.x;
        double var15 = var10.y * movement.z + var11.y * movement.y + var12.y * movement.x;
        double var17 = var10.z * movement.z + var11.z * movement.y + var12.z * movement.x;
        return new Vec3(pos.x + var13, pos.y + var15, pos.z + var17);
    }
}
