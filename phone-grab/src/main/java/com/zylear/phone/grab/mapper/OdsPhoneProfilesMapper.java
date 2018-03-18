package com.zylear.phone.grab.mapper;


import com.zylear.phone.grab.domain.OdsPhoneProfiles;

public interface OdsPhoneProfilesMapper {

    int insert(OdsPhoneProfiles record);

    int deleteBySource(String source);

    OdsPhoneProfiles selectByLink(String link);
}