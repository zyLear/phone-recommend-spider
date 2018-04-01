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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/24.
 */

@Service("feiNiuWang")
public class FeiNiuWangImpl extends BaseWebGrab implements WebGrabInterface {

    public FeiNiuWangImpl() {
        thisSource = "http://www.feiniu.com/";
    }


    @Override
    public void getWebInfo() {

        int p = 1;
        int i = 0;
        int MAX = 37;

        WebDriver ffDriver = BaseChromeDriver.getWebDriver();// getWebDriver(new FirefoxProfile());

        ffDriver.get("http://search.feiniu.com/?q=%E6%89%8B%E6%9C%BA&tp=search.0.3006.6.1490171856228VP8P&page=" + p);
        try {
            WebElement next = ffDriver.findElement(By.className("J_close_distpop"));
            Thread.sleep(1000);
            next.click();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("找不到元素哦");
        }


        while (p <= MAX) {

            //让滚动条分阶段滑下
            try {
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) ffDriver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*2/8)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*3/8)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*4/8)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*5/8)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*6/8)");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String content = ffDriver.getPageSource();

            Document doc = Jsoup.parse(content); //,"GBK"
            Elements linkElements = doc.select(".prod.J-tab-c");// .res-info


            for (Element e : linkElements) {
                i++;

                String title = e.select(".p-name.J_p_name a").attr("title");
                double price = 0;
                try {
                    price = Double.parseDouble(e.select(".J_now_price").text());
                } catch (Exception e1) {
                    System.out.println("价格异常");
                    continue;
                }
                String link = e.select(".p-img.J_p_img a").attr("href");
                String img = e.select(".p-img.J_p_img img").attr("data-original");

                System.out.println(i + "   " + price + "    " + title + "   " + link + "    " + img);

                try {
                    if (price < 50) {
                        System.out.println("价格小于50，不要了");
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

            ffDriver.get("http://search.feiniu.com/?q=%E6%89%8B%E6%9C%BA&tp=search.0.3006.6.1490171856228VP8P&page=" + p);
        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if(ffDriver != null)
//            ffDriver.close();
        System.out.println("飞牛网采集结束！");
    }

    @Override
    public void getDetailWebInfo() {
        int count = 1;

        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);

        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {
//            if (count < 1340) {
//                count++;
//                continue;
//            }
//            if (count > 500) {
//                break;
//            }
            //  System.out.println(phoneInfo.getLink());

            Document document = null;
            try {
                document = Jsoup.connect(odsPhoneInfo.getLink())
                        .headers(getHeaders())
                        .get();
            } catch (IOException e) {
                System.out.println("网页打不开!");
                continue;
            }


            String brand = "其他";
            String size = "其他";
            String ram = "其他";
            String pixel = "其他";
            String rom = "其他";
            String cpu = "其他";
            String model = "其他";

            Elements elements = document.select("#pro-detail-property .proSpecifications-table tr");

            int i = 1;

            if (elements.size() == 0) {
                //第二种页面采集
                System.out.println("第一种方法采集不了信息，请使用第二种方法！:采集到如下：");
                elements = document.select("div.detail-norm.detail-box tr");
            }

            for (Element element : elements) {
                String string = element.select("td:first-child").text();

                if ("品牌：".equals(string)) {
                    brand = element.select("td:nth-child(2)").text();
                } else if (string.matches(".*(手机屏幕尺寸：|屏幕尺寸：).*")) {
                    String var = element.select("td:nth-child(2)").text();
                    if (!var.contains("以官")) {
                        size = var;
                    }
                } else if ("运存(RAM)：".equals(string)) {
                    ram = element.select("td:nth-child(2)").text();
                } else if (string.matches(".*(主摄像头像素：|主摄像头：).*")) { //主摄像头：|
                    String var = element.select("td:nth-child(2)").text();
                    if (!var.contains("以官")) {
                        pixel = var;
                    }
                } else if ("内存(ROM)：".equals(string)) {
                    rom = element.select("td:nth-child(2)").text();
                } else if ("CPU核心数：".equals(string)) {
                    cpu = element.select("td:nth-child(2)").text();
                } else if ("型号：".equals(string)) {
                    model = element.select("td:nth-child(2)").text();
                }
            }


            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());
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


    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>(8);
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "guid=4B4CA524-AA58-4C26-8E69-015e7a445b28; first_login_time=1505289853736; cart_token=9dff9b7955392024c8a675e452f25929_1505289864364; C_dist=CPG1_CS000016; C_dist_area=CS000016_310100_310104_3101040005; access=7; Qs_lvt_73574=1505833346%2C1506151035%2C1506227596%2C1506265642%2C1509976656; abToken=78; TS019feb4b=01cfbf1eb52c11d8b9a098248aa76583d4391b3fdfdda7fa08e70c3d371ae7ac0a27591dde89bec1707076dbd12aa0221a7c08e8e7; _jzqckmp_v2=1/; _jzqckmp=1/; fncps_lt=%7B%22adr_id%22%3A%22C00000001%22%2C%22alliance_id%22%3A%22emar%3Acps%3A17598%3AODQxNDY5fDAweTU1YzkwZDEzZDAxMjYzM2Zl%22%7D; TS0146dacf=01cfbf1eb5754a1fb2ba2441543c80ee27580e7f5f8df05b0138e237b8b5841bc7351b0a662e49dc24dc9fe10150e43e76f01fb0b8; ITEM_HISTORY_LIST=204707_90301045497_0%2C0_KS1201703CG070000118_0%2C0_KZ1201612CG140000159_0%2C0_KS1201702CG080000004_0%2C0_KS1170790301081941_0%2C0_KS1170590300106054_0%2C0_KS1170990301808476_0%2C0_KS1170890301695277_0%2C204037_90105082329_0%2C204170_90301520595_0%2C0_KS1170590300138286_0%2C204707_90300913844_0%2C204682_90300982256_0%2C201680_90300167027_0%2C204170_90105552684_0%2C204037_90105389934_0%2C203164_90105316190_0%2C203813_90104960057_0%2C203458_90104577471_0%2C203813_90104961047_0%2C200782_100562332_0%2C200782_100561006_0%2C200247_100383889_0%2C200510_100242767_0%2C200510_100242750_0%2C0_KZ1201606CG280001699_0%2C204831_90301569810_0; Qs_pv_73574=2594273757266613000%2C3817606377481324000%2C270168348476593820%2C881303563329467900%2C2804153548905597400; _qzja=1.2123403325.1505289855131.1506265642901.1509976672849.1509976721108.1509976721127..0.0.44.6; _qzjb=1.1509976672849.6.0.0.0; _qzjc=1; _qzjto=6.1.0; _ga=GA1.3.1460083720.1505289856; _gid=GA1.3.870916829.1509976673; mediav=%7B%22eid%22%3A%22239578%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22%60%25a%25er.57p9yODz%3A%28IOJ%22%2C%22ctn%22%3A%22%22%7D");
        headers.put("Host", "item.feiniu.com");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", SiteFactory.getUserAgent());
        return headers;

    }
}
