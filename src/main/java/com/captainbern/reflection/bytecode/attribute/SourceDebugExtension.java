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

public class SourceDebugExtension extends Attribute implements Opcode {

    private int sourceDebugExtensionsCount;
    private DebugExtension[] debugExtensions;

    public SourceDebugExtension(int index, int lenght, DataInputStream codeStream, ConstantPool constantPool) throws IOException {
        this(index, lenght, (DebugExtension[]) null, constantPool);
        this.sourceDebugExtensionsCount = codeStream.readUnsignedShort();
        this.debugExtensions = new DebugExtension[this.sourceDebugExtensionsCount];
        for(int i = 0; i < this.sourceDebugExtensionsCount; i++) {
            this.debugExtensions[i] = new DebugExtension(codeStream);
        }
    }

    public SourceDebugExtension(int index, int length, DebugExtension[] debugExtensions, ConstantPool constantPool) {
        super(ATTR_SOURCE_DEBUG_EXTENSION, index, length, constantPool);
        setDebugExtensions(debugExtensions);
    }

    public final int getSourceDebugExtensionsCount() {
        return this.sourceDebugExtensionsCount;
    }

    public final DebugExtension[] getDebugExtensions() {
        return this.debugExtensions;
    }

    public final void setDebugExtensions(DebugExtension[] debugExtensions) {
        this.sourceDebugExtensionsCount = debugExtensions == null ? 0 : debugExtensions.length;
        this.debugExtensions = debugExtensions;
    }
}