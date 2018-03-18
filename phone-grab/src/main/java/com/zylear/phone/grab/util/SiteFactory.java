package com.zylear.phone.grab.util;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author 28444
 * @date 2017/11/3.
 */
public class SiteFactory {
    private static List<String> pcOperatingSystems = new ArrayList<>();

    static {
        pcOperatingSystems.add("Windows NT 10.0; Win64; x64");
        pcOperatingSystems.add("compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0");
        pcOperatingSystems.add("compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0");
        pcOperatingSystems.add("Macintosh; Intel Mac OS X 10.6; rv,2.0.1");
        pcOperatingSystems.add("Macintosh; Intel Mac OS X 10_7_0");
        pcOperatingSystems.add("Macintosh; U; Intel Mac OS X 10_6_8; en-us");
    }


    private static String userAgentTemplate = "Mozilla/5.0 (%s) AppleWebKit/%s (KHTML, like Gecko) Chrome/%s Safari/%s 360/%s";


    public static String getUserAgent() {
        String appleWebKit = RandomUtils.nextInt(500, 599) + "." + RandomUtils.nextInt(0, 99);
        String chrome = "61.0." + RandomUtils.nextInt(3000, 4000) + "." + RandomUtils.nextInt(0, 999);
        String safari = RandomUtils.nextInt(500, 599) + "." + RandomUtils.nextInt(0, 99);
        String operatingSystem = getOperatingSystem();
        return String.format(userAgentTemplate, operatingSystem, appleWebKit, chrome, safari, System.currentTimeMillis());
    }

    private static String getOperatingSystem() {
        int randomCount = RandomUtils.nextInt(0, pcOperatingSystems.size());
        return pcOperatingSystems.get(randomCount);
    }
}
