package edu.hw11.Task3;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public enum FibonacciStackManipulation implements StackManipulation {

    INSTANCE;

    private static final int SIZE = 5;
    public static final String FIB_METHOD = "fib";
    public static final String DESCRIPTOR = "(I)J";

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public @NotNull Size apply(MethodVisitor methodVisitor, Implementation.Context context) {

        Label ifleZeroLabel = new Label();
        Label ifeqOneLabel = new Label();
        // Записываем в стек n
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        // Если n == 0, переходим в zeroLabel
        methodVisitor.visitJumpInsn(Opcodes.IFLE, ifleZeroLabel);

        // Записивыаем в стек n - 1
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ISUB);
        // Если n == 0 после вычитания, то переходим в ifeqOneLabel
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, ifeqOneLabel);

        // n > 1
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        // Получаем n - 1
        methodVisitor.visitInsn(Opcodes.ISUB);

        // Вызываем fib(n - 1)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            context.getInstrumentedType().getInternalName(),
            FIB_METHOD,
            DESCRIPTOR,
            false
        );

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        // Получаем n - 2
        methodVisitor.visitInsn(Opcodes.ISUB);

        // Вызываем fib(n - 2)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            context.getInstrumentedType().getInternalName(),
            FIB_METHOD,
            DESCRIPTOR,
            false
        );

        // Складываем fib(n - 1) и fib(n - 2) и возвращаем значение
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        // n <= 0, выводим 0
        methodVisitor.visitLabel(ifleZeroLabel);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        // n == 1, выводим 1
        methodVisitor.visitLabel(ifeqOneLabel);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        return new Size(0, SIZE);
    }
}
