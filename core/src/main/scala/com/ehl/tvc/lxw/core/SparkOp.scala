

/**
 * @author ehl
 */
package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.common.EhlConfiguration
import com.ehl.tvc.lxw.core.parser.OfflineParams
import org.apache.spark.{SparkConf, SparkContext}

/**
  *
 *
 * @author ehl
 */
trait SparkOp extends EhlSpark{



  /**
    * 設置hadoop配置
    */
  def setHadoopConfig(sc:SparkContext):Unit


  override def argumentClass=new OfflineParams().getClass


  /**
    * sparkContext
    *(op:(SparkContext)=>Unit)
    */
  def operateSpark(args:Array[String],ehlConf:EhlConfiguration)(op:SparkContext=>Unit)

}