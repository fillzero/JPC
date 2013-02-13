package org.jpc.emulator.execution.opcodes.pm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class bsr_Gw_Ew_mem extends Executable
{
    final int op1Index;
    final Pointer op2;

    public bsr_Gw_Ew_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        op1Index = Processor.getRegIndex(parent.operand[0].toString());
        op2 = new Pointer(parent.operand[1], parent.adr_mode);
    }

    public Branch execute(Processor cpu)
    {
        Reg op1 = cpu.regs[op1Index];
        if (op2.get16(cpu) == 0) {
	    cpu.zf(true);
	} else {
	    cpu.zf(false);
	    op1.set16(31-StaticOpcodes.numberOfLeadingZeros(0xFFFF&op2.get16(cpu)));
	}
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}