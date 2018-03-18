package com.zylear.phone.grab.grab.test;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 28444 on 2017/10/11.
 */
public class PureTest {

    @Test
    public void test() {


//        http://item.yhd.com/squ/item/ajaxGetDesc.do?params.channelId=0&params.skuId=10448830212&params.cat=9987,653,655&params.popType=0&params.venderId=1000004123&callback=detailProdDescCallback

        Response document = null;
        try {
            //http://item.yhd.com/5159242.html

            String url = " http://item.yhd.com/squ/item/ajaxGetDesc.do?params.channelId=0&params.skuId=10448830212&params.cat=9987,653,655&params.popType=0&params.venderId=1000004123&callback=detailProdDescCallback";
            System.out.println(url);
            document = Jsoup.connect(url).ignoreContentType(true).execute();
        } catch (IOException e) {
            System.out.println("网页打不开!");
            return;
        }


        String primary = document.body();
        int start = primary.indexOf("(") + 1;
        int end = primary.lastIndexOf(")");
        String string = primary.substring(start, end);

        List<JSONObject> atts = new ArrayList<>();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(string);
            JSONArray var = jsonObject.getJSONArray("data");
            JSONObject var2 = var.getJSONObject(1);
            JSONArray var3 = new JSONArray(var2.getString("tabDetail"));
            for (int i = 0; i < var3.length(); i++) {
                JSONObject var4 = var3.getJSONObject(i);
                JSONArray var5 = var4.getJSONArray("atts");
                for (int j = 0; j < var5.length(); j++) {
                    atts.add(var5.getJSONObject(j));
                }
            }

            for (JSONObject var6 : atts) {
                String attName = var6.getString("attName");
                StringBuilder vals = new StringBuilder();
                JSONArray var7 = var6.getJSONArray("vals");
                for (int i = 0; i < var7.length(); i++) {
                    vals.append(var7.getString(i)).append(";");
                }
                vals.deleteCharAt(vals.length() - 1);


                System.out.println(attName + ":" + vals);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("no arrayJson");
        }


    }

    @Test
    public void testTwo() {
        String string = "sdfsdf(133432)y";
        int start = string.indexOf("(") + 1;
        int end = string.lastIndexOf(")");
        System.out.println(string.substring(start, end));
    }


    private static int x = 100;

    public static void main(String args[]) {
        PureTest hs1 = new PureTest();
        PureTest.x++;
        PureTest hs2 = new PureTest();
        PureTest.x++;
        hs1 = new PureTest();
        PureTest.x++;
        PureTest.x--;
        System.out.println("x=" + x);
    }


    Set<String> suningDetailPartSet = new HashSet<>();

