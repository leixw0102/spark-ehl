//package com.ehl.tvc.lxw.core.offline
//
//import com.ehl.tvc.lxw.common.EhlConfiguration
//import com.ehl.tvc.lxw.core.es.EsConfOp
//import org.apache.spark.{SparkConf, SparkContext}
//
//
///**
//  * Created by 雷晓武 on 2016/12/6.
//  */
//abstract class AbstractEhl extends AbstractSparkEhl with EsConfOp with App{
//
//  override def getESConfig(ehlConfiguration: EhlConfiguration):Map[String,String]= {
//     ehlConfiguration.getStartWithNameMap("es")
//  }
//
//}
