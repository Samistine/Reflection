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
import com.captainbern.reflection.bytecode.constant.Utf8Constant;
import com.captainbern.reflection.bytecode.exception.ClassFormatException;

import java.io.DataInputStream;
import java.io.IOException;

public class SourceFile extends Attribute implements Opcode {

    private int sourceFileIndex;

    public SourceFile(SourceFile sourceFile) {
        this(sourceFile.getNameIndex(), sourceFile.getLength(), sourceFile.getSourceFileIndex(), sourceFile.getConstantPool());
    }

    public SourceFile(int index, int length, DataInputStream codeStream, ConstantPool constantPool) throws IOException {
        this(index, length, codeStream.readUnsignedShort(), constantPool);
    }

    public SourceFile(int index, int length, int sourceFileIndex, ConstantPool constantPool) {
        super(ATTR_SOURCE_FILE, index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
    }

    public final int getSourceFileIndex() {
        return this.sourceFileIndex;
    }

    public final void setSourceFileIndex(int sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }

    public String getSourceFile() throws ClassFormatException {
        Utf8Constant constant = (Utf8Constant) this.constantPool.getConstant(this.sourceFileIndex, CONSTANT_Utf8);
        return constant.getString();
    }
}