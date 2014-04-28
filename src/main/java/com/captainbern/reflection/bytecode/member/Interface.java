package com.captainbern.reflection.bytecode.member;

import com.captainbern.reflection.bytecode.ConstantPool;
import com.captainbern.reflection.bytecode.Opcode;
import com.captainbern.reflection.bytecode.constant.ClassConstant;
import com.captainbern.reflection.bytecode.constant.Utf8Constant;
import com.captainbern.reflection.bytecode.exception.ClassFormatException;

public class Interface implements Opcode {

    private int index;
    private ConstantPool constantPool;

    public Interface(int index, ConstantPool constantPool) {
         this.index = index;
        this.constantPool = constantPool;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int index) {
        this.index = index;
    }

    public final String getName() throws ClassFormatException {
        return this.constantPool.getConstantString(this.index, CONSTANT_Class);
    }

    public final void setName(String name) throws ClassFormatException {
        this.constantPool.setConstant(((ClassConstant) this.constantPool.getConstant(this.index, CONSTANT_Class)).getNameIndex(), new Utf8Constant(name));
    }

    public final ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public final void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
}