    @Test
    public void encodeSuningJson() {

        String string = "{\"sericeFlag\":false,\"smartFlag\":false,\"serice\":\"\",\"model\":\"\",\"knum\":\"\",\"classes\":\"\",\"brandName\":\"\",\"productName\":\"\",\"cccCode\":\"012200\",\"cccFlag\":false,\"coreParameterList\":[],\"eleParameterList\":[{\"主体\":[{\"snparameterCode\":\"000897\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"品牌\",\"snparameterVal\":\"三星(SAMSUNG)\",\"snparametersCode\":\"0001\",\"snparametersDesc\":\"主体\",\"snsequence\":\"01\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"001360\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"型号\",\"snparameterVal\":\"三星GALAXY S7\",\"snparametersCode\":\"0001\",\"snparametersDesc\":\"主体\",\"snsequence\":\"01\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"001012\",\"snparameterSequence\":\"005\",\"snparameterdesc\":\"上市时间\",\"snparameterVal\":\"2016.03\",\"snparametersCode\":\"0001\",\"snparametersDesc\":\"主体\",\"snsequence\":\"01\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"}]},{\"外观\":[{\"snparameterCode\":\"009715\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"颜色\",\"snparameterVal\":\"金色\",\"snparametersCode\":\"0002\",\"snparametersDesc\":\"外观\",\"snsequence\":\"02\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308474497181275.png\"},{\"snparameterCode\":\"001236\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"外形尺寸\",\"snparameterVal\":\"142.4×69.6×7.9毫米\",\"snparametersCode\":\"0002\",\"snparametersDesc\":\"外观\",\"snsequence\":\"02\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"MM\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308474497181275.png\"},{\"snparameterCode\":\"006985\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"重量\",\"snparameterVal\":\"152克\",\"snparametersCode\":\"0002\",\"snparametersDesc\":\"外观\",\"snsequence\":\"02\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"G.\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308474497181275.png\"}]},{\"系统\":[{\"snparameterCode\":\"001921\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"手机操作系统\",\"snparameterVal\":\"Android\",\"snparametersCode\":\"0003\",\"snparametersDesc\":\"系统\",\"snsequence\":\"03\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308473145517142.png\"},{\"snparameterCode\":\"001686\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"系统版本\",\"snparameterVal\":\"Android OS 6.0\",\"snparametersCode\":\"0003\",\"snparametersDesc\":\"系统\",\"snsequence\":\"03\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308473145517142.png\"},{\"snparameterCode\":\"001922\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"手机操控方式\",\"snparameterVal\":\"电容触摸屏\",\"snparametersCode\":\"0003\",\"snparametersDesc\":\"系统\",\"snsequence\":\"03\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308473145517142.png\"}]},{\"CPU\":[{\"snparameterCode\":\"006141\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"CPU核数\",\"snparameterVal\":\"四核\",\"snparametersCode\":\"0004\",\"snparametersDesc\":\"CPU\",\"snsequence\":\"04\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308466058432256.png\"},{\"snparameterCode\":\"011481\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"CPU频率\",\"snparameterVal\":\"1.6GHz,2.1GHz\",\"snparametersCode\":\"0004\",\"snparametersDesc\":\"CPU\",\"snsequence\":\"04\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308466058432256.png\"},{\"snparameterCode\":\"000020\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"CPU型号\",\"snparameterVal\":\"高通 骁龙820\",\"snparametersCode\":\"0004\",\"snparametersDesc\":\"CPU\",\"snsequence\":\"04\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308466058432256.png\"}]},{\"网络\":[{\"snparameterCode\":\"011482\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"4G网络制式\",\"snparameterVal\":\"移动4G,联通4G,电信4G\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"011483\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"3G网络制式\",\"snparameterVal\":\"移动3G,电信3G,联通3G\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"011484\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"2G网络制式\",\"snparameterVal\":\"电信2G,移动2G/联通2G\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"008733\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"待机模式\",\"snparameterVal\":\"双卡多模\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"009622\",\"snparameterSequence\":\"005\",\"snparameterdesc\":\"手机制式\",\"snparameterVal\":\"多模（TD-LTE+FDD-LTE+WCDMA+TD-SCDMA+GSM+CDMA2000/CDMA）\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"002542\",\"snparameterSequence\":\"006\",\"snparameterdesc\":\"SIM卡尺寸\",\"snparameterVal\":\"Nano SIM卡\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"001248\",\"snparameterSequence\":\"007\",\"snparameterdesc\":\"网络频率\",\"snparameterVal\":\"全网通\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"},{\"snparameterCode\":\"009681\",\"snparameterSequence\":\"008\",\"snparameterdesc\":\"数据业务\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0005\",\"snparametersDesc\":\"网络\",\"snsequence\":\"05\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308468128850662.png\"}]},{\"存储\":[{\"snparameterCode\":\"010664\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"机身内存\",\"snparameterVal\":\"32GB\",\"snparametersCode\":\"0006\",\"snparametersDesc\":\"存储\",\"snsequence\":\"06\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469101161912.png\"},{\"snparameterCode\":\"009130\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"运行内存\",\"snparameterVal\":\"4GB\",\"snparametersCode\":\"0006\",\"snparametersDesc\":\"存储\",\"snsequence\":\"06\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469101161912.png\"},{\"snparameterCode\":\"011485\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"最大存储扩展\",\"snparameterVal\":\"200G\",\"snparametersCode\":\"0006\",\"snparametersDesc\":\"存储\",\"snsequence\":\"06\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469101161912.png\"}]},{\"屏幕\":[{\"snparameterCode\":\"010025\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"屏幕尺寸\",\"snparameterVal\":\"5.1英寸\",\"snparametersCode\":\"0007\",\"snparametersDesc\":\"屏幕\",\"snsequence\":\"07\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"INC\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469940875198.png\"},{\"snparameterCode\":\"008099\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"屏幕分辨率\",\"snparameterVal\":\"2560×1440\",\"snparametersCode\":\"0007\",\"snparametersDesc\":\"屏幕\",\"snsequence\":\"07\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469940875198.png\"},{\"snparameterCode\":\"011486\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"屏幕材质\",\"snparameterVal\":\"AMOLED\",\"snparametersCode\":\"0007\",\"snparametersDesc\":\"屏幕\",\"snsequence\":\"07\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469940875198.png\"},{\"snparameterCode\":\"006390\",\"snparameterSequence\":\"009\",\"snparameterdesc\":\"屏幕尺寸\",\"snparameterVal\":\"12.95厘米\",\"snparametersCode\":\"0007\",\"snparametersDesc\":\"屏幕\",\"snsequence\":\"07\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"CM\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308469940875198.png\"}]},{\"拍照\":[{\"snparameterCode\":\"000470\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"前摄像头\",\"snparameterVal\":\"500万像素\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"MPI\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"},{\"snparameterCode\":\"012057\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"后摄像头\",\"snparameterVal\":\"1200万像素\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"MPI\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"},{\"snparameterCode\":\"012059\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"摄像头类型\",\"snparameterVal\":\"其他\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"},{\"snparameterCode\":\"001001\",\"snparameterSequence\":\"006\",\"snparameterdesc\":\"闪光灯类型\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"},{\"snparameterCode\":\"008545\",\"snparameterSequence\":\"007\",\"snparameterdesc\":\"传感器类型\",\"snparameterVal\":\"CMOS\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"与传统相机相比，传统相机使用“胶卷”作为其记录信息的载体，而数码相机的“胶卷”就是其成像感光元件，而且是与相机一体的，是数码相机的心脏。感光器是数码相机的核心，也是最关键的技术。目前数码相机的核心成像部件有两种：一种是广泛使用的CCD（电荷藕合）元件；另一种是CMOS（互补金属氧化物导体）器件。\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"},{\"snparameterCode\":\"009001\",\"snparameterSequence\":\"008\",\"snparameterdesc\":\"变焦模式\",\"snparameterVal\":\"数码变焦\",\"snparametersCode\":\"0008\",\"snparametersDesc\":\"拍照\",\"snsequence\":\"08\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308470640601304.png\"}]},{\"电池\":[{\"snparameterCode\":\"006788\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"电池容量\",\"snparameterVal\":\"3000mAh\",\"snparametersCode\":\"0009\",\"snparametersDesc\":\"电池\",\"snsequence\":\"09\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"S66\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308471305281357.png\"},{\"snparameterCode\":\"008912\",\"snparameterSequence\":\"005\",\"snparameterdesc\":\"电池更换\",\"snparameterVal\":\"不支持\",\"snparametersCode\":\"0009\",\"snparametersDesc\":\"电池\",\"snsequence\":\"09\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"http://image.suning.cn/uimg/PCMS/prmtExposition/149308471305281357.png\"}]},{\"接口\":[{\"snparameterCode\":\"008388\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"蓝牙版本\",\"snparameterVal\":\"蓝牙4.1\",\"snparametersCode\":\"0010\",\"snparametersDesc\":\"接口\",\"snsequence\":\"10\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"007885\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"耳机接口\",\"snparameterVal\":\"3.5mm\",\"snparametersCode\":\"0010\",\"snparametersDesc\":\"接口\",\"snsequence\":\"10\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"009677\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"数据线\",\"snparameterVal\":\"MICRO USB\",\"snparametersCode\":\"0010\",\"snparametersDesc\":\"接口\",\"snsequence\":\"10\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"008168\",\"snparameterSequence\":\"005\",\"snparameterdesc\":\"PC数据同步\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0010\",\"snparametersDesc\":\"接口\",\"snsequence\":\"10\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"}]},{\"手机特性\":[{\"snparameterCode\":\"006115\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"重力传感器\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0011\",\"snparametersDesc\":\"手机特性\",\"snsequence\":\"11\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"006113\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"光线传感器\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0011\",\"snparametersDesc\":\"手机特性\",\"snsequence\":\"11\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"006114\",\"snparameterSequence\":\"003\",\"snparameterdesc\":\"距离传感器\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0011\",\"snparametersDesc\":\"手机特性\",\"snsequence\":\"11\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"009281\",\"snparameterSequence\":\"004\",\"snparameterdesc\":\"陀螺仪\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0011\",\"snparametersDesc\":\"手机特性\",\"snsequence\":\"11\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"006117\",\"snparameterSequence\":\"007\",\"snparameterdesc\":\"OTG\",\"snparameterVal\":\"支持\",\"snparametersCode\":\"0011\",\"snparametersDesc\":\"手机特性\",\"snsequence\":\"11\",\"coreFlag\":\"\",\"explain\":\"OTG是On-The-Go的缩写，是近年发展起来的技术，主要应用于各种不同的设备或移动设备间的联接，进行数据交换。\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"}]},{\"其他\":[{\"snparameterCode\":\"011507\",\"snparameterSequence\":\"001\",\"snparameterdesc\":\"运营商标识\",\"snparameterVal\":\"无\",\"snparametersCode\":\"0012\",\"snparametersDesc\":\"其他\",\"snsequence\":\"12\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"},{\"snparameterCode\":\"009949\",\"snparameterSequence\":\"002\",\"snparameterdesc\":\"选购热点\",\"snparameterVal\":\"2K屏,三防手机,指纹识别\",\"snparametersCode\":\"0012\",\"snparametersDesc\":\"其他\",\"snsequence\":\"12\",\"coreFlag\":\"\",\"explain\":\"\",\"measureUnit\":\"\",\"recommendParamFlag\":\"\",\"prmtExpositionDetailsLink\":\"\",\"prmtExpositionText\":\"\",\"recomExpositionImgLink\":\"\",\"prmtGroupImgUrl\":\"\"}]}],\"visualParameterList\":[],\"recommendParameterList\":[]}";

        suningDetailPartSet.add("主体");
        suningDetailPartSet.add("CPU");
        suningDetailPartSet.add("存储");
        suningDetailPartSet.add("屏幕");
        suningDetailPartSet.add("拍照");
        String brand = "其他";
        String size = "其他";
        String ram = "其他";
        String pixel = "其他";
        String rom = "其他";
        String cpu = "其他";
        String model = "其他";
        try {
            JSONObject var = new JSONObject(string);

            JSONArray var2 = var.getJSONArray("eleParameterList");
            List<JSONArray> var3 = new ArrayList<>();
            loop:
            for (int i = 0; i < var2.length(); i++) {
                for (String var4 : suningDetailPartSet) {
                    if (var2.getJSONObject(i).has(var4)) {
                        var3.add(var2.getJSONObject(i).getJSONArray(var4));
                        continue loop;
                    }
                }
            }


            for (JSONArray var5 : var3) {
                for (int i = 0; i < var5.length(); i++) {
                    String snparameterdesc = var5.getJSONObject(i).getString("snparameterdesc");
                    String snparameterVal = var5.getJSONObject(i).getString("snparameterVal");
                    System.out.println(snparameterdesc + ":" + snparameterVal);
                    if ("品牌".equals(snparameterdesc)) {
                        brand = snparameterVal;
                    } else if ("屏幕尺寸".equals(snparameterdesc)) {
                        size = snparameterVal;
                    } else if ("运行内存".equals(snparameterdesc)) {
                        ram = snparameterVal;
                    } else if ("后摄像头".equals(snparameterdesc)) {
                        pixel = snparameterVal;
                    } else if ("ROM".equals(snparameterdesc)) {
                        rom = snparameterVal;
                    } else if ("CPU核数".equals(snparameterdesc)) {
                        cpu = snparameterVal;
                    } else if ("型号".equals(snparameterdesc)) {
                        model = snparameterVal;
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testtt() {
        String string = "{\"a\":1}";
        try {
            JSONObject jsonObject = new JSONObject(string);
            System.out.println();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoop() {
        for (int i = 1; i < 10; i++) {
            int b = 2;
            System.out.println(i);
            continue;
        }

        int k=3;
        for (int i=0;i<k;k--,i++) {
            System.out.println(k+" "+i);
        }
    }

    @Test
    public void cssSelector() {
        String string = "<ee:w> 我的 </ee:w>";
        Document document = Jsoup.parse(string);

        Elements elements = document.select("ee");

        for (Element element : elements) {
            System.out.println(element);
        }
    }

    @Test
    public void replaceAllTest() {
        String string = "wfd2df33ww.2wef3e!\\";
        System.out.println(string.replaceAll("[^0-9\\\\.]", ""));

        String string2 = "+f万dsf+d+";
        String[] pixels = string2.split("[万+]");
        System.out.println(pixels.length);
        for (String s : pixels) {
            System.out.println(s);
        }
    }

    @Test
    public void interruptedExceptionTest() {
        System.out.println("what ???");
        List<Thread> listThreads = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            final int tempCount = i;
            listThreads.add(new Thread(String.valueOf(i)) {

                @Override
                public void run() {
                    try {
                        // interrupt();
                        System.out.println("this is " + tempCount + " thread, sleep start  currentThreadName:" + currentThread().getName());

                        sleep(10000);
                        System.out.println("this is " + tempCount + " thread, sleep end");
                    } catch (InterruptedException e) {
                        System.out.println("我不想中断");
                        System.out.println("this is " + tempCount + " thread, sleep end");
                    }

                }
            });

            listThreads.get(i).start();

        }


//        for (int i = 0; i < 100; i++) {
//            try {
//                listThreads.get(i).interrupt();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        for (int i = 0; i < 100; i++) {
            try {
                listThreads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void interruptedExceptionSecondTest() {


//        Thread thread = new Thread() {
//            @Override
//            public void run() {


//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String aa="s";
        boolean ee=aa.matches("dsf");

        Pattern.compile("");
        Pattern.matches("sdf", "sdf");
        Matcher matcher;


        System.out.println("the value of interrupted()  :" + Thread.interrupted());
        System.out.println("after interrupted()  :" + Thread.currentThread().isInterrupted());

        Thread.currentThread().interrupt();
        System.out.println("after interrupt()  :" + Thread.currentThread().isInterrupted());

        System.out.println("the value of interrupted()  :" + Thread.interrupted());
        System.out.println("after interrupted()  :" + Thread.currentThread().isInterrupted());
        System.out.println("the value of interrupted()  :" + Thread.interrupted());
        System.out.println("after interrupted()  :" + Thread.currentThread().isInterrupted());

//        Thread.interrupted();
//                System.out.println("after interrupted()  :"+isInterrupted());


//            }
//        };


        try {
            int a = 2;
            if (a == 2) {
                throw new XzyException("xzy");
            }
        } catch (XzyException e) {
            System.out.println(e.getExceptionValue());
        }

        MyThread myThread = new MyThread();
        new MyThread().my();
    }


    @Test
    public void patternTest() {
//        Pattern pattern = new Pattern();

    }


    @Test
    public void RegexTest(){

        String content = "thisIsMy100Words!";
        String regex = "([a-zA-Z]*)(\\d*)(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        System.out.println("is matches?"+Pattern.matches(regex, content));
//        System.out.println(matcher.matches());
        if (matcher.find()){
            for (int i=0;i<=matcher.groupCount();i++ ) {
                System.out.println(matcher.group(i));
            }
        }
    }

    @Test
    public void usePatternTest(){
        String content = "<div> yes </div>" +
                "<a>the content is what I want</a>" +
                "<div>aaaa</div>" +
                "<a>second</a>";
        String regex = "(<a>)(.*?)(</a>)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }


    @Test
    public void PatternTest(){
        String content = "sfdsf/sdfdsfe/sdfds!@#$$^$%^fs.jpg";
        String regex = "(/)([^/]*)(\\.jpg)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }

    @Test
    public void testUpper(){
        String regex = "ASD";
        String content = "asd";
        System.out.println(content.matches(regex));


    }

    @Test
    public void testContains(){
        System.out.println("以官方参数为准".contains("以官"));
    }

}


class MyThread extends Thread {
    public static int my;

    public static void my() {

    }

}

class XzyException extends Exception {
    private String exceptionValue;

    XzyException(String value) {
        exceptionValue = value;
    }

    public String getExceptionValue() {
        return exceptionValue;
    }
}