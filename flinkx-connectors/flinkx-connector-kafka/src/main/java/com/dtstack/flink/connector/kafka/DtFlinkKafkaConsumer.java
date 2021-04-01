package com.dtstack.flink.connector.kafka;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @program: slink
 * @author: wuren
 * @create: 2021/03/16
 **/
public class DtFlinkKafkaConsumer extends FlinkKafkaConsumer {

    private KafkaDeserializationSchema deserializationSchema;

    public DtFlinkKafkaConsumer(String topic, KafkaDeserializationSchema deserializationSchema, Properties props) {
        super(Arrays.asList(StringUtils.split(topic, ",")), deserializationSchema, props);
        this.deserializationSchema = deserializationSchema;
    }

    public DtFlinkKafkaConsumer(Pattern subscriptionPattern, KafkaDeserializationSchema deserializationSchema, Properties props) {
        super(subscriptionPattern, deserializationSchema, props);
        this.deserializationSchema = deserializationSchema;
    }
}