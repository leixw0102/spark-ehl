package com.ehl.webservice.kafka;

import com.ehl.tvc.lxw.common.EhlConfiguration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by 雷晓武 on 2017/8/7.
 *
 * //TODO 变种 实现将producer 放入多线程
 */
public class Producer extends Thread {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final KafkaProducer<Long, ByteBuffer> producer;
    private String topic;
    private boolean isAsync;

    private ConcurrentLinkedQueue<ByteBuffer> concurrentLinkedQueue;
    public Producer(EhlConfiguration ehlConfiguration, ConcurrentLinkedQueue<ByteBuffer> concurrentLinkedQueue) {
        Properties props = new Properties();
        props.put("bootstrap.servers", ehlConfiguration.get("kafka.broker"));
        props.put("client.id", MessageFormat.format(ehlConfiguration.get("kafka.client.id","{0}"),System.currentTimeMillis()+""));
        props.put("key.serializer", ehlConfiguration.get("kafka.key.serializer"));
        props.put("value.serializer", ehlConfiguration.get("kafka.value.serializer"));
        props.put("batch.size",ehlConfiguration.getInt("kafka.batch.size",2014*2));
        props.put("linger.ms",ehlConfiguration.getInt("kafka.linger.ms",2));
        props.put("buffer.memory",ehlConfiguration.getInt("kafka.buffer",128)*1024);
        props.put("acks","1");
        props.put("compression.type",ehlConfiguration.get("kafka.compression.type","none"));
        props.put("partitioner.class",ehlConfiguration.get("kafka.partitioner.class"));
        producer = new KafkaProducer(props);
        this.topic = ehlConfiguration.get("kafka.topic","leixw-tvc");
        this.isAsync = ehlConfiguration.getBoolean("kafka.isAsync",true);
        this.concurrentLinkedQueue = concurrentLinkedQueue;
    }

    @Override
    public void run() {
        while (true){
            ByteBuffer input = concurrentLinkedQueue.poll();
            if(null != input){
                long key = System.currentTimeMillis();
                    producer.send(new ProducerRecord(topic,key,input),
                            new EhlKafkaCallBack(key,key,input.toString()));

            }else{
//                try {
//                    if(logger.isDebugEnabled()){
//                        logger.debug("queue null ,sleep 1 ms");
//                    }
//                    Thread.sleep(1L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

    }



}
