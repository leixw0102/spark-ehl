package com.ehl.webservice.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 雷晓武 on 2017/8/8.
 */
public class EhlKafkaCallBack implements Callback {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final long startTime;
    private final long key;
    private final String message;

    public EhlKafkaCallBack(long startTime, long key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }


    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
//            if(logger.isDebugEnabled()) {
                logger.info(
                        "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                                "), " +
                                "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
//            }
        } else {
            exception.printStackTrace();
        }
    }
}
