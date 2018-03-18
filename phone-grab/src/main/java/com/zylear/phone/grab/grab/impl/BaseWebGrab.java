package com.zylear.phone.grab.grab.impl;


import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.domain.OdsPhoneProfiles;
import com.zylear.phone.grab.mapper.*;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/8/15.
 */
public abstract class BaseWebGrab {

    public String thisSource;

    @Resource
    public OdsPhoneInfoMapper odsPhoneInfoMapper;
    @Resource
    public OdsPhoneProfilesMapper odsPhoneProfilesMapper;
    @Resource
    public TempPhoneMapper tempPhoneMapper;
    @Resource
    public PhoneInfoMapper phoneInfoMapper;
    @Resource
    public PhoneProfilesMapper phoneProfilesMapper;


    static {
        System.setProperty("webdriver.firefox.marionette", "D://FireFox//驱动//geckodriver.exe");
        System.setProperty("webdriver.firefox.bin", "D://FireFox//firefox.exe");
    }


    protected WebDriver getWebDriver(FirefoxProfile firefoxProfile) {

        firefoxProfile.setPreference("browser.cache.disk.parent_directory", "D:\\FireFox\\缓存");
        firefoxProfile.setPreference("browser.cache.offline.parent_directory", "D:\\FireFox\\缓存");
        firefoxProfile.setPreference("browser.cache.disk.enable", true);
        firefoxProfile.setPreference("browser.cache.offline.enable", true);


//        FirefoxBinary firefoxBinary = new FirefoxBinary();
//        firefoxBinary.addCommandLineOptions("--headless");

        WebDriver ffDriver = new FirefoxDriver(firefoxProfile);
        ffDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  //找不到元素就等待 2 秒!!!!!!!!
        ffDriver.manage().window().maximize();
        return ffDriver;
    }

    public void copy() {
        System.out.println("copy -> temp 表开始");
        tempPhoneMapper.deleteBySource(thisSource);
        tempPhoneMapper.copy(thisSource);
        System.out.println("copy -> temp 表结束");

    }

    public void getOdsInfo() {
        odsPhoneProfilesMapper.deleteBySource(thisSource);
        odsPhoneInfoMapper.deleteBySource(thisSource);
        getWebInfo();
        getDetailWebInfo();
    }

    public void getOdsProfiles() {
        switchToProfiles();
    }

    public void merge() {
        update();
        add();
        delete();
    }

    public void deleteNoEffect() {
//        odsPhoneProfilesMapper.deleteBySource(thisSource);
//        odsPhoneInfoMapper.deleteBySource(thisSource);
//        tempPhoneMapper.deleteBySource(thisSource);
        //以上三个先不弄

        int a = phoneInfoMapper.deleteNoEffect(thisSource);
        System.out.println("source:" + thisSource + "   phone_info表里删除数据行数：" + a);
    }

    abstract public void getWebInfo();

    abstract public void getDetailWebInfo();


    private void add() {
        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getAdd(thisSource);
        // List<PhoneInfo> phoneInfoList = phoneInfoMapper.getPhoneInfoBySource(thisSource);
        System.out.println("add开始");
        int i = 1;
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {

            phoneInfoMapper.insert(odsPhoneInfo);
            phoneProfilesMapper.insert(odsPhoneInfo.getLink());
            //插入info
            //插入profiles
            System.out.println(i + "." + odsPhoneInfo.getLink());
            i++;
        }
        System.out.println("add结束");
    }

    private void update() {
        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getUpdate(thisSource);
        //List<PhoneInfo> phoneInfoList = phoneInfoMapper.getPhoneInfoBySource(thisSource);
        System.out.println("update开始");
        int i = 1;
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {


            phoneInfoMapper.update(odsPhoneInfo);
            phoneProfilesMapper.update(odsPhoneProfilesMapper.selectByLink(odsPhoneInfo.getLink()));
            //更新info
            //更新profiles

            System.out.println(i + "." + odsPhoneInfo.getLink());
            i++;
        }
        System.out.println("update结束");
    }

    private void delete() {
        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getDelete(thisSource);
        //List<PhoneInfo> phoneInfoList = phoneInfoMapper.getPhoneInfoBySource(thisSource);
        System.out.println("delete开始");
        int i = 1;
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {

            phoneInfoMapper.deleteByLink(odsPhoneInfo.getLink());
            //  phoneProfilesMapper.deleteByLink(odsPhoneInfo.getLink());
            //删除info   不用真正的删除了，只需要改一个 is_delete字段就可以了
            //删除profiles

            System.out.println(i + "." + odsPhoneInfo.getLink());
            i++;
        }
        System.out.println("delete结束");

    }


