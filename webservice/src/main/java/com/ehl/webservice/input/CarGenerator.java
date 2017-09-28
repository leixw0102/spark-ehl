package com.ehl.webservice.input;

import com.ehl.lxw.common.bean.CarMsg;
import com.ehl.lxw.common.bean.CarMsgBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * Created by 雷晓武 on 2017/8/8.
 */
public class CarGenerator {

    public static CarMsg getCarMsg(){
        CarMsgBuilder testBuilder = CarMsgBuilder.getTestCustom();
        testBuilder.setTgsj(System.currentTimeMillis()+"")
                .setKkbh(RandomUtils.nextLong(100000L,200000L)+"")
                .setWebSystime(System.currentTimeMillis()+"")
                .setHphm("津"+ RandomStringUtils.random(6,true,true));
        return testBuilder.toBuilder();
    }

    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            System.out.println(getCarMsg().toString()+"\r\n");
        }
    }

}
