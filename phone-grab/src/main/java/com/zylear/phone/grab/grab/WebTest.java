package com.zylear.phone.grab.grab;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/17.
 */
public class WebTest {



    @Test
    public void testGrep() {
        if ("魅族 魅蓝E 全网通版 3G+32GB（A680Q）月光银 移动联通电信4G手机现货速发 顺丰包邮 购机送钢化贴膜和保护套 指纹支付 双卡双待 5.5寸大屏 全国联保".matches(".*魅族.*")) {
            System.out.println("匹配成功");
        } else {
            System.out.println("匹配失败");
        }
    }

    @Test
    public void secondTest(){
        Document document = null;
        try {
            document = Jsoup.connect("http://product.suning.com/0000000000/940000124.html").get();
            System.out.println(document.select("#J-procon-param .prods-show-rel").size());
            System.out.println(document.select("#J-procon-param .prods-show-rel").first().attr("id"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("网页打不开!");
        }
        System.out.println("end");
     //   System.out.println(document);
    }

    @Test
    public void testt(){
       // http://product.suning.com/pds-web/ajax/itemParameter_000000000142929518_R1901001_10051.html
        Document document = null;
        try {
            document = Jsoup.connect("http://product.suning.com/pds-web/ajax/itemParameter_000000000142929518_R1901001_10051.html")
                    .ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("网页打不开!");
        }
        System.out.println(document.body());
    }

    @Test
    public void JingdongDetailTest(){
        Document document = null;
        try {
            document = Jsoup.connect("http://item.feiniu.com/90301061187?tp=search.0.3007-item39.1.1503575944707feKr").get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("网页打不开!");
        }
        System.out.println(document);
    }

    @Test
    public void testSplit() {
        String[] rams = "123456".split("G");
        System.out.println(rams[0]);
    }

    @Test
    public void testGetNumber(){
        String string="5000+200万像素；500万像素；2116万像素；2256万像素";
        String[] pixels = string.split("万|\\+");
      //  System.out.println(Double.parseDouble(string.replaceAll("[^0-9]","")));
        double dPixel=0;

        for (String pp : pixels) {

            try {
                double temp=Double.parseDouble(pp.replaceAll("[^0-9]",""));
                if (temp > dPixel) {
                    dPixel=temp;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        System.out.println(dPixel+"     size:"+pixels.length);

    }


    @Test
    public void TestJingDongDetailByTheSecondWay(){

        int count=1;
        Document document = null;
        try {
            document = Jsoup.connect("http://item.jd.com/11839691432.html").get();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("网页打不开!");

        }

        Elements dt = document.select(".Ptable .Ptable-item dt");
        Elements dd = document.select(".Ptable .Ptable-item dd:not(.Ptable-tips)");

        String brand = "其他";
        String size = "其他";
        String ram = "其他";
        String pixel = "其他";
        String rom = "其他";
        String cpu = "其他";
        String model = "其他";

        if (dt.size() != 0) {
            brand = document.select("#parameter-brand li a:first-child").text();
            for (int i = 0; i < dt.size(); i++) {
                Element element = dt.get(i);
                if ("品牌".equals(element.text())) {
                    if ("其他".equals(brand) || "".equals(brand.trim())) {
                        brand = dd.get(i).text();
                    }
                    //  System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("主屏幕尺寸（英寸）".equals(element.text())) {
                    size = dd.get(i).text();
                    //  System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("RAM".equals(element.text())) {
                    ram = dd.get(i).text();
                    // System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("后置摄像头".equals(element.text())) {
                    pixel = dd.get(i).text();
                    //  System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("ROM".equals(element.text())) {
                    rom = dd.get(i).text();
                    //  System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("CPU核数".equals(element.text())) {
                    cpu = dd.get(i).text();
                    // System.out.println(element.text() + "    " + dd.get(i).text());
                } else if ("型号".equals(element.text())) {
                    model=dd.get(i + 1).text();
                }
            }
        } else {
            System.out.println("全球购");
            Elements td = document.select("#specifications tbody td:not(.Ptable-tips)");
            for (int i = 0; i < td.size(); i++) {
                Element element = td.get(i);
                if ("品牌".equals(element.text())) {
                    brand = td.get(i + 1).text();
                } else if ("主屏幕尺寸（英寸）".equals(element.text())) {
                    size = td.get(i + 1).text();
                } else if ("RAM".equals(element.text())) {
                    ram = td.get(i + 1).text();
                } else if ("后置摄像头".equals(element.text())) {
                    pixel = td.get(i + 1).text();
                    if (pixel.length() > 30) {
                        pixel = "其他";
                    //    System.out.println("qqqqqqqqqqq    " + odsPhoneInfo.getLink());
                    }
                } else if ("ROM".equals(element.text())) {
                    rom = td.get(i + 1).text();
                } else if ("CPU核数".equals(element.text())) {
                    cpu = td.get(i + 1).text();
                } else if ("型号".equals(element.text())) {
                    model=td.get(i + 1).text();
                }
            }
        }
        System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu +"   "+model+ "   ");
        System.out.println(count);
        count++;
//        try {
//            odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu,model);
//            odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
//        } catch (Exception e) {
//
//        }
    }

}
