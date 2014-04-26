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

package com.captainbern.reflection.bytecode.attribute.annotation;

import com.captainbern.reflection.bytecode.ConstantPool;
import com.captainbern.reflection.bytecode.attribute.Attribute;

import java.io.DataInputStream;
import java.io.IOException;

public class ParameterAnnotation extends Attribute {

    private int paramTableLength;
    private ParameterAnnotationEntry[] parameterAnnotationEntries;

    public ParameterAnnotation(String tag, int index, int length, DataInputStream codeStream, ConstantPool constantPool) throws IOException {
        this(tag, index, length, (ParameterAnnotationEntry[]) null, constantPool);
        this.paramTableLength = codeStream.readUnsignedByte();
        this.parameterAnnotationEntries = new ParameterAnnotationEntry[this.paramTableLength];
        for(int i = 0; i < this.paramTableLength; i++) {
            this.parameterAnnotationEntries[i] = new ParameterAnnotationEntry(codeStream, constantPool);
        }
    }

    public ParameterAnnotation(String tag, int index, int length, ParameterAnnotationEntry[] entries, ConstantPool constantPool) {
        super(tag, index, length, constantPool);
        setParameterAnnotationEntries(entries);
    }

    public final void setParameterAnnotationEntries(ParameterAnnotationEntry[] entries) {
        this.parameterAnnotationEntries = entries;
        this.paramTableLength = entries == null ? 0 : entries.length;
    }

    public final ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return this.parameterAnnotationEntries;
    }

    public final int getParamTableLength() {
        return this.paramTableLength;
    }
}