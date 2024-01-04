package org.karn.karnslib.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ParticleArgument;
import net.minecraft.commands.arguments.coordinates.Vec2Argument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.karn.karnslib.hitbox.CubicHitbox;
import org.karn.karnslib.hitbox.CylinderHitbox;
import org.karn.karnslib.hitbox.LineHitbox;
import org.karn.karnslib.hitbox.SphereHitbox;
import org.karn.karnslib.particle.Draw;
import org.karn.karnslib.particle.shape.Arc;
import org.karn.karnslib.particle.shape.Line2Dot;
import org.karn.karnslib.particle.shape.LineRotation;
import org.karn.karnslib.particle.shape.Spiral;

import java.util.List;
import java.util.Map;


public class ParticleCmd {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(Commands.literal("particleshape")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("circle")
                        .then(Commands.argument("center", Vec3Argument.vec3())
                                .then(Commands.argument("radius",DoubleArgumentType.doubleArg(0))
                                        .then(Commands.argument("num",IntegerArgumentType.integer(1))
                                                .then(Commands.argument("particle", ParticleArgument.particle(registryAccess))
                                                        .executes(ctx ->{
                                                            List<Map<String, Double>> dots = Arc.CircleDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"));
                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,false,dots.size());
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("force", BoolArgumentType.bool())
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> dots = Arc.CircleDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                    Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),dots.size());
                                                                    return 1;
                                                                })
                                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> dots = Arc.CircleDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),IntegerArgumentType.getInteger(ctx,"step"));
                                                                            return 1;
                                                                        })
                                                                )
                                                        )
                                                )

                                        )
                                )
                        )

                )

                .then(Commands.literal("arc")
                        .then(Commands.argument("center", Vec3Argument.vec3())
                                .then(Commands.argument("radius",DoubleArgumentType.doubleArg(0))
                                        .then(Commands.argument("angle1",DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("angle2",DoubleArgumentType.doubleArg())
                                                    .then(Commands.argument("num",IntegerArgumentType.integer(1))
                                                        .then(Commands.argument("particle", ParticleArgument.particle(registryAccess))
                                                            .executes(ctx ->{
                                                                List<Map<String, Double>> dots = Arc.ArcDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"),DoubleArgumentType.getDouble(ctx,"angle1"),DoubleArgumentType.getDouble(ctx,"angle2"));
                                                                Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,false,dots.size());
                                                                return 1;
                                                            })
                                                        .then(Commands.argument("force", BoolArgumentType.bool())
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> dots = Arc.ArcDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"),DoubleArgumentType.getDouble(ctx,"angle1"),DoubleArgumentType.getDouble(ctx,"angle2"));
                                                                    Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),dots.size());
                                                                    return 1;
                                                                })
                                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> dots = Arc.ArcDots(Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx,"num"),DoubleArgumentType.getDouble(ctx,"angle1"),DoubleArgumentType.getDouble(ctx,"angle2"));
                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),IntegerArgumentType.getInteger(ctx,"step"));
                                                                            return 1;
                                                                        })
                                                                )
                                                        )
                                                )
                                        )
                                        )
                                        )
                                )
                        )

                )

                .then(Commands.literal("spiral")
                        .then(Commands.argument("center", Vec3Argument.vec3())
                                .then(Commands.argument("radius",DoubleArgumentType.doubleArg(0))
                                        .then(Commands.argument("height",DoubleArgumentType.doubleArg())
                                                .then(Commands.argument("rotation",IntegerArgumentType.integer(1))
                                                        .then(Commands.argument("num",IntegerArgumentType.integer(1))
                                                                .then(Commands.argument("particle", ParticleArgument.particle(registryAccess))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> dots = Spiral.SpiralDots(Vec3Argument.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"),IntegerArgumentType.getInteger(ctx, "num"));
                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,false,dots.size());
                                                                            return 1;
                                                                        })
                                                                        .then(Commands.argument("force", BoolArgumentType.bool())
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> dots = Spiral.SpiralDots(Vec3Argument.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"),IntegerArgumentType.getInteger(ctx, "num"));
                                                                                    Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),dots.size());
                                                                                    return 1;
                                                                                })
                                                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                                                        .executes(ctx ->{
                                                                                            List<Map<String, Double>> dots = Spiral.SpiralDots(Vec3Argument.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"),IntegerArgumentType.getInteger(ctx, "num"));
                                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),IntegerArgumentType.getInteger(ctx,"step"));
                                                                                            return 1;
                                                                                        })
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )

                )

                .then(Commands.literal("line1")
                        .then(Commands.argument("pos1", Vec3Argument.vec3())
                                .then(Commands.argument("pos2",Vec3Argument.vec3())
                                        .then(Commands.argument("num",IntegerArgumentType.integer(1))
                                                .then(Commands.argument("particle", ParticleArgument.particle(registryAccess))
                                                        .executes(ctx ->{
                                                            List<Map<String, Double>> dots = Line2Dot.Line2Point(Vec3Argument.getVec3(ctx,"pos1"),Vec3Argument.getVec3(ctx,"pos2"),IntegerArgumentType.getInteger(ctx,"num"));
                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,false,dots.size());
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("force", BoolArgumentType.bool())
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> dots = Line2Dot.Line2Point(Vec3Argument.getVec3(ctx,"pos1"),Vec3Argument.getVec3(ctx,"pos2"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                    Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),dots.size());
                                                                    return 1;
                                                                })
                                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> dots = Line2Dot.Line2Point(Vec3Argument.getVec3(ctx,"pos1"),Vec3Argument.getVec3(ctx,"pos2"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),IntegerArgumentType.getInteger(ctx,"step"));
                                                                            return 1;
                                                                        })
                                                                )
                                                        )
                                                )

                                        )
                                )
                        )

                )

                .then(Commands.literal("line2")
                        .then(Commands.argument("pos1", Vec3Argument.vec3())
                                .then(Commands.argument("rot",Vec2Argument.vec2())
                                        .then(Commands.argument("length",DoubleArgumentType.doubleArg())
                                        .then(Commands.argument("num",IntegerArgumentType.integer(1))
                                                .then(Commands.argument("particle", ParticleArgument.particle(registryAccess))
                                                        .executes(ctx ->{
                                                            List<Map<String, Double>> dots = LineRotation.LineRotation(Vec3Argument.getVec3(ctx,"pos1"),Vec2Argument.getVec2(ctx,"rot"),DoubleArgumentType.getDouble(ctx,"length"),IntegerArgumentType.getInteger(ctx,"num"));
                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,false,dots.size());
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("force", BoolArgumentType.bool())
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> dots = LineRotation.LineRotation(Vec3Argument.getVec3(ctx,"pos1"),Vec2Argument.getVec2(ctx,"rot"),DoubleArgumentType.getDouble(ctx,"length"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                    Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),dots.size());
                                                                    return 1;
                                                                })
                                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> dots = LineRotation.LineRotation(Vec3Argument.getVec3(ctx,"pos1"),Vec2Argument.getVec2(ctx,"rot"),DoubleArgumentType.getDouble(ctx,"length"),IntegerArgumentType.getInteger(ctx,"num"));
                                                                            Draw.DotArrayDraw(ctx.getSource().getLevel(),ParticleArgument.getParticle(ctx,"particle"),dots,BoolArgumentType.getBool(ctx,"force"),IntegerArgumentType.getInteger(ctx,"step"));
                                                                            return 1;
                                                                        })
                                                                )
                                                        )
                                                )

                                        )
                                ))
                        )

                )
        );
    }
}
