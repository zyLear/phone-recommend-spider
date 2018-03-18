package com.zylear.phone.grab.mapper;


import com.zylear.phone.grab.domain.TempPhone;

public interface TempPhoneMapper {

    int copy(String source);

    int deleteBySource(String source);
}