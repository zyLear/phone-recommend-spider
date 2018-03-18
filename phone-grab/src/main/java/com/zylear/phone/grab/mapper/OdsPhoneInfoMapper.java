package com.zylear.phone.grab.mapper;



import com.zylear.phone.grab.domain.OdsPhoneInfo;

import java.util.List;

public interface OdsPhoneInfoMapper {


    int insertBased(OdsPhoneInfo odsPhoneInfo);

    List<OdsPhoneInfo> getPhoneInfoBySource(String source);

    int insertDetail(OdsPhoneInfo odsPhoneInfo);

    List<OdsPhoneInfo> getAdd(String source);

    List<OdsPhoneInfo> getUpdate(String source);

    List<OdsPhoneInfo> getDelete(String source);

    int deleteBySource(String source);

    int deleteNoEffect(String source);
}