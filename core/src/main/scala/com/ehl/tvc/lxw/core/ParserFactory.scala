package com.ehl.tvc.lxw.core

import com.ehl.tvc.lxw.core.parser.ArgumentParser

/**
  * Created by 雷晓武 on 2017/9/19.
  */
trait ParserFactory {
    def getParserParameter:ArgumentParser
}
