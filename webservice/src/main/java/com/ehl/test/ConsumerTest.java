package com.ehl.test;

import com.ehl.lxw.common.bean.CarMsg;
import com.ehl.lxw.common.bean.CarMsgBuilder;
import com.ehl.tvc.lxw.common.EhlConfiguration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by 雷晓武 on 2017/8/8.
 */
public class ConsumerTest extends Thread{
    private final KafkaConsumer<Long, ByteBuffer> consumer;
    private final String topic;
    private Logger logger = LoggerFactory.getLogger(getClass());
    public ConsumerTest() {
        EhlConfiguration ehlConfiguration = new EhlConfiguration("kafka.conf");
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ehlConfiguration.get("kafka.broker"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-llll");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,"35000");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteBufferDeserializer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.LongDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = ehlConfiguration.get("kafka.topic");
    }

    @Override
    public void run() {
        while (true) {
            consumer.subscribe(Collections.singletonList(this.topic));
            ConsumerRecords<Long, ByteBuffer> records = consumer.poll(0);
            for (ConsumerRecord<Long, ByteBuffer> record : records) {
                try {
//                    CarMsg carMsg = CarMsgBuilder.toBuilderByByteBuffer(record.value());
//                    logger.info("Received message: (" + record.key() + ", value= " + carMsg.toString() + ") at offset " + record.offset()+" in partition "+record.partition() );
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
//            consumer.commitSync();
         //commit by yourself and setting ENABLE_AUTO_COMMIT_CONFIG=false
            consumer.commitSync();
        }

    }

    public static void main(String[] args) {

//        for(int i=0;i<4;i++) {
            new ConsumerTest().start();
//        }
    }
}