    private void switchToProfiles() {
        System.out.println("开始SwitchToProfiles---------------------");
        List<OdsPhoneInfo> odsPhoneInfoList = odsPhoneInfoMapper.getPhoneInfoBySource(thisSource);
        int i = 1;
        for (OdsPhoneInfo odsPhoneInfo : odsPhoneInfoList) {
//            System.out.println(phoneInfo.toString());
            System.out.println(i);
            i++;
            OdsPhoneProfiles odsPhoneProfiles = new OdsPhoneProfiles();
            odsPhoneProfiles.setLink(odsPhoneInfo.getLink());

            try {
                setBrand(odsPhoneInfo.getBrand(), odsPhoneProfiles);
                setPrice(odsPhoneInfo.getPrice(), odsPhoneProfiles);
                setSize(odsPhoneInfo.getSize(), odsPhoneProfiles);
                setRam(odsPhoneInfo.getRam(), odsPhoneProfiles);
                setPixel(odsPhoneInfo.getPixel(), odsPhoneProfiles);
                setRom(odsPhoneInfo.getRom(), odsPhoneProfiles);
                setCpu(odsPhoneInfo.getCpu(), odsPhoneProfiles);

                if (odsPhoneProfiles.getBrandOther() == 1) {
                    odsPhoneProfiles.setBrandOther(0);
                    setBrand(odsPhoneInfo.getTitle(), odsPhoneProfiles);
                    if (odsPhoneProfiles.getBrandOther() == 1) {
                        System.out.println(odsPhoneInfo.getTitle());
                    }
                }

            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("由于之前网页打不开，get信息为null，报出空指针异常，continue");
                continue;
            }

            try {
                odsPhoneProfilesMapper.insert(odsPhoneProfiles);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("插入失败！continue");
                continue;
            }
        }
        System.out.println("删除info中取不到profiles的");
        odsPhoneInfoMapper.deleteNoEffect(thisSource);
        System.out.println("结束SwitchToProfiles---------------------");
    }

    public void setBrand(String brand, OdsPhoneProfiles odsPhoneProfiles) {
        String apple = ".*(苹果|Apple).*";
        String mi = ".*(小米|红米).*";
        String honor = ".*(荣耀|HONOR).*";
        String samsung = ".*(三星|SAMSUNG).*";
        String meizu = ".*(魅族|魅蓝).*";
        String huawei = ".*(华为|HUAWEI).*";
        String oppo = ".*(OPPO|欧珀).*";
        String vivo = ".*(步步高|vivo).*";
        String nokia = ".*(诺基亚|NOKIA).*";
        String letv = ".*乐视.*";
        if (brand == null) {
            odsPhoneProfiles.setBrandOther(1);
            return;
        }
        if (brand.matches(apple)) {
            odsPhoneProfiles.setBrandApple(1);
        } else if (brand.matches(mi)) {
            odsPhoneProfiles.setBrandMi(1);
        } else if (brand.matches(honor)) {
            odsPhoneProfiles.setBrandHonor(1);
        } else if (brand.matches(samsung)) {
            odsPhoneProfiles.setBrandSamsung(1);
        } else if (brand.matches(meizu)) {
            odsPhoneProfiles.setBrandMeizu(1);
        } else if (brand.matches(huawei)) {
            odsPhoneProfiles.setBrandHuawei(1);
        } else if (brand.matches(oppo)) {
            odsPhoneProfiles.setBrandOppo(1);
        } else if (brand.matches(vivo)) {
            odsPhoneProfiles.setBrandVivo(1);
        } else if (brand.matches(nokia)) {
            odsPhoneProfiles.setBrandNokia(1);
        } else if (brand.matches(letv)) {
            odsPhoneProfiles.setBrandLetv(1);
        } else if (brand.matches(mi)) {
            odsPhoneProfiles.setBrandMi(1);
        } else {
            odsPhoneProfiles.setBrandOther(1);
        }
    }

    public void setPrice(double price, OdsPhoneProfiles odsPhoneProfiles) {
        if (price < 400) {
            odsPhoneProfiles.setPriceLess400(1);
        } else if (price >= 400 && price <= 1000) {
            odsPhoneProfiles.setPrice400to1000(1);
        } else if (price >= 1001 && price <= 1700) {
            odsPhoneProfiles.setPrice1001to1700(1);
        } else if (price >= 1701 && price <= 2800) {
            odsPhoneProfiles.setPrice1701to2800(1);
        } else if (price >= 2801 && price <= 4500) {
            odsPhoneProfiles.setPrice2801to4500(1);
        } else if (price > 4500) {
            odsPhoneProfiles.setPriceGreater4500(1);
        }
    }

