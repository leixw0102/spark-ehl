package com.ehl.tvc.lxw.core.realtime

import com.ehl.tvc.lxw.common.EhlConfiguration
import com.ehl.tvc.lxw.core.SparkStreamOp
import org.apache.spark.{SparkConf, SparkFiles}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by 雷晓武 on 2017/8/11.
  */
abstract class AbstractSparkStreamingWithEs extends SparkStreamOp {

   def getESConfig(ehlConfiguration: EhlConfiguration):Map[String,String]={
    ehlConfiguration.getStartWithNameMap("es")
  }

//  override def getParserParameter:ArgumentParser=new StreamingParams

  override def operateStreamingSpark(arg:Array[String],ehlConf:EhlConfiguration)(op:StreamingContext=>Unit): Unit ={
      val conf = new SparkConf();
      conf.setAppName(appName).set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
//      if(arg.length==1){
//        conf.setMaster(arg(0))
//      }
    //parameter parser
     val parser = getParserFactory.getParserParameter(argumentClass).get
    parser.parser(arg)

    if(parser.isEs){
      val map = getESConfig(ehlConf)
      conf.setAll(map)
    }

    // default 1 second
    val ssc = new StreamingContext(conf,Seconds(parser.duration))

    try{
      ssc.checkpoint(parser.checkPointPath+System.nanoTime())
      if(parser.isYarn){
        ssc.sparkContext.addFile(parser.yarnFile)
        ehlConf.addResource(SparkFiles.get(parser.yarnFile))
      }

      op(ssc);
      ssc.start()
      ssc.awaitTermination()
    }catch {
      case e:Exception =>e.printStackTrace()
    }finally {

    }

  }

}
