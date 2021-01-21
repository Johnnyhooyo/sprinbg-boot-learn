package com.hyq;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.io.IOException;

/**
 * @author：huyuanqiang
 * @time: 2020-08-20 14:23
 * @description:
 **/
public class AntInputSchema implements DeserializationSchema<AntInputDto>, SerializationSchema<AntInputDto> {
    @Override
    public AntInputDto deserialize(byte[] message) throws IOException {
        return JSON.parseObject(new String(message), AntInputDto.class);
    }

    @Override
    public boolean isEndOfStream(AntInputDto nextElement) {
        return false;
    }

    @Override
    public byte[] serialize(AntInputDto element) {
        return JSON.toJSONBytes(element);
    }

    @Override
    public TypeInformation<AntInputDto> getProducedType() {
        return TypeInformation.of(AntInputDto.class);
    }
}
