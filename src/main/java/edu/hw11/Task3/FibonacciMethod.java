package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;

public class FibonacciMethod implements ByteCodeAppender {
    @Override
    public @NotNull Size apply(
        @NotNull MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        MethodDescription methodDescription
    ) {
        if (!methodDescription.getReturnType().asErasure().represents(long.class)) {
            throw new IllegalArgumentException(methodDescription + " must return long");
        }
        StackManipulation.Size operandStackSize = FibonacciStackManipulation.INSTANCE.apply(methodVisitor, context);
        return new Size(operandStackSize.getMaximalSize(), methodDescription.getStackSize());
    }

}
