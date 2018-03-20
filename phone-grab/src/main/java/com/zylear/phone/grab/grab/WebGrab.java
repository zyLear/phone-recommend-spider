package com.zylear.phone.grab.grab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/8/17.
 */
@Component
public class WebGrab {

    @Resource
    private WebGrabInterface jingDong;
    @Resource
    private WebGrabInterface suNing;
    @Resource
    private WebGrabInterface feiNiuWang;
    @Resource
    private WebGrabInterface yiHaoDian;
    @Autowired
    @Qualifier("jiuJiWang")
    private WebGrabInterface jiuJiWang;


    public void dealSuNing() {
        suNing.copy();
        suNing.getOdsInfo();
        suNing.getOdsProfiles();
        suNing.merge();
        suNing.deleteNoEffect();
    }


    public void dealJingDong() {
        jingDong.copy();
        jingDong.getOdsInfo();
        jingDong.getOdsProfiles();
        jingDong.merge();
        jingDong.deleteNoEffect();
    }

    public void dealFeiNiuWang() {
        feiNiuWang.copy();
        feiNiuWang.getOdsInfo();
        feiNiuWang.getOdsProfiles();
        feiNiuWang.merge();
        feiNiuWang.deleteNoEffect();
    }

    public void dealYiHaoDian() {
        yiHaoDian.copy();
        yiHaoDian.getOdsInfo();
        yiHaoDian.getOdsProfiles();
        yiHaoDian.merge();
        yiHaoDian.deleteNoEffect();
    }

    public void dealJiuJiWang() {
        jiuJiWang.copy();
        jiuJiWang.getOdsInfo();
        jiuJiWang.getOdsProfiles();
        jiuJiWang.merge();
        jiuJiWang.deleteNoEffect();
    }


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        WebGrab webGrab = (WebGrab) ac.getBean("webGrab");

        //webGrab.dealSuNing();
        // webGrab.dealJingDong();
        webGrab.dealFeiNiuWang();
        // webGrab.dealYiHaoDian();
    }
}
