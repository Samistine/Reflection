/*
 *  CaptainBern-Reflection-Framework contains several utils and tools
 *  to make Reflection easier.
 *  Copyright (C) 2014  CaptainBern
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.captainbern.reflection.bytecode.attribute;

import com.captainbern.reflection.bytecode.ConstantPool;
import com.captainbern.reflection.bytecode.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTable extends Attribute implements Opcode {

    private int localVariableTableLength;
    private LocalVariable[] variables;

    public LocalVariableTable(LocalVariableTable localVariableTable) {
        this(localVariableTable.getNameIndex(), localVariableTable.getLength(), localVariableTable.getVariables(), localVariableTable.getConstantPool());
    }

    public LocalVariableTable(int index, int length, DataInputStream codeStream, ConstantPool constantPool) throws IOException {
        this(index, length, (LocalVariable[]) null, constantPool);
        this.localVariableTableLength = codeStream.readUnsignedShort();
        this.variables = new LocalVariable[this.localVariableTableLength];
        for(int i = 0; i < this.localVariableTableLength; i++) {
            this.variables[i] = new LocalVariable(codeStream);
        }
    }

    public LocalVariableTable(int index, int length, LocalVariable[] variables, ConstantPool constantPool) {
        super(ATTR_LOCAL_VARIABLE_TABLE, index, length, constantPool);
        setVariables(variables);
    }

    public final int getLocalVariableTableLength() {
        return this.localVariableTableLength;
    }

    public final LocalVariable[] getVariables() {
        return this.variables;
    }

    public final void setVariables(LocalVariable[] variables) {
        this.localVariableTableLength = variables == null ? 0: variables.length;
        this.variables = variables;
    }
}