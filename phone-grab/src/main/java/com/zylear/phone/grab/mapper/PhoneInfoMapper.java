package com.zylear.phone.grab.mapper;


import com.zylear.phone.grab.domain.OdsPhoneInfo;
import com.zylear.phone.grab.domain.PhoneInfo;

import java.util.List;

public interface PhoneInfoMapper {

    int insert(OdsPhoneInfo odsPhoneInfo);

    int update(OdsPhoneInfo odsPhoneInfo);

    int deleteByLink(String link);

    int deleteNoEffect(String source);
}