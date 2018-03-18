package com.zylear.phone.grab.pinterface;

import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.grab.WebGrab;
import com.zylear.phone.grab.mapper.OdsPhoneInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 28444 on 2017/9/22.
 */
@RestController
@RequestMapping("/grab")
public class GrabInterface {

    @Autowired
    private WebGrab webGrab;

    @Autowired
    public OdsPhoneInfoMapper odsPhoneInfoMapper;



    @RequestMapping("/ok")
    public String test() {
       // webGrab.dealYiHaoDian();
        return "OK";
    }

    @RequestMapping("/one")
    public String one() {
        // webGrab.dealYiHaoDian();
        return "OK";
    }






    @RequestMapping("/feiniu")
    public String two(HttpServletRequest request) {
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        webGrab.dealFeiNiuWang();
                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }

    @RequestMapping("/yhd")
    public String three(HttpServletRequest request){
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        webGrab.dealYiHaoDian();
                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }

    @RequestMapping("/suning")
    public String suning(HttpServletRequest request){
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        webGrab.dealSuNing();
                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }

    @RequestMapping("/jiujiwang")
    public String jiuJiWang(HttpServletRequest request){
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        webGrab.dealJiuJiWang();
                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }

    @RequestMapping("/jingdong")
    public String jingDong(HttpServletRequest request){
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        webGrab.dealJingDong();
                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }

    @RequestMapping("/databasetest")
    public String tett(HttpServletRequest request) {
        try {
            if (request.getParameter("pd").equals("xzyxzy")) {
                new Thread() {
                    @Override
                    public void run() {
                        List<OdsPhoneInfo> list=odsPhoneInfoMapper.getPhoneInfoBySource("http://www.9ji.com/");
                        for (OdsPhoneInfo odsPhoneInfo : list) {
                            System.out.println(odsPhoneInfo.toString());
                        }


                    }
                }.start();
                return "ok";
            }
        } catch (Exception e) {
            System.out.println("错误");
        }

        return "passord error";
    }



}
