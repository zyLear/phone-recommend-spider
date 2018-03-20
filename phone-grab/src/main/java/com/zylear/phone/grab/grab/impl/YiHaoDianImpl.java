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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/8/24.
 */
@Service("yiHaoDian")
public class YiHaoDianImpl extends BaseWebGrab implements WebGrabInterface {

    private static Logger logger = LoggerFactory.getLogger(YiHaoDianImpl.class);

    YiHaoDianImpl() {
        thisSource = "http://www.yhd.com/";
    }

    @Override
    public void getWebInfo() {


        WebDriver ffDriver = getWebDriver();

        int p = 1;
        int i = 0;
        int chance = 1;


        while (p < 40) {
            ffDriver.get("http://search.yhd.com/c0-0/mbname-b/a-s1-v4-p39-price-d0-f0-m1-rt0-pid-mid0-color-size-k%E6%89%8B%E6%9C%BA/#page=" + p + "&sort=1");

            if (p == 1) {
                try {
                    WebElement sss = ffDriver.findElement(By.cssSelector(".close_btn"));
                    Thread.sleep(1000);
                    sss.click();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("找不到按钮哦");
                }
            }


            try {
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) ffDriver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*2/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*3/6)");
                Thread.sleep(1500);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight*4/6)");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            String content = ffDriver.getPageSource();
            Document doc = Jsoup.parse(content);//, "UTF-8"
            Elements elements = doc.select("#plist .mod_search_pro");
            // org.jsoup.select.Elements
            // elements=doc.getElementsByAttribute("target");

            if (elements.size() < 10 && chance == 1) {
                chance = 0;
                continue;
            }

            for (Element e : elements) {
                i++;

                String title = e.select(".proName.clearfix a:first-child").attr("title");
                double price = 0;
                try {
                    price = Double.parseDouble(e.select(".num").text().replace(e.select(".num b").text(), "").trim());
                } catch (NumberFormatException e1) {
                    System.out.println("价格错误");
                    continue;
                }
                String link = e.select(".proImg a").attr("href");
                String img = e.select(".proImg a img").attr("original");
                if (img.equals("")) {
                    img = e.select(".proImg a img").attr("src");
                }

                System.out.println(i + "   " + price + "    " + title + "   " + link + "    " + img);

                try {
                    if (price < 50) {
                        System.out.println("价格小于50，不要了");
                        continue;
                    }

                    odsPhoneInfoMapper.insertBased(new OdsPhoneInfo(title, link, img, thisSource, price));

                } catch (Exception e1) {
                    // e1.printStackTrace();
                    System.out.println("插入异常");
                }
            }
            System.out.println(i);
            // nextButton.click();
            p++;
            // WebElement submitButton =
            // ffDriver.findElement(By.className("fp-next"));
            // submitButton.click();
            chance = 1;
//            ffDriver.get(
//                    "http://list.yhd.com/c23586-0-81436/b/a-s1-v4-p1-price-d0-f0-m1-rt0-pid-mid0-k/?tp=52.23586.1502.0.1.LfNHcJx-00-F0vUq&ti=72RQKV#page="
//                            + p + "&sort=1");
//            ffDriver.get("http://search.yhd.com/c0-0/mbname-b/a-s1-v4-p39-price-d0-f0-m1-rt0-pid-mid0-color-size-k%E6%89%8B%E6%9C%BA/#page=" + p + "&sort=1");

        }


        //
        //

        // //5秒后关闭浏览器
        // try {
        // Thread.sleep(5000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // if(ffDriver != null)
        // ffDriver.close();
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

