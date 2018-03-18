package com.zylear.phone.grab.grab.impl;

import javafx.application.Application;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by 28444 on 2017/9/23.
 */


public class TestFiuNiuWang {


    @Test
    public void testGetDetail() {

//            if (count < 3605) {
//                count++;
//                continue;
//            }
//            if (count > 20) {
//                break;
//            }
        //  System.out.println(phoneInfo.getLink());

        Document document = null;
        try {
            document = Jsoup.connect("http://item.feiniu.com/KS1170790301177957").get();
        } catch (IOException e) {
            System.out.println("网页打不开!");
            return;
        }


        System.out.println(document);
        String brand = "其他";
        String size = "其他";
        String ram = "其他";
        String pixel = "其他";
        String rom = "其他";
        String cpu = "其他";
        String model = "其他";

//        Elements elements = document.select("div.detail-norm.detail-box tr");
        Elements elements = document.select("#pro-detail-property .proSpecifications-table tr");


        int i = 1;

        if (elements.size() == 0) {
            System.out.println("第一种方法采集不了信息，请使用第二种方法！采集到的如下信息：");
            elements = document.select("div.detail-norm.detail-box tr");
        }

        //第一种页面采集
        for (Element element : elements) {
            String string = element.select("td:first-child").text();

            if ("品牌：".equals(string)) {
                brand = element.select("td:nth-child(2)").text();
            } else if (string.matches("屏幕尺寸\\(描述\\)：|屏幕尺寸：")) {
                size = element.select("td:nth-child(2)").text();
            } else if ("运存(RAM)：".equals(string)) {
                ram = element.select("td:nth-child(2)").text();
            } else if ("主摄像头：".equals(string)) {
                pixel = element.select("td:nth-child(2)").text();
            } else if ("内存(ROM)：".equals(string)) {
                rom = element.select("td:nth-child(2)").text();
            } else if ("CPU核心数：".equals(string)) {
                cpu = element.select("td:nth-child(2)").text();
            } else if ("型号：".equals(string)) {
                model = element.select("td:nth-child(2)").text();
            }
        }

        System.out.println("   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   ");
        System.out.println();
//        count++;
//        try {
//            odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
//            odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
//        } catch (Exception e) {
//            System.out.println("插入异常!");
//        }

    }

}
