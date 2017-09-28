

/**
 * @author ehl
 */
package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.common.EhlConfiguration
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
 *
 * @author ehl
 */
trait SparkOp extends ParserFactory{


  /**
    * 获取spark app name
    *
    * @return
    */
  def getSparkAppName:String

  /**
    * 設置hadoop配置
    */
  def setHadoopConfig(sc:SparkContext):Unit

  def initEhlConfig:EhlConfiguration


  val ehlConf=initEhlConfig
  /**
    * sparkContext
    *(op:(SparkContext)=>Unit)
    */
  def operateSpark(args:Array[String],ehlConf:EhlConfiguration)(op:SparkContext=>Unit)

}