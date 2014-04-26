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

public class Synthetic extends Attribute implements Opcode {

    private byte[] bytes;

    public Synthetic(Synthetic synthetic) {
        this(synthetic.getNameIndex(), synthetic.getLength(), synthetic.getBytes(), synthetic.getConstantPool());
    }

    public Synthetic(int index, int length, DataInputStream codeStream, ConstantPool constantPool) throws IOException {
        this(index, length, (byte[]) null, constantPool);
        if(length > 0) {
            byte[] bytes = new byte[length];
            codeStream.readFully(bytes);
        }
    }

    public Synthetic(int index, int length, byte[] bytes, ConstantPool constantPool) {
        super(ATTR_SYNTHETIC, index, length, constantPool);
        this.bytes = bytes;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}