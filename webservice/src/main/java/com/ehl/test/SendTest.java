//package com.ehl.test;
//
//import com.ehl.webservice.input.CarGenerator;
//import com.ehl.webservice.send.ISender;
//import com.ehl.webservice.send.KafkaSend;
//import com.google.common.base.Supplier;
//
///**
// * Created by 雷晓武 on 2017/8/8.
// */
//public class SendTest {
//    public static void main(String[] args) {
//
////        new ConsumerTest().start();
//        Supplier<ISender> supplier = ()->new KafkaSend("kafka.conf");
//        ISender send = supplier.get();
//        while (true){
//            try {
//                send.send(CarGenerator.getCarMsg());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            try {
////                Thread.sleep(Long.parseLong(args[0]));
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
