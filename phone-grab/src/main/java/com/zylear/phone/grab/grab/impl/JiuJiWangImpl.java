package com.zylear.phone.grab.grab.impl;

import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.grab.WebGrabInterface;
import com.zylear.phone.grab.pinterface.GrabInterface;
import com.zylear.phone.grab.util.SiteFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by 28444 on 2017/10/18.
 */
@Component("jiuJiWang")
public class JiuJiWangImpl extends BaseWebGrab implements WebGrabInterface {

    private String urlTemplate = "http://www.9ji.com/list/2-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-%s.html";
    private int maxSearchPage = 20;
    private static final Logger logger = LoggerFactory.getLogger(JiuJiWangImpl.class);

    public JiuJiWangImpl() {
        thisSource = "http://www.9ji.com/";
    }

    @Override
    public void getWebInfo() {
        int currentPage = 1;
        int chance = 1;
        int count = 1;

        WebDriver webDriver = getWebDriver(new FirefoxProfile());



        while (currentPage <= maxSearchPage) {
            webDriver.get(String.format(urlTemplate, currentPage));
            if (currentPage == 1) {
                try {
                    WebElement webElement = webDriver.findElement(By.cssSelector(".city-wrap.hide.unselected .close"));
                    Thread.sleep(1000);
                    webElement.click();
                } catch (Exception e) {
                    logger.warn("click close button exception");
                }
            }

            try {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                Thread.sleep(1000);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(2000);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.info("滚动条异常 source:{}", thisSource, e);
            }

            String content = webDriver.getPageSource();
            Document doc = Jsoup.parse(content);

            Elements elements = doc.select(".list>a0|ul>a0|li");

            if (elements.size() < 10 && chance == 1) {
                chance = 0;
                continue;
            }

            for (Element element : elements) {
                count++;
                String title = element.select(".shopTitle").text();
                double price = 0;
                try {
                    price = Double.parseDouble(element.select(".p_price").text().replace("￥", "").trim());
                } catch (NumberFormatException e1) {
                    logger.info("价格异常");
                    continue;
                }
                String link = element.select(".propic.relative").attr("href");
                String img = element.select(".propic.relative a0|img").attr("src");
                logger.info("count:{}  price:{}   title:{}   link:{}   img:{}", count, price, title, link, img);

                try {
                    if (price < 50) {
                        System.out.println("价格小于50，不要了");
                        continue;
                    }
                    odsPhoneInfoMapper.insertBased(new OdsPhoneInfo(title, link, img, thisSource, price));
                } catch (Exception e1) {
                    logger.info("插入异常 ");
                }
            }

            currentPage++;
            chance = 1;
        }
    }


    @Override
    public void getDetailWebInfo() {


        int count = 1;

        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);

        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkk");
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {

//            if (count < 1340) {
//                count++;
//                continue;
//            }
//            if (count > 100) {
//                break;
//            }
            //  System.out.println(phoneInfo.getLink());

            Document document = null;
            try {
                document = Jsoup.connect("http://www.9ji.com"+odsPhoneInfo.getLink())
                        .header("User-Agent", SiteFactory.getUserAgent())
                        .get();
            } catch (IOException e) {
                System.out.println("网页打不开!");
//                continue;
                break;
            }


            String brand = "其他";
            String size = "其他";
            String ram = "其他";
            String pixel = "其他";
            String rom = "其他";
            String cpu = "其他";
            String model = "其他";

            Elements elements = document.select(".product tbody tr ");

            int i = 1;

//            if (elements.size() == 0) {
//                //第二种页面采集
//                System.out.println("第一种方法采集不了信息，请使用第二种方法！:采集到如下：");
//                elements = document.select("div.detail-norm.detail-box tr");
//            }

            for (Element element : elements) {
                String string = element.select("th").text();
                String value = element.select(" td span").text();

                if ("品牌：".equals(string)) {
                    brand = value;
                } else if ("屏幕尺寸".equals(string)) {
                    size = value;
                } else if ("RAM容量".equals(string)) {
                    ram = value;
                } else if ("后置摄像头像素".equals(string)) {
                    pixel = value;
                } else if ("ROM容量".equals(string)) {
                    rom = value;
                } else if ("核心数".equals(string)) {
                    cpu = value;
                } else if ("型号：".equals(string)) {
                    model = value;
                }
            }

            if ("其他".equals(brand)) {
                String string = document.select("#CsDetailShow li a").text();
                if (!"".equals(string)) {
                    brand = string;
                }
            }


            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   "
                    + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());
            System.out.println(count);
            count++;
            try {
                odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
                odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
            } catch (Exception e) {
                System.out.println("插入异常!");
            }
        }

    }


    public static void main(String[] args) {
        new JiuJiWangImpl().getWebInfo();
    }
}