    public void setSize(String size, OdsPhoneProfiles odsPhoneProfiles) {
        String sizes[] = size.split("英寸|-");
        double dSize = 0;
        try {
            dSize = Double.parseDouble(sizes[0].replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            odsPhoneProfiles.setSizeOther(1);
            return;
        }

        if (0.2 < dSize && dSize < 3.0) {
            odsPhoneProfiles.setSizeLess3p0(1);
        } else if (dSize >= 3.0 && dSize <= 4.5) {
            odsPhoneProfiles.setSize3p0to4p5(1);
        } else if (dSize >= 4.6 && dSize <= 5.0) {
            odsPhoneProfiles.setSize4p6to5p0(1);
        } else if (dSize >= 5.1 && dSize <= 5.5) {
            odsPhoneProfiles.setSize5p1to5p5(1);
        } else if (dSize > 5.5) {
            odsPhoneProfiles.setSizeGreater5p5(1);
        } else {
            odsPhoneProfiles.setSizeOther(1);
        }
    }

    public void setRam(String ram, OdsPhoneProfiles odsPhoneProfiles) {
        String[] rams = ram.split("G");
        if (rams.length == 1) {
            odsPhoneProfiles.setRamOther(1);
            return;
        } else {
            if (rams[0].equals("2")) {
                odsPhoneProfiles.setRam2(1);
            } else if (rams[0].equals("3")) {
                odsPhoneProfiles.setRam3(1);
            } else if (rams[0].equals("4")) {
                odsPhoneProfiles.setRam4(1);
            } else if (rams[0].equals("6")) {
                odsPhoneProfiles.setRam6(1);
            } else {
                odsPhoneProfiles.setRamOther(1);
            }
        }
    }

    public void setPixel(String pixel, OdsPhoneProfiles odsPhoneProfiles) {
        String[] pixels = pixel.split("[万+]");
        double dPixel = 0;

        for (String pp : pixels) {
            try {
                double temp = Double.parseDouble(pp.replaceAll("[^0-9.]", ""));
                if (temp > dPixel) {
                    dPixel = temp;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }


        if (1 < dPixel && dPixel < 500) {
            odsPhoneProfiles.setPixelLess500(1);
        } else if (dPixel >= 500 && dPixel <= 1000) {
            odsPhoneProfiles.setPixel500to1000(1);
        } else if (dPixel >= 1001 && dPixel <= 1600) {
            odsPhoneProfiles.setPixel1001to1600(1);
        } else if (dPixel > 1600) {
            odsPhoneProfiles.setPixelGreater1600(1);
        } else {
            odsPhoneProfiles.setPixelOther(1);
        }
    }

    public void setRom(String rom, OdsPhoneProfiles odsPhoneProfiles) {
        String[] roms = rom.split("G");
        if (roms.length == 1) {
            odsPhoneProfiles.setRomOther(1);
            return;
        } else {
            if (roms[0].equals("8")) {
                odsPhoneProfiles.setRom8(1);
            } else if (roms[0].equals("16")) {
                odsPhoneProfiles.setRom16(1);
            } else if (roms[0].equals("32")) {
                odsPhoneProfiles.setRom32(1);
            } else if (roms[0].equals("64")) {
                odsPhoneProfiles.setRom64(1);
            } else if (roms[0].equals("128")) {
                odsPhoneProfiles.setRom128(1);
            } else {
                odsPhoneProfiles.setRomOther(1);
            }
        }
    }

    public void setCpu(String cpu, OdsPhoneProfiles odsPhoneProfiles) {
        String[] cpus = cpu.split("核");
        if (cpus[0].equals("单")) {
            odsPhoneProfiles.setCpu1(1);
        } else if (cpus[0].equals("双")) {
            odsPhoneProfiles.setCpu2(1);
        } else if (cpus[0].equals("四")) {
            odsPhoneProfiles.setCpu4(1);
        } else if (cpus[0].equals("六")) {
            odsPhoneProfiles.setCpu6(1);
        } else if (cpus[0].equals("八")) {
            odsPhoneProfiles.setCpu8(1);
        } else {
            odsPhoneProfiles.setCpuOther(1);
        }
    }
}
