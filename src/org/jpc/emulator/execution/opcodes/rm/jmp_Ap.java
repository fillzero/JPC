package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class jmp_Ap extends Executable
{
    final int cs, targetEip;

    public jmp_Ap(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        targetEip = parent.operand[0].ptr.off;
        cs = parent.operand[0].ptr.seg;
    }

    public Branch execute(Processor cpu)
    {
        cpu.jumpFar(cs, targetEip);
        return Branch.Jmp_Unknown;
    }

    public boolean isBranch()
    {
        return true;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}