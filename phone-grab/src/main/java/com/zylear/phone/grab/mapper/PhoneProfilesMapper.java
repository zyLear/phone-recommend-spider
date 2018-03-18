package com.zylear.phone.grab.mapper;


import com.zylear.phone.grab.domain.OdsPhoneProfiles;
import com.zylear.phone.grab.domain.PhoneProfiles;

public interface PhoneProfilesMapper {

    int insert(String link);

    int update(OdsPhoneProfiles odsPhoneProfiles);

}