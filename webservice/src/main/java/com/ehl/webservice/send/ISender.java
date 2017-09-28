package com.ehl.webservice.send;

import org.apache.kafka.clients.producer.Callback;

/**
 * Created by 雷晓武 on 2017/8/7.
 */
@FunctionalInterface
public interface ISender<I> {

    public void send(I input) throws Exception;


}
