package com.ehl.lxw.common.bean;

import com.google.common.base.Strings;
import scala.util.control.Exception;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by 雷晓武 on 2017/8/7.
 */

/**
 * TODO 修改为protocol ,自带解析方便
 */
public class CarMsg {

    /**
     * 字段验证
     * @return
     * @throws java.lang.Exception
     */
    public boolean verify() throws java.lang.Exception{
        return true;
    }


    private String webSystime;

    public String getWebSystime() {
        return webSystime;
    }

    public void setWebSystime(String webSystime) {
        this.webSystime = webSystime;
    }

    /**
     * 号牌号码
     */
    private String hphm;
    /**
     * 号牌种类
     */
    private String hpzl;
    /**
     * 车道编号
     */
    private String cdbh;
    /**
     * 通过时间
     */
    private String tgsj;
    /**
     * 行驶速度
     */
    private String xssd;
    /**
     * 号牌颜色
     */
    private String hpys;
    /**
     * 车行方向
     */
    private String cxfx;
    /**
     * 抓拍方向
     */
    private String zpfx;
    /**
     * 车身颜色
     */
    private String csys;
    /**
     * 卡口编号
     */
    private String kkbh;
    /**
     * 设备编号
     */
    private String sbbh;
    /**
     * 车辆通行状态
     */
    private String cltxzt;
    /**
     * 车辆品牌
     */
    private String clpp;
    /**
     * 车辆中文品牌
     */
    private String clzwpp;

    public String getClzwpp() {
        return clzwpp;
    }

    public void setClzwpp(String clzwpp) {
        this.clzwpp = clzwpp;
    }

    /**
     * 车牌坐标
     */
    private String cpzb;
    /**
     * 驾驶室坐标
     */
    private String jsszb;
    /**
     * 图片路径1-全景图像
     */
    private String tplj1;
    /**
     * 图片路径2-特制图像
     */
    private String tplj2;
    /**
     * 图片路径3-号牌图像
     */
    private String tplj3;// 号牌图像

    public String getCdbh() {
        return cdbh;
    }

    public String getClpp() {
        return clpp;
    }

    public String getCltxzt() {
        return cltxzt;
    }

    public String getCpzb() {
        return cpzb;
    }

    public String getCsys() {
        return csys;
    }

    public String getCxfx() {
        return cxfx;
    }

    public String getHphm() {
        return hphm;
    }

    public String getHpys() {
        return hpys;
    }

    public String getHpzl() {
        return hpzl;
    }

    public String getJsszb() {
        return jsszb;
    }

