package com.ehl.tvc.lxw.core.offline

import java.io.File

import com.ehl.tvc.lxw.common.EhlConfiguration
import com.ehl.tvc.lxw.core.SparkOp
import com.ehl.tvc.lxw.core.parser.{ArgumentParser, OfflineParams}
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext, SparkFiles}


/**
  * Created by 雷晓武 on 2016/12/6.
  */
abstract class AbstractSparkEhl extends SparkOp with App{
  val logger = Logger.getLogger(getClass)
  def getESConfig(ehlConfiguration: EhlConfiguration):Map[String,String]={
    ehlConfiguration.getStartWithNameMap("es")
  }

  override def getParserParameter:ArgumentParser=new OfflineParams

  /**
    * 設置hadoop配置
    */
  override def setHadoopConfig(sc: SparkContext): Unit = {
  }

   def operateSpark(args:Array[String],ehlConf:EhlConfiguration)(op:SparkContext=>Unit){

    //first
    val conf=new SparkConf().setAppName(getSparkAppName).set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
    val parser = getParserParameter.asInstanceOf[OfflineParams]
     parser.parser(args)
//     if(parser.isEs){
//       val map = getESConfig(ehlConf)
//       conf.setAll(map)
//     }

    val session = new SparkContext(conf)
    try{

      if(parser.isYarn){
        val kv=session.textFile(".sparkStaging/"+session.getConf.getAppId+"/"+parser.yarnFile).filter(f=>f.startsWith("#")==false).filter(f=>f.split("=").length==2)
          .map(f=>{
          val arr = f.split("=")
          (arr(0),arr(1))
        }).collect().toMap

        ehlConf.addResource(kv)
      }
      setHadoopConfig(session)
      op(session)
    }catch{
      case ex:Exception=>ex.printStackTrace()
    } finally{
      //end
      session.stop()
    }
  }

  def exitDirectoryWithHadoop(path:String,fs:FileSystem): Boolean ={
    fs.exists(new Path(path))
  }

}
