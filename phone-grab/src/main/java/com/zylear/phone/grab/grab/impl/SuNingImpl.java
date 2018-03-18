package com.zylear.phone.grab.grab.impl;


import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.grab.WebGrabInterface;
import com.zylear.phone.grab.util.SiteFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection.Response;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/13.
 */
@Service("suNing")
public class SuNingImpl extends BaseWebGrab implements WebGrabInterface {
//    static {
//        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.SEVERE);
//    }


    Set<String> suningDetailPartSet = new HashSet<>();

    {
        suningDetailPartSet.add("主体");
        suningDetailPartSet.add("CPU");
        suningDetailPartSet.add("存储");
        suningDetailPartSet.add("屏幕");
        suningDetailPartSet.add("拍照");
    }

    SuNingImpl() {
        thisSource = "https://www.suning.com/";
    }

    private String ajaxUrlTemplate = "http://product.suning.com/pds-web/ajax/itemParameter_%s_R1901001_10051.html";

    @Override
    public void getWebInfo() {
        WebDriver ffDriver = getWebDriver(new FirefoxProfile());
        try {
            ffDriver.get("http://list.suning.com/0-20006-0.html");
        } catch (Exception e) {
            //do nothing
        }
        int p = 0;
        int i = 0;
        // WebElement next=ffDriver.findElement(By.className("r-btn"));

        while (p < 101) {

            try {
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) ffDriver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*2/8)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*3/8)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*4/8)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*5/8)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*6/8)");
                Thread.sleep(1800);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*7/8)");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //
            //
            String content = ffDriver.getPageSource();
            //
            Document doc = Jsoup.parse(content);
            Elements elements = doc.select("#filter-results .border-in>div[class^=wrap]");// .res-info

            // System.out.println(ee);
            for (Element e : elements) {
                i++;
                String title = e.select(".sellPoint").text();
                double price = 0;
                try {
                    //
                    price = Double.parseDouble(e.select("[class=prive price]").text().replace(e.select("[class=\"prive price\"] b").text(), "").trim());
                } catch (NumberFormatException e1) {
                    System.out.println("价格异常");
                    continue;
                }
                String link = e.select(".res-img a").attr("href");
                String img = e.select(".res-img img").attr("src");
                System.out.println(i + "   " + price + "    " + title + "   " + link + "    " + img);
                try {
                    if (price < 50) {
                        continue;
                    }
                    odsPhoneInfoMapper.insertBased(new OdsPhoneInfo(title, link, img, thisSource, price));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.out.println("插入异常");
                }
            }
            System.out.println(i);
            p++;
            try {
                ffDriver.get("http://list.suning.com/0-20006-" + p + ".html");
            } catch (Exception e) {
                //do nothing
            }
        }

        if (ffDriver != null) {
            ffDriver.close();
            ffDriver = null;
        }
    }


    @Override
    public void getDetailWebInfo() {

        int count = 1;
        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {
//            if (count < 150) {
//                count++;
//                continue;
//            }
//            if (count > 400) {
//                break;
//            }
            //  System.out.println(phoneInfo.getLink());
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Document document;
            try {

                document = Jsoup.connect("https:" + odsPhoneInfo.getLink())
                        .headers(getHeaders())
                        .timeout(10000).get();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("网页打不开!");
                continue;
            }
            // System.out.println(document);
            Elements elements = document.select("table#itemParameter.pro-para-tbl tbody tr");

            String brand = "其他";
            String size = "其他";
            String ram = "其他";
            String pixel = "其他";
            String rom = "其他";
            String cpu = "其他";
            String model = "其他";

            if (elements.isEmpty()) {
                try {
                    System.out.println("都是其他第二种方法:" + odsPhoneInfo.getLink());
                    String string = document.select("#J-procon-param .prods-show-rel").first().attr("id");
                    String phoneNumber = string.substring(string.lastIndexOf("_") + 1);
                    System.out.println(phoneNumber);
                    String url = String.format(ajaxUrlTemplate, phoneNumber);
                    Response response = null;
                    try {
                        response = Jsoup.connect(url).header("User-Agent",SiteFactory.getUserAgent()).ignoreContentType(true).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("suning ajax异常 link:"+odsPhoneInfo.getLink());
                        continue;
                    }


                    String jsonString = response.body();
                    JSONObject var = new JSONObject(jsonString);
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
                            //   System.out.println(snparameterdesc + ":" + snparameterVal);
                            if ("品牌".equals(snparameterdesc)) {
                                brand = snparameterVal;
                            } else if ("屏幕尺寸".equals(snparameterdesc) && snparameterVal.contains("英寸")) {
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
                } catch (Exception e) {
                    System.out.println("json解析异常，可能不用解析");
                }
            } else {
                for (Element element : elements) {
                    String before = element.select("td").eq(0).text();
                    String after = element.select("td").eq(1).text();
                    if ("品牌".equals(before)) {
                        brand = after;
                    } else if ("屏幕尺寸".equals(before) && after.contains("英寸")) {
                        size = after;
                    } else if ("运行内存".equals(before)) {
                        ram = after;
                    } else if ("后摄像头".equals(before)) {
                        pixel = after;
                    } else if ("机身内存".equals(before)) {
                        rom = after;
                    } else if ("CPU核数".equals(before)) {
                        cpu = after;
                    } else if ("型号".equals(before)) {
                        model = after;
                    }
                }
            }
//            if (sign == 0) {
//                System.out.println("都是其他第二种方法22222222222222222:" + odsPhoneInfo.getLink());
//            }
            //    http://product.suning.com/pds-web/ajax/itemParameter_000000000602425577_R1901001_10051.html

            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " +
                    pixel + "   " + rom + "   " + cpu + "   " + model + odsPhoneInfo.getLink());
            System.out.println(count);
            count++;
            try {
                odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
                odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("插入失败");
            }
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>(8);
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "__wms=1510064815; SN_CITY=20_021_1000267_9264_01_12113_2_0; cityId=9264; districtId=12113; _snstyxuid=7DAE0852B07BOLP4; authId=si09DF7544B9B555CFEBB6841586160833; secureToken=48DE30EFCED2A9B49F0EF99E1D1C9E51; _snsr=direct%7Cdirect%7C%7C%7C; _snms=151006293040238097; smhst=726363561|0070128064a163128316|0000000000a609134383|0000000000a163125526|0000000000a185289319|0070079092a188940906|0000000000a188951235|0000000000a188940908|0000000000a173278777|0070079092a616643143|0070129296a628662301|0070086258a681150013|0070129296a167247579|0000000000a687105296|0000000000a171958805|0070129296a626377268|0000000000a177942249|0000000000a945015589|0000000000a602425577|0000000000a945015565|0000000000; _gat=1; _snma=1%7C150806535111884860%7C1508065351118%7C1510063015544%7C1510063029805%7C50%7C8; _snmc=1; _snmp=151006302973841642; _snmb=15100629226503511%7C1510063029946%7C1510063029811%7C4; _ga=GA1.2.98239207.1508065352; _gid=GA1.2.1367881358.1510062923");
        headers.put("Host", "product.suning.com");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", SiteFactory.getUserAgent());
        return headers;
    }
}