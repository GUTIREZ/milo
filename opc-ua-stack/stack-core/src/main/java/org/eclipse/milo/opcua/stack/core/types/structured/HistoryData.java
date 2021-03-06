/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryData")
public class HistoryData implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryData;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryData_Encoding_DefaultXml;

    protected final DataValue[] _dataValues;

    public HistoryData() {
        this._dataValues = null;
    }

    public HistoryData(DataValue[] _dataValues) {
        this._dataValues = _dataValues;
    }

    @Nullable
    public DataValue[] getDataValues() { return _dataValues; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("DataValues", _dataValues)
            .toString();
    }

    public static void encode(HistoryData historyData, UaEncoder encoder) {
        encoder.encodeArray("DataValues", historyData._dataValues, encoder::encodeDataValue);
    }

    public static HistoryData decode(UaDecoder decoder) {
        DataValue[] _dataValues = decoder.decodeArray("DataValues", decoder::decodeDataValue, DataValue.class);

        return new HistoryData(_dataValues);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryData::encode, HistoryData.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryData::decode, HistoryData.class, BinaryEncodingId, XmlEncodingId);
    }

}
