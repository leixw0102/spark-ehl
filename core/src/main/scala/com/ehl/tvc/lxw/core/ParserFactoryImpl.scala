package com.ehl.tvc.lxw.core

/**
  * Created by 雷晓武 on 2018/1/9.
  */
class ParserFactoryImpl extends ParserFactory{

    override def getParserParameter[ArgumentParser](cls: Class[ArgumentParser]): Option[ArgumentParser] = {
//        assert(cls ==null,"parameter cls is null")
        val obj = cls.newInstance();
        if(obj.isInstanceOf[ArgumentParser]) Option(obj.asInstanceOf[ArgumentParser]) else None
}
}
