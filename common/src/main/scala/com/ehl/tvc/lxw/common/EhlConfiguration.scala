package com.ehl.tvc.lxw.common

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import java.util.concurrent.ConcurrentHashMap

import com.ehl.lxw.common.PropertiesLoaderUtils

/**
  * Created by 雷晓武 on 2016/12/12.
  */
class EhlConfiguration(file:String="default.conf") extends Cloneable with Serializable{

  val default_file = file;

  /**
    *
    */
  private val settings = new ConcurrentHashMap[String, String]()

  addResource(default_file)


  def foreach()={
    for((k,v)<- settings){
      println(k+"\t"+v)
    }
  }

  def toMap():Map[String,String]=settings.toMap

  def getStartWithNameMap(name:String):Map[String,String]={
    val kvs = for{(k,v)<- settings
                  if k.startsWith(name)}yield {(k,v)}
    kvs.toMap
  }

  def addResource(conf:String):EhlConfiguration={
    val pro = Option(PropertiesLoaderUtils.loadAllProperties(conf))
    pro match {
      case Some(p)=>
        for(k <- p.keySet()){
          settings.put(k.toString,p.getProperty(k.toString))
        }
      case None=>println(s"property = $conf not exist ")
    }

    this;
  }

  def addResource(map:Map[String,String]):EhlConfiguration={
    settings.putAll(map);
    this
  }

  def getOption(key: String): Option[String] = {
    Option(settings.get(key)) //get or else
  }

  /**
    *
    * @param key
    * @param value
    * @return
    */
  def set(key: String, value: String): EhlConfiguration = {
    if(key ==null || key.isEmpty){
      throw new NullPointerException("null key")
    }

    if( value == null || value.isEmpty){
      throw new NullPointerException("null value for " + key)
    }

    settings.put(key,value)
    this;
  }


  /**
    *
    * @param key
    * @return
    */
  def get(key: String): String = {
    getOption(key).getOrElse(throw new NoSuchElementException(key))
  }
  def get(key: String, defaultValue: String): String = {
    getOption(key).getOrElse(defaultValue)
  }

  def getInt(key:String, defaultValue: Int):Int={
    getOption(key).map(_.toInt).getOrElse(defaultValue)
  }

  def getLong(key:String, defaultValue: Long):Long={
    getOption(key).map(_.toLong).getOrElse(defaultValue)
  }

  def getDouble(key:String, defaultValue: Double):Double={
    getOption(key).map(_.toDouble).getOrElse(defaultValue)
  }

  def getShort(key:String, defaultValue: Short):Short={
    getOption(key).map(_.toShort).getOrElse(defaultValue)
  }

  def getBoolean(key:String,defaultValue:Boolean=false):Boolean={
    getOption(key).map(_.toBoolean).getOrElse(defaultValue)
  }

  override def clone():EhlConfiguration={
    super.clone().asInstanceOf[EhlConfiguration];
  }

}

/**
  *
  */
object EhlConfiguration{
  def apply(file: String = "default.conf"): EhlConfiguration = new EhlConfiguration(file)
}
