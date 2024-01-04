package org.karn.karnslib.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import org.karn.karnslib.hitbox.CubicHitbox;
import org.karn.karnslib.hitbox.CylinderHitbox;
import org.karn.karnslib.hitbox.LineHitbox;
import org.karn.karnslib.hitbox.SphereHitbox;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

import java.util.List;

public class HitboxCmd {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("hitboxapi")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("cubic")
                        .then(Commands.argument("pos1", Vec3Argument.vec3())
                                .then(Commands.argument("pos2",Vec3Argument.vec3())
                                        .executes(ctx ->{
                                            List<Entity> entities = CubicHitbox.getEntities(ctx.getSource().getLevel(),Vec3Argument.getVec3(ctx,"pos1"),Vec3Argument.getVec3(ctx,"pos2"));
                                            entities.forEach(e->{
                                                ctx.getSource().getLevel().sendParticles(ParticleTypes.END_ROD,e.getX(),e.getY()+1,e.getZ(),30,0.5,0.5,0.5,0);
                                            });
                                            ctx.getSource().sendSystemMessage(Component.literal(entities.toString()));
                                            return 1;
                                        })
                                )
                        )

                )
                .then(Commands.literal("sphere")
                        .then(Commands.argument("center", Vec3Argument.vec3())
                                .then(Commands.argument("radius", DoubleArgumentType.doubleArg(0.0))
                                        .executes(ctx ->{
                                            List<Entity> entities = SphereHitbox.getEntities(ctx.getSource().getLevel(),Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"));
                                            entities.forEach(e->{
                                                ctx.getSource().getLevel().sendParticles(ParticleTypes.END_ROD,e.getX(),e.getY()+1,e.getZ(),30,0.5,0.5,0.5,0);
                                            });
                                            ctx.getSource().sendSystemMessage(Component.literal(entities.toString()));
                                            return 1;
                                        })
                                )
                        )

                )

                .then(Commands.literal("cylinder")
                        .then(Commands.argument("center", Vec3Argument.vec3())
                                .then(Commands.argument("radius", DoubleArgumentType.doubleArg(0.0))
                                        .then(Commands.argument("height", DoubleArgumentType.doubleArg(0.0))
                                                .executes(ctx ->{
                                                    List<Entity> entities = CylinderHitbox.getEntities(ctx.getSource().getLevel(),Vec3Argument.getVec3(ctx,"center"),DoubleArgumentType.getDouble(ctx,"radius"),DoubleArgumentType.getDouble(ctx,"height"));
                                                    entities.forEach(e->{
                                                        ctx.getSource().getLevel().sendParticles(ParticleTypes.END_ROD,e.getX(),e.getY()+1,e.getZ(),30,0.5,0.5,0.5,0);
                                                    });
                                                    ctx.getSource().sendSystemMessage(Component.literal(entities.toString()));
                                                    return 1;
                                                })
                                        )
                                )
                        )

                )

                .then(Commands.literal("line")
                        .then(Commands.argument("start", Vec3Argument.vec3())
                                .then(Commands.argument("end", Vec3Argument.vec3())
                                        .then(Commands.argument("width", DoubleArgumentType.doubleArg(0.0))
                                                .then(Commands.argument("step", IntegerArgumentType.integer(0))
                                                        .executes(ctx ->{
                                                            List<Entity> entities = LineHitbox.getEntities(ctx.getSource().getLevel(),Vec3Argument.getVec3(ctx,"start"),Vec3Argument.getVec3(ctx,"end"),DoubleArgumentType.getDouble(ctx,"width"),IntegerArgumentType.getInteger(ctx,"step"));
                                                            entities.forEach(e->{
                                                                ctx.getSource().getLevel().sendParticles(ParticleTypes.HAPPY_VILLAGER,e.getX(),e.getY()+1,e.getZ(),30,0.5,0.5,0.5,0);
                                                            });
                                                            ctx.getSource().sendSystemMessage(Component.literal(entities.toString()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )

                )
        );
    }
}