    public String getKkbh() {
        return kkbh;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getTgsj() {
        return tgsj;
    }

    public String getTplj1() {
        return tplj1;
    }

    public String getTplj2() {
        return tplj2;
    }

    public String getTplj3() {
        return tplj3;
    }

    public String getXssd() {
        return xssd;
    }

    public String getZpfx() {
        return zpfx;
    }

    public void setCdbh(String cdbh) {
        this.cdbh = cdbh;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public void setCltxzt(String cltxzt) {
        this.cltxzt = cltxzt;
    }

    public void setCpzb(String cpzb) {
        this.cpzb = cpzb;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public void setCxfx(String cxfx) {
        this.cxfx = cxfx;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public void setHpys(String hpys) {
        this.hpys = hpys;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public void setJsszb(String jsszb) {
        this.jsszb = jsszb;
    }

    public void setKkbh(String kkbh) {
        this.kkbh = kkbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setTgsj(String tgsj) {
        this.tgsj = tgsj;
    }

    public void setTplj1(String tplj1) {
        this.tplj1 = tplj1;
    }

    public void setTplj2(String tplj2) {
        this.tplj2 = tplj2;
    }

    public void setTplj3(String tplj3) {
        this.tplj3 = tplj3;
    }

    public void setXssd(String xssd) {
        this.xssd = xssd;
    }

    public void setZpfx(String zpfx) {
        this.zpfx = zpfx;
    }

//    public String toKafkaString(VERSION v) {
//        switch (v) {
//            case V1:
//                return toKafkaStringV1();
//            default:
//                throw new RuntimeException("版本号错误");
//        }
//    }

    private String version="1.0";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 主要问题  汉字
     *
     * @return
     */
    @Deprecated
    public ByteBuffer toKafkaStringV1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        String magic="ehl-webservice";
        buffer.putInt(magic.length()).put(magic.getBytes());
        buffer.putInt(version.length()).put(version.getBytes());
        tgsj = Strings.nullToEmpty(tgsj);
        buffer.putInt(tgsj.length()).put(tgsj.getBytes());
        cltxzt = Strings.nullToEmpty(cltxzt);
        buffer.putInt(cltxzt.length()).put(cltxzt.getBytes());
        hphm = Strings.nullToEmpty(hphm);
        buffer.putInt(hphm.getBytes(Charset.forName("utf-8")).length).put(hphm.getBytes(Charset.forName("utf-8")));
        hpzl = Strings.nullToEmpty(hpzl);
        buffer.putInt(hpzl.length()).put(hpzl.getBytes());
        xssd = Strings.nullToEmpty(xssd);
        buffer.putInt(xssd.length()).put(xssd.getBytes());
        hpys = Strings.nullToEmpty(hpys);
        buffer.putInt(hpys.getBytes(Charset.forName("utf-8")).length).put(hpys.getBytes(Charset.forName("utf-8")));
        sbbh = Strings.nullToEmpty(sbbh);
        buffer.putInt(sbbh.length()).put(sbbh.getBytes());
        cdbh = Strings.nullToEmpty(cdbh);
        buffer.putInt(cdbh.length()).put(cdbh.getBytes());
        cxfx = Strings.nullToEmpty(cxfx);
        buffer.putInt(cxfx.getBytes(Charset.forName("utf-8")).length).put(cxfx.getBytes(Charset.forName("utf-8")));
        zpfx = Strings.nullToEmpty(zpfx);
        buffer.putInt(zpfx.length()).put(zpfx.getBytes());
        csys = Strings.nullToEmpty(csys);
        buffer.putInt(csys.getBytes(Charset.forName("utf-8")).length).put(csys.getBytes(Charset.forName("utf-8")));
        clpp = Strings.nullToEmpty(clpp);
        buffer.putInt(clpp.length()).put(clpp.getBytes());
        clzwpp = Strings.nullToEmpty(clzwpp);
        buffer.putInt(clzwpp.getBytes(Charset.forName("utf-8")).length).put(clzwpp.getBytes(Charset.forName("utf-8")));
        kkbh = Strings.nullToEmpty(kkbh);
        buffer.putInt(kkbh.length()).put(kkbh.getBytes());
        cpzb = Strings.nullToEmpty(cpzb);
        buffer.putInt(cpzb.length()).put(cpzb.getBytes());
        jsszb = Strings.nullToEmpty(jsszb);
        buffer.putInt(jsszb.length()).put(jsszb.getBytes());
        tplj1 = Strings.nullToEmpty(tplj1);
        buffer.putInt(tplj1.length()).put(tplj1.getBytes());
        tplj2 = Strings.nullToEmpty(tplj2);
        buffer.putInt(tplj2.length()).put(tplj2.getBytes());
        tplj3 = Strings.nullToEmpty(tplj3);
        buffer.putInt(tplj3.length()).put(tplj3.getBytes());
        buffer.flip();
        return buffer.asReadOnlyBuffer();
    }

    @Override
    public String toString() {
        return "CarMsg{" +
                "webSystime='" + webSystime + '\'' +
                ", hphm='" + hphm + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", cdbh='" + cdbh + '\'' +
                ", tgsj='" + tgsj + '\'' +
                ", xssd='" + xssd + '\'' +
                ", hpys='" + hpys + '\'' +
                ", cxfx='" + cxfx + '\'' +
                ", zpfx='" + zpfx + '\'' +
                ", csys='" + csys + '\'' +
                ", kkbh='" + kkbh + '\'' +
                ", sbbh='" + sbbh + '\'' +
                ", cltxzt='" + cltxzt + '\'' +
                ", clpp='" + clpp + '\'' +
                ", clzwpp='" + clzwpp + '\'' +
                ", cpzb='" + cpzb + '\'' +
                ", jsszb='" + jsszb + '\'' +
                ", tplj1='" + tplj1 + '\'' +
                ", tplj2='" + tplj2 + '\'' +
                ", tplj3='" + tplj3 + '\'' +
                '}';
    }
}