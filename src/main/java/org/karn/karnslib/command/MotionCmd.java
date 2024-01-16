package org.karn.karnslib.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import org.karn.karnslib.motion.SimpleCharge;

public class MotionCmd {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("charge")
                .requires(source -> source.hasPermission(2))
                .then(Commands.argument("entity", EntityArgument.entity())
                        .then(Commands.argument("direction", Vec3Argument.vec3())
                                .then(Commands.argument("range", DoubleArgumentType.doubleArg())
                                        .executes(ctx ->{
                                            SimpleCharge.startCharge(EntityArgument.getEntity(ctx,"entity"),Vec3Argument.getVec3(ctx,"direction"),DoubleArgumentType.getDouble(ctx,"range"),EntityArgument.getEntity(ctx,"entity").position());
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("looking")
                                .then(Commands.argument("range", DoubleArgumentType.doubleArg())
                                        .executes(ctx ->{
                                            SimpleCharge.startCharge(EntityArgument.getEntity(ctx,"entity"),ctx.getSource().getPlayerOrException().getLookAngle(),DoubleArgumentType.getDouble(ctx,"range"),EntityArgument.getEntity(ctx,"entity").position());
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }
}
