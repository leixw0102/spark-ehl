package com.ehl.tvc.lxw.core.parser

/**
  * Created by 雷晓武 on 2017/1/12.
  * 解析shell传递参数
  */
trait ArgumentParser {
    def parser(args:Array[String]):Unit

    def verify():Boolean=true;
}

class OfflineParams extends Serializable with ArgumentParser{
    var isYarn=false;
    var yarnFile=""
    var isEs = false;
    override def parser(args: Array[String]): Unit = {
        parser(args.toList)
    }
    def printUsageAndExit(i: Int): Unit = {
        System.err.println(
            "Usage: process data [options]\n" +
              "\n" +
              "Options:\n" +
              "  -isYarn  true/false \n"+
              "  -yarnFile \n"+
              "  -isEs use es \n"+
              "  --help println help.\n" +
              "                         Default is conf/spark-defaults.conf.")
        System.exit(i)
    }

    def parser(args: List[String]): Unit = args match {
        case ("-isYarn") :: value :: tail =>
            isYarn = value.toBoolean;
            parser(tail)

        case ("-yarnFile") :: value :: tail =>
            yarnFile = value
            parser(tail)
        case ("-isEs") :: value :: tail =>
            isEs=value.toBoolean
            parser(tail)
        case ("--help") :: tail =>
            printUsageAndExit(0)

        case Nil => {}

        case _ =>
            printUsageAndExit(1)
    }

    override def toString=yarnFile

}

class StreamingParams extends Serializable with ArgumentParser{

    var duration=1;
    var isYarn=false;
    var checkPointPath = ""
    var yarnFile=""
    var isEs = false
    def printUsageAndExit(i: Int): Unit = {
        System.err.println(
            "Usage: process data [options]\n" +
              "\n" +
              "Options:\n" +
              "  -d duration     streaming.batch.duration=1 \n" +
              "  -path check point path  \n" +
              "  -isYarn  true/false \n"+
              "  -yarnFile \n"+
              "  -isEs defalut false \n"+
              "  --help println help.\n" +
              "                         Default is conf/spark-defaults.conf.")
        System.exit(i)
    }
    override def parser(args:Array[String]): Unit ={
        parser(args.toList)
    }
    def parser(args: List[String]): Unit = args match {
        case ("-d") :: value :: tail=>
            duration = Option(value).getOrElse("1").toInt
            parser(tail)
        case ("-path") :: value :: tail=>
            checkPointPath = value
            parser(tail)
        case ("-isEs") :: value :: tail=>
            isEs = value.toBoolean
            parser(tail)
        case ("-isYarn") :: value :: tail =>
            isYarn = value.toBoolean;
            parser(tail)

        case ("-yarnFile") :: value :: tail =>
            yarnFile = value
            parser(tail)
        case ("--help") :: tail =>
            printUsageAndExit(0)

        case Nil => {}

        case _ =>
            printUsageAndExit(1)
    }

    override def toString()={duration+"\t"+checkPointPath}
}

