package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.core.parser.ArgumentParser

import scala.util.Try

/**
  * Created by 雷晓武 on 2017/9/19.
  */
trait ParserFactory {
//    def getParserParameter:ArgumentParser =

//    def getParserParameter(className:String):Option[ArgumentParser]

    def getParserParameter[T](cls:Class[T]):Option[T]

}


