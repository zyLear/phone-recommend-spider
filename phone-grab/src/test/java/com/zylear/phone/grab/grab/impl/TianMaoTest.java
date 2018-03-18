package com.zylear.phone.grab.grab.impl;

import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.test.context.TestExecutionListeners;

import java.util.concurrent.TimeUnit;

/**
 * Created by 28444 on 2017/10/10.
 */
public class TianMaoTest {

    protected WebDriver getWebDriver(FirefoxProfile firefoxProfile){

        firefoxProfile.setPreference("browser.cache.disk.parent_directory","D:\\FireFox\\缓存");
        firefoxProfile.setPreference("browser.cache.offline.parent_directory","D:\\FireFox\\缓存");
        firefoxProfile.setPreference("browser.cache.disk.enable",true);
        firefoxProfile.setPreference("browser.cache.offline.enable",true);
        WebDriver ffDriver = new FirefoxDriver(firefoxProfile);
        ffDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);  //找不到元素就等待 2 秒!!!!!!!!
        ffDriver.manage().window().maximize();
        return ffDriver;
    }

    @Test
    public void test(){
        System.setProperty("webdriver.firefox.marionette", "D://FireFox//驱动//geckodriver.exe");
        System.setProperty("webdriver.firefox.bin", "D://FireFox//firefox.exe");
        WebDriver webDriver = getWebDriver(new FirefoxProfile());
        addCookies(webDriver);
//        webDriver.get("https://list.tmall.com/search_product.htm?q=%CA%D6%BB%FA&type=p&vmarket=&spm=875.7931836%2FB.a2227oh.d100&xl=shouji_1&from=mallfp..pc_1_suggest");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addCookies(WebDriver webDriver) {
        webDriver.manage().addCookie(new Cookie("tk_trace","1"));
        webDriver.manage().addCookie(new Cookie("cna","Q8BjEgGpORkCAXxMEt3SXurU"));
        webDriver.manage().addCookie(new Cookie("isg","AltbbqfWhj6LOvpK74_rvW336b8FmH9WVd_u5U2YN9pxLHsO1QD_gnmusrZU"));
        webDriver.manage().addCookie(new Cookie("t","690d98cba03125777af3323a0eea87bc"));
        webDriver.manage().addCookie(new Cookie("_tb_token_","eb3ee0833560b"));
        webDriver.manage().addCookie(new Cookie("cookie2","1c1890e7bd4f37197eaa21b76bebfa2c"));
        webDriver.manage().addCookie(new Cookie("tt","tmall-main"));
        webDriver.manage().addCookie(new Cookie("_med","dw:1920&dh:1080&pw:1920&ph:1080&ist:0"));
        webDriver.manage().addCookie(new Cookie("res","scroll%3A1903*6236-client%3A1903*971-offset%3A1903*6236-screen%3A1920*1080"));
        webDriver.manage().addCookie(new Cookie("pnm_cku822","098%23E1hvHpvUvbpvUvCkvvvvvjiPP2SO6j3CRscUljrCPmPyzjEHPLLp0jiEPsFwAjt8RphvCvvvvvvPvpvhvv2MMgyCvvOWvvVvahRivpvUvvmvd6fLlJRtvpvIvvvvvhCvvvvvvUnvphvWbQvv96CvpC29vvm2phCvhhvvvUnUphvp9vyCvhQUWHgvC0Ersj7Q%2BulsbSLXS4ZAhjCwD7zWaXTAVArl%2BWe9eEr%2Bm7zWa4p7%2B3%2BiaNoxfXkKfvc61WFyeED%2BVd0DyOvO5onmsX7vAEyavphvC9vhvvCvp8wCvvpvvUmm"));
        webDriver.manage().addCookie(new Cookie("cq","ccp%3D1"));
    }
}
