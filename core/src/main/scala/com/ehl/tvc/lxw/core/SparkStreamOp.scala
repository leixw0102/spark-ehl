package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.common.EhlConfiguration
import com.ehl.tvc.lxw.core.parser.StreamingParams
import org.apache.spark.streaming.StreamingContext

/**
 * @author ehl
 */
trait SparkStreamOp extends EhlSpark{

  override def argumentClass=new StreamingParams().getClass

  def operateStreamingSpark(arg:Array[String],conf:EhlConfiguration)(op:StreamingContext=>Unit)


}