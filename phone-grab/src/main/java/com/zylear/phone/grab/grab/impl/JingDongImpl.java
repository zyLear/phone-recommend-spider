package com.zylear.phone.grab.grab.impl;


import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.grab.WebGrabInterface;
import com.zylear.phone.grab.spider.manager.BaseChromeDriver;
import com.zylear.phone.grab.util.SiteFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/17.
 */
@Service("jingDong")
public class JingDongImpl extends BaseWebGrab implements WebGrabInterface {

    JingDongImpl() {
        thisSource = "https://www.jd.com/";
    }

    @Override
    public void getWebInfo() {
        final int MAX = 200;//205;
        int chance = 1;


        //FirefoxProfile firefoxProfile = new FirefoxProfile();
//        firefoxProfile.setPreference("permissions.default.image", 2);
//        firefoxProfile.setPreference("browser.migration.version", 9001);
        //firefoxProfile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false); //关flash
//        firefoxProfile.setPreference("permissions.default.stylesheet", 2);
        //  ffDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //网页加载3秒超时

        WebDriver ffDriver = BaseChromeDriver.getWebDriver();//getWebDriver(new FirefoxProfile());


        int page = 1;
        int count = 1;

        while (page < MAX) {

            try {
                System.out.println("************************************这一页已经结束******************************************");
                ffDriver.get("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&offset=3&suggest=1.his.0.0&cid2=653&cid3=655&page=" + page + "&s=58&click=0");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("page加载超时！");
                chance = 1;
                page += 2;
            }


            if (page == 1) {
                try {
                    ffDriver.get("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&offset=3&suggest=1.his.0.0&cid2=653&cid3=655&page=1&s=58&click=0");
                    WebElement sss = ffDriver.findElement(By.className("btn-sure"));
                    Thread.sleep(500);
                    sss.click();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("找不到按钮或者加载超时，不处理！");
                }
            }


            try {
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) ffDriver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*2/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*4/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            String content = ffDriver.getPageSource();
            Document doc = Jsoup.parse(content);
            Elements elements = doc.select("#J_goodsList .gl-item");

            if (elements.size() == 0) {
                System.out.println("已经没有内容，跳出循环");
                break;
            } else if (elements.size() <= 30 && chance == 1) {
                chance--;
                continue;
            }


            for (Element e : elements) {

                if ("广告".equals(e.select(".p-promo-flag").text())) {
                    System.out.println("广告");
                    continue;
                }

                //String a;
                String title = e.select("[class=\"p-name p-name-type-2\"] em").text();
                double price = 0;
                try {
                    price = Double.parseDouble(e.select(".p-price strong i").text());
                } catch (Exception e1) {
                    System.out.println("价格异常");
                    continue;
                }

                String link = e.select(".p-img a").attr("href").trim();

                String img = e.select(".p-img a img").attr("data-lazy-img").trim();
                if ("".equals(img) || "done".equals(img)) {
                    img = e.select(".p-img a img").attr("src");
                }

                System.out.print(count + "." + price);
                System.out.print("     " + title);
                System.out.print("     " + link);
                System.out.print("     " + img + "\n");

                try {
                    if (price < 50) {
                        continue;
                    }
                    odsPhoneInfoMapper.insertBased(new OdsPhoneInfo(title, link, img, thisSource, price));
                } catch (Exception e1) {
                    System.out.println("插入异常");
                }
                chance = 1;
                count++;
            }

            page = page + 2;

        }
    }

    @Override
    public void getDetailWebInfo() {
        int count = 1;

        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);
        System.out.println("size" + odsPhoneInfoList.size());
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {
            if (odsPhoneInfo.getBrand() != null) {
                System.out.println("已经搞过了");
                continue;
            }
//            if (count > 20) {
//                break;
//            }
            //  System.out.println(phoneInfo.getLink());
            Document document = null;
            try {
                document = Jsoup.connect("https:" + odsPhoneInfo.getLink())
                        .header("User-Agent", SiteFactory.getUserAgent())
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("网页打不开! link:" + odsPhoneInfo.getLink());
                continue;
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
                        model = dd.get(i).text();
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
                            System.out.println(pixel + "    qqqqqqqqqqq    " + odsPhoneInfo.getLink());
                        }
                    } else if ("ROM".equals(element.text())) {
                        rom = td.get(i + 1).text();
                    } else if ("CPU核数".equals(element.text())) {
                        cpu = td.get(i + 1).text();
                    } else if ("型号".equals(element.text())) {
                        model = td.get(i + 1).text();
                    }
                }
            }
            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());
            System.out.println(count);
            count++;
            try {
                odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
                odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
            } catch (Exception e) {
            }
        }
    }
}
