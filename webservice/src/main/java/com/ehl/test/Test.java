package com.ehl.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import scala.util.control.Exception;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by 雷晓武 on 2017/8/7.
 */
public class Test {


    public ByteBuffer toByteBuffer(int i){
        ByteBuffer b = ByteBuffer.allocate(20);

        b.putInt(10+i);
        b.putChar('a');
        b.asCharBuffer().put("hello");
        b.flip();
        return b.asReadOnlyBuffer();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Test te = new Test();
//        for(int i=0;i<10;i++){
//            ByteBuffer buffer = te.toByteBuffer(i);
//            ByteBuffer b = te.toByteBuffer(i+1);
//            System.out.println(b.asCharBuffer().get());
//            System.out.println(len);
//            byte[] v = new byte[len];
//            b.get(v);
//            System.out.println(new String(v));
//            System.out.println(buffer.getInt()+"\t"+buffer.getChar()+"\t"+new String(buffer.get()+"\t"+buffer.get()
//                    +"....");
//            System.out.println(b.getInt()+"\t"+b.getChar()+"\t"+b.get()+"\t"+b.get());
//            b=null;
//        }
//        System.out.println(RandomUtils.nextInt(2,5));
//        System.out.println("hello world");
        System.out.println(RandomStringUtils.randomAlphabetic(5));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String a = "hello word";
        System.out.println(a.length());
        buffer.putInt(a.length());
        buffer.put(a.getBytes());
        String h="津hl2183";
        System.out.println(h.length());
        System.out.println(h.getBytes("utf-8").length);
        buffer.putInt(h.getBytes("utf-8").length).put(h.getBytes("utf-8"));


        buffer.flip();
        byte[] a1 = new byte[buffer.getInt()];
        buffer.get(a1);
        System.out.println(new String(a1));

        byte[] b1 = new byte[buffer.getInt()];
        buffer.get(b1);
        System.out.println(new String(b1,"utf-8"));
    }
}
