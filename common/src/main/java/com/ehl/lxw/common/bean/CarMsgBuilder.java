package com.ehl.lxw.common.bean;

import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by 雷晓武 on 2017/8/8.
 */
public class CarMsgBuilder {
    public static CarMsgBuilder getCustom(){
        return new CarMsgBuilder();
    }
    private String version;

    public String getVersion() {
        return version;
    }

    public CarMsgBuilder setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * 解析
     * TODO utf-8 比较麻烦
     * 考虑 protof
     *
     * @param byteBuffer
     * @return
     */
    @Deprecated
    public static CarMsg  toBuilderByByteBuffer(ByteBuffer byteBuffer){
        CarMsgBuilder carMsgBuilder = getCustom();

        byte[] magicByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(magicByte);
        String magic = new String(magicByte);
        if(Strings.isNullOrEmpty(magic) || !magic.equals("ehl-webservice")){
            return null;
        }
        //version
        byte[] versionByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(versionByte);
        carMsgBuilder.setVersion(new String(versionByte));
        versionByte=null;
        //tgsj
        byte[] tgsjByte=new byte[byteBuffer.getInt()];
        byteBuffer.get(tgsjByte);
        carMsgBuilder.setTgsj(new String(tgsjByte));
        tgsjByte=null;
        //cltxzt
        byte[] cltxztByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(cltxztByte);
        carMsgBuilder.setCltxzt(new String(cltxztByte));

        //hphm
        byte[] hphmByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(hphmByte);
        try {
            carMsgBuilder.setHphm(new String(hphmByte,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            carMsgBuilder.setHphm(new String(hphmByte));
        }
        hphmByte =null;
        //hpzl
        byte[] hpzlByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(hpzlByte);
        carMsgBuilder.setHpzl(new String(hpzlByte));
        hpzlByte=null;
        //xssd
        byte[] xssdByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(xssdByte);
        carMsgBuilder.setXssd(new String(xssdByte));
        xssdByte=null;
        //hpys
        byte[] hpysByte=new byte[byteBuffer.getInt()];
        byteBuffer.get(hpysByte);
        try {
            carMsgBuilder.setHpys(new String(hpysByte,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            carMsgBuilder.setHpys(new String(hpysByte));

        }
        hpysByte=null;
        //sbbh
        byte[] sbbhByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(sbbhByte);
            carMsgBuilder.setSbbh(new String(sbbhByte));
        sbbhByte=null;
        //cdbh
        byte[] cdbhByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(cdbhByte);
        carMsgBuilder.setCdbh(new String(cdbhByte));
        cdbhByte=null;
        //cxfx
        byte[]cxfxByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(cxfxByte);
        try {
            carMsgBuilder.setCxfx(new String(cxfxByte,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            carMsgBuilder.setCxfx(new String(cxfxByte));

        }
        cxfxByte=null;
        //zpfx
        byte[]zpfxByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(zpfxByte);
        carMsgBuilder.setZpfx(new String(zpfxByte));
        zpfxByte=null;
        //csys
        byte[]csysByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(csysByte);
        try {
            carMsgBuilder.setCsys(new String(csysByte,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            carMsgBuilder.setCsys(new String(csysByte));
        }
        csysByte=null;
        //clpp
        byte[] clppByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(clppByte);
        carMsgBuilder.setClpp(new String (clppByte));
        clppByte=null;
        //clzwpp
        byte[] clzwppByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(clzwppByte);
        try {
            carMsgBuilder.setClzwpp(new String (clzwppByte,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            carMsgBuilder.setClzwpp(new String(clppByte));

        }
        clzwppByte=null;
//kkbh
        byte[] kkbhByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(kkbhByte);
        carMsgBuilder.setKkbh(new String (kkbhByte));
        kkbhByte=null;
        //cpzb

        byte[] cpzbByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(cpzbByte);
        carMsgBuilder.setCpzb(new String (cpzbByte));
        cpzbByte=null;
        //jsszb

        byte[] jsszbByte = new byte[byteBuffer.getInt()];
        byteBuffer.get(jsszbByte);
        carMsgBuilder.setJsszb(new String (jsszbByte));
        jsszbByte=null;

        byte[] tplj1Byte = new byte[byteBuffer.getInt()];
        byteBuffer.get(tplj1Byte);
        carMsgBuilder.setTplj1(new String (tplj1Byte));
        tplj1Byte=null;

        byte[] tplj2Byte = new byte[byteBuffer.getInt()];
        byteBuffer.get(tplj2Byte);
        carMsgBuilder.setTplj2(new String (tplj2Byte));
        tplj2Byte=null;
        byte[] tplj3Byte = new byte[byteBuffer.getInt()];
        byteBuffer.get(tplj3Byte);
        carMsgBuilder.setTplj3(new String (tplj3Byte));
        tplj3Byte=null;
        return carMsgBuilder.toBuilder();
    }





    /**
     * time /nu/卡口
     * @return
     */
    public static CarMsgBuilder getTestCustom(){
        CarMsgBuilder builder = getCustom();
        builder.setTgsj("leixw").setTplj1("http://www.baidu.com/ls/sdf/sdf/sdf/1223")
                .setTplj2("http://www.baidu.com/ls/sdf/sdf/sdf/1223")
                .setTplj3("http://www.baidu.com/ls/sdf/sdf/sdf/1223")
                .setCdbh("4")
                .setClpp("10")
                .setCltxzt("running")
                .setClzwpp("宝马")
                .setCpzb("0.0")
                .setCsys("白色")
                .setCxfx("东向南")
                .setHpys("蓝白")
                .setHpzl("1")
                .setJsszb("0.00,1.00")
                .setVersion("1.0")
//                .setKkbh()
        .setSbbh("10000")
                .setWebSystime("")
                .setZpfx("test");
        return builder;
    }

    public CarMsg toBuilder(){
        CarMsg carMsg = new CarMsg();
        carMsg.setCdbh(getCdbh());
        carMsg.setClpp(getClpp());
        carMsg.setCltxzt(getCltxzt());
        carMsg.setClzwpp(getClzwpp());
        carMsg.setCpzb(getCpzb());
        carMsg.setCsys(getCsys());
        carMsg.setCxfx(getCxfx());
        carMsg.setHphm(getHphm());
        carMsg.setHpys(getHpys());
        carMsg.setHpzl(getHpzl());
        carMsg.setJsszb(getJsszb());
        carMsg.setKkbh(getKkbh());
        carMsg.setSbbh(getSbbh());
        carMsg.setZpfx(getZpfx());
        carMsg.setXssd(getXssd());
        carMsg.setWebSystime(getWebSystime());
        carMsg.setTplj3(getTplj3());
        carMsg.setTplj2(getTplj2());
        carMsg.setTplj1(getTplj1());
        carMsg.setTgsj(getTgsj());
        carMsg.setVersion(getVersion());
        return carMsg;
    }

    private String webSystime;

    public String getWebSystime() {
        return webSystime;
    }

    public CarMsgBuilder setWebSystime(String webSystime) {
        this.webSystime = webSystime;
        return this;
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

    public CarMsgBuilder setClzwpp(String clzwpp) {
        this.clzwpp = clzwpp;
        return this;
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

    public CarMsgBuilder setCdbh(String cdbh) {
        this.cdbh = cdbh;
        return this;
    }

    public CarMsgBuilder setClpp(String clpp) {
        this.clpp = clpp;
        return this;
    }

    public CarMsgBuilder setCltxzt(String cltxzt) {
        this.cltxzt = cltxzt;
        return this;
    }

    public CarMsgBuilder setCpzb(String cpzb) {
        this.cpzb = cpzb;
        return this;
    }

    public CarMsgBuilder setCsys(String csys) {
        this.csys = csys;
        return this;
    }

    public CarMsgBuilder setCxfx(String cxfx) {
        this.cxfx = cxfx;
        return this;
    }

    public CarMsgBuilder setHphm(String hphm) {
        this.hphm = hphm;
        return this;
    }

    public CarMsgBuilder setHpys(String hpys) {
        this.hpys = hpys;
        return this;
    }

    public CarMsgBuilder setHpzl(String hpzl) {
        this.hpzl = hpzl;
        return this;
    }

    public CarMsgBuilder setJsszb(String jsszb) {
        this.jsszb = jsszb;
        return this;
    }

    public CarMsgBuilder setKkbh(String kkbh) {
        this.kkbh = kkbh;
        return this;
    }

    public CarMsgBuilder setSbbh(String sbbh) {
        this.sbbh = sbbh;
        return this;
    }

    public CarMsgBuilder setTgsj(String tgsj) {
        this.tgsj = tgsj;
        return this;
    }

    public CarMsgBuilder setTplj1(String tplj1) {
        this.tplj1 = tplj1;
        return this;
    }

    public CarMsgBuilder setTplj2(String tplj2) {
        this.tplj2 = tplj2;
        return this;
    }

    public CarMsgBuilder setTplj3(String tplj3) {
        this.tplj3 = tplj3;
        return this;
    }

    public CarMsgBuilder setXssd(String xssd) {
        this.xssd = xssd;
        return this;
    }

    public CarMsgBuilder setZpfx(String zpfx) {
        this.zpfx = zpfx;
        return this;
    }



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
