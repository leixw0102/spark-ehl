package com.ehl.lxw.common;


import com.ehl.lxw.common.bean.CarMsg;
import com.ehl.lxw.common.bean.CarMsgBuilder;
import com.ehl.lxw.common.reflect.Reflect;
import com.ehl.lxw.common.reflect.Reflect.FieldReflect;
import com.google.common.base.Function;
import com.google.common.collect.Maps;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by 雷晓武 on 2017/8/18.
 */
public class ObjectUtils {

    public static Map<String,Object> toMapByReflect(final Object obj) {
        Map<String,FieldReflect> maps = Reflect.auto(obj).fields(0);
        return Maps.transformValues(maps, new Function<FieldReflect, Object>() {
            @Override
            public Object apply( FieldReflect value) {
                return value.getValue(obj);
            }
        });
    }

    public static Map<String,Object> toMapByIntrospector(Object obj){

        if(null == obj){
            return null;
        }
        Map<String,Object> maps = Maps.newHashMap();
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass(),Object.class);
            for(PropertyDescriptor pd : info.getPropertyDescriptors()){
                Method reader = pd.getReadMethod();
                if (reader != null ) {
                    maps.put(pd.getName(), reader.invoke(obj));
                }
            }
            return maps;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        CarMsg msg = CarMsgBuilder.getTestCustom().toBuilder();
//        System.out.println(toMap(msg));
//        Map m = toMapByReflect(msg);
//        System.out.println(m.get("class"));
//        System.out.println(m);
    }
}
