package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.common.EhlConfiguration
import com.ehl.tvc.lxw.core.parser.ArgumentParser

/**
  * Created by 雷晓武 on 2018/1/9.
  */
trait EhlSpark extends App{
  /**
    * 启动脚本，参数解析
    * @return
    */
  def getParserFactory:ParserFactory=new ParserFactoryImpl

  def argumentClass:Class[_<:ArgumentParser]

  /**
    * 应用名称
    * @return
    */
  def appName:String

  /**
    * 获取配置；如果是yarn-cluster 模式则不许用实现
    * @return
    */
  def initEhlConfiguration:EhlConfiguration

  /**
    * 使用ehlConf获取参数
    */
  val ehlConf=initEhlConfiguration
}
