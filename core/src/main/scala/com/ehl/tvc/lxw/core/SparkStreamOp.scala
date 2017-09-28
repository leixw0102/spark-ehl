package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.common.EhlConfiguration
import org.apache.spark.streaming.StreamingContext

/**
 * @author ehl
 */
trait SparkStreamOp extends ParserFactory{

  def appName:String
  def initEhlConfiguration:EhlConfiguration

  val ehlConf= initEhlConfiguration

  def operateStreamingSpark(arg:Array[String],conf:EhlConfiguration)(op:StreamingContext=>Unit)

//  /**
//   * stream context;
//   * args
//   * 0--second
//   */
//  def operateStreamSpark(args: Array[String], conf:()=>EhlConfiguration)(op: (StreamingContext) => Unit) {
//    //first
//     val conf  = new SparkConf();
//    if (args.length < 1) {
//      println("Task takes one params,please set taskName ")
//       conf.setAppName("test").setMaster("local[*]")
//    }else{
//        conf.setAppName(args(0))
//    }
//
//    conf.set("es.index.auto.create", "true")
//    conf.set("es.nodes", ConfigLoader.getPropertyValueString("es.nodes"))
//    val sc = new SparkContext(conf)

//    val streamC = new StreamingContext(sc, Milliseconds(ConfigLoader.getPropertyValueString("process_cycle").toLong))
//    try {
//      op(streamC, args)
//      streamC.start()
//      streamC.awaitTermination()
//    } catch {
//      case ex: Exception => ex.printStackTrace()
//    } finally {
//      end
//      sc.stop()
//    }
//  }

}