//            if (count > 50) {
//                break;
//            }
            //  System.out.println(phoneInfo.getLink());

            Response document = null;
            try {
                //http:+//item.yhd.com/5159242.html
                int start = odsPhoneInfo.getLink().lastIndexOf("/") + 1;
                int end = odsPhoneInfo.getLink().lastIndexOf(".");
                String phoneNumber = odsPhoneInfo.getLink().substring(start, end);
                //http://item.yhd.com/squ/item/ajaxGetDesc.do?params.channelId=0&params.skuId=17744707405&params.cat=9987,653,655&params.popType=1&params.venderId=654975&callback=detailProdDescCallback
                String yhdUrlTemplate = "http://item.yhd.com/squ/item/ajaxGetDesc.do?params.channelId=0&params.skuId=%s&params.cat=9987,653,655&params.popType=0&params.venderId=1000004123&callback=detailProdDescCallback";
                String url = String.format(yhdUrlTemplate, phoneNumber);


                document = Jsoup
                        .connect(url)
                        .header("User-Agent", SiteFactory.getUserAgent())
                        .ignoreContentType(true)
                        .headers(getHeaders("http:" + odsPhoneInfo.getLink()))
                        .execute();
            } catch (IOException e) {
                logger.warn("网页打不开!", e);
                count++;
                continue;
            }


            String primary = document.body();

            if (primary == null || "".equals(primary)) {
                logger.warn("fail to get ajaxGetDesc ");
                continue;
            }

            int start = primary.indexOf("(") + 1;
            int end = primary.lastIndexOf(")");
            String string = primary.substring(start, end);

            List<JSONObject> atts = new ArrayList<>();
            JSONObject jsonObject = null;


            String brand = "其他";
            String size = "其他";
            String ram = "其他";
            String pixel = "其他";
            String rom = "其他";
            String cpu = "其他";
            String model = "其他";

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


                    //    System.out.println(attName + ":" + vals);
                    if ("品牌".equals(attName)) {
                        brand = vals.toString();
                    } else if ("主屏幕尺寸（英寸）".equals(attName)) {
                        size = vals.toString();
                    } else if ("RAM".equals(attName)) {
                        ram = vals.toString();
                    } else if ("后置摄像头".equals(attName)) {
                        pixel = vals.toString();
                    } else if ("ROM".equals(attName)) {
                        rom = vals.toString();
                    } else if ("CPU核数".equals(attName)) {
                        cpu = vals.toString();
                    } else if ("型号".equals(attName)) {
                        model = vals.toString();
                    }


                }

            } catch (JSONException e) {
                logger.warn("no arrayJson formatString:{}", string, e);
            }


            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());


            count++;

            try {
                odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
                odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
            } catch (Exception e) {
                System.out.println("插入异常!");
            }
        }
        System.out.println("一号店detail_info采集结束");
    }


    private Map<String, String> getHeaders(String link) {
        Map<String, String> headers = new HashMap<>(8);
        headers.put("Accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.8");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "detail_yhdareas=1_1_3_%E4%B8%8A%E6%B5%B7_%E4%B8%8A%E6%B5%B7%E5%B8%82_%E9%BB%84%E6%B5%A6%E5%8C%BA; abtest=87; guid=Y5DZRD65ZC33F4H8JVBW52GUN4REVN99MHHQ; search_browse_history=64447355; cart_cookie_uuid=86b10991-fd52-41dd-a4d1-aecf2fc61c06; yhd_location=1_2816_6667_0; provinceId=1; cityId=2816; cart_num=0; __jda=81617359.15076488900081529386975.1507648890.1507955873.1508064308.5");
        headers.put("Host", "item.yhd.com");
        headers.put("Referer", link);
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent", SiteFactory.getUserAgent());
        return headers;
    }


    /*
    @Override
    public void getDetailWebInfo() {


        int count = 1;

        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);

        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {
//            if (count < 1340) {
//                count++;
//                continue;
//            }
            if (count > 330) {
                break;
            }
            //  System.out.println(phoneInfo.getLink());

            Document document = null;
            try {
                document = Jsoup.connect("http:" + odsPhoneInfo.getLink()).get();
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

            Elements elements = document.select("#prodDetailCotentDiv .des_info.clearfix dd");
            //#prodDetailCotentDiv .des_info.clearfix dd
            //.desitem.desqoute .standard dd

            int i = 1;

            for (Element element : elements) {
                String[] var = element.text().split("：");
                String string = "";
                String param = "";
                if (var.length >= 2) {
                    string = var[0];
                    param = var[1];
                }

                if ("品牌".equals(string)) {
                    brand = param;
                }
            }
//            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());



            elements = document.select(".desitem.desqoute .standard dd");
            //#prodDetailCotentDiv .des_info.clearfix dd
            //.desitem.desqoute .standard dd


            if (elements.size() == 0) {
                //第二种页面采集
                System.out.println("第一种方法采集不了信息，请使用第二种方法！:");
                // elements = document.select("div.detail-norm.detail-box tr");
            }

            for (Element element : elements) {
                String string = element.select("label").text();
                String param = element.text().replace(string, "").trim();

                if ("屏幕尺寸(英寸)".equals(string)) {
                    size = param;
                } else if ("运存(RAM)".equals(string)) {
                    ram = param;
                } else if ("后置摄像头(像素)".equals(string)) {
                    pixel = param;
                } else if ("机身内存".equals(string)) {
                    rom = param;
                } else if ("CPU核心数".equals(string)) {
                    cpu = param;
                } else if ("型号".equals(string)) {
                    model = param;
                }
            }


            System.out.println(count + "   " + brand + "   " + size + "   " + ram + "   " + pixel + "   " + rom + "   " + cpu + "   " + model + "   " + odsPhoneInfo.getLink());
            System.out.println(count);
            count++;
            try {
                odsPhoneInfo.setDetail(brand, size, ram, pixel, rom, cpu, model);
                //   odsPhoneInfoMapper.insertDetail(odsPhoneInfo);
            } catch (Exception e) {
                System.out.println("插入异常!");
            }

        }
    }

*/

    public static void main(String[] args) {
        new YiHaoDianImpl().getWebInfo();
    }


}
//item.yhd.com/10448830212.html