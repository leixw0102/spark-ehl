//package com.ehl.tvc.lxw.service
//
//import java.nio.ByteBuffer
//import java.time.{Instant, LocalDateTime, ZoneId}
//
//import com.ehl.lxw.common.ObjectUtils
//import com.ehl.lxw.common.bean.CarMsgBuilder
//import com.ehl.tvc.lxw.common.EhlConfiguration
//import com.ehl.tvc.lxw.core.realtime.AbstractSparkStreamingWithEs
//import org.apache.spark.streaming.kafka010._
//import org.elasticsearch.spark._
///**
//  * Created by 雷晓武 on 2017/8/11.
//  */
//object RealServiceWithKafka extends AbstractSparkStreamingWithEs with App{
//  override def appName: String = "test spark2.2"
//
//  /**
//    * add stand
//    * @return
//    */
//  override def initEhlConfiguration: EhlConfiguration = {
//    val conf = System.getProperty("service-conf","service.conf")
//    val resources = new EhlConfiguration()
//    resources.addResource(conf)
//    resources
//  }
//
//  /**
//    *   println(System.getenv("SPARK_YARN_STAGING_DIR"))
//    */
//  operateStreamingSpark(args,ehlConf)(op=>{
//    /**
//      * consumer with kafka
//      */
//    val tops = Set(ehlConf.get("kafka.topic"))
//    val kafkaParams = Map[String, Object](
//      "bootstrap.servers" -> ehlConf.get("kafka.broker"),
//      "key.deserializer" -> Class.forName(ehlConf.get("kafka.key.deserializer")),
//      "value.deserializer" -> Class.forName(ehlConf.get("kafka.value.deserializer")),
//      "group.id" -> ehlConf.get("kafka.group"),
//      "auto.offset.reset" -> ehlConf.get("kafka.auto.offset.reset"),
//      "enable.auto.commit" -> (ehlConf.get("kafka.enable.auto.commit","false").toBoolean:java.lang.Boolean)
//    )
//    val streams = KafkaUtils.createDirectStream[Long,ByteBuffer](op,LocationStrategies.PreferConsistent
//      ,ConsumerStrategies.Subscribe[Long,ByteBuffer](tops,kafkaParams))
//
//    streams.foreachRDD(rdd=>{
//      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
//
//      rdd.mapPartitions(msg=>{
//        msg.map{r=>
//          val v = CarMsgBuilder.toBuilderByByteBuffer(r.value())
////          (v.getTgsj,v)
//          val maps = ObjectUtils.toMapByIntrospector(v);
//          maps.put("type",getType(v.getTgsj.toLong))
//          maps
//        }
//       }
//      ).saveToEs("ehl-test/{type}",getESConfig(ehlConf))
////        .saveToEsWithMeta("ehles/test-1",getESConfig(ehlConf))
//
//
//
////      rdd.foreachPartition(f=>{
//
////        f.map(a=>{
////          Thread.sleep(10)
////          val msg = CarMsgBuilder.toBuilderByByteBuffer(a.value())
////          //to es with partition
//////          println(msg.toString)
////
////        })
////        Thread.sleep(1000L)
////      })
////
////      /**
////        * TODO logic
////        */
////
//      streams.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
//    })
//  })
//
//  def getType(time:Long):String={
//    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault())
//    val hour = dateTime.getHour
//    (hour %2) match {
//      case 0 =>"test-2"
//      case 1 => "test-3"
//    }
//  }
//
//  println("hello sparking")
//}
