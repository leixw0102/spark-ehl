package com.ehl.webservice.send;

import com.ehl.tvc.lxw.common.EhlConfiguration;
import com.ehl.lxw.common.bean.CarMsg;
import com.ehl.webservice.kafka.Producer;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by 雷晓武 on 2017/8/7.
 */
public class KafkaSend implements ISender<CarMsg>{

    public ConcurrentLinkedQueue<ByteBuffer> concurrentLinkedQueue =new ConcurrentLinkedQueue();

    final ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"ehl-tvc-webservice");
        }
    });
    private EhlConfiguration ehlConfiguration;


    public KafkaSend(String file) {
        ehlConfiguration = new EhlConfiguration(file);
//        executorService.execute(;

        int size=ehlConfiguration.getInt("kafka.producer.size",1);

        for(int i=0;i<size;i++){
            executorService.execute(new Producer(ehlConfiguration,concurrentLinkedQueue));
        }

//        Runtime.getRuntime().addShutdownHook(new Thread(()->executorService.shutdown()));
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    executorService.shutdown();
                }
            });
    }



    public static void main(String[] args) throws Exception {
        KafkaSend send = new KafkaSend("kafka.conf");
        DateTime start = DateTime.now();
        for(int i=0;i<10000;i++){
//            send.send(new CarMsg(i));
        }
        System.out.println(Seconds.secondsBetween(start,DateTime.now()).getSeconds());
        Thread.sleep(10000L);
    }


    @Override
    public void send(CarMsg input) throws Exception {
        concurrentLinkedQueue.offer(input.toKafkaStringV1());
    }

}
