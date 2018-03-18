package com.zylear.phone.grab.domain;

public class PhoneProfiles {

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private long sort;

    private String searchTitle;

    private float startPrice;

    private float endPrice;

    private int currentPage;

    private Integer id;

    private int brandApple;

    private int brandMi;

    private int brandHonor;

    private int brandSamsung;

    private int brandMeizu;

    private int brandHuawei;

    private int brandOppo;

    private int brandVivo;

    private int brandNokia;

    private int brandLetv;

    private int brandOther;

    private int priceLess400;

    private int price400to1000;

    private int price1001to1700;

    @Override
    public String toString() {
        return "PhoneProfiles{" +
                "id=" + id +
                ", brandApple=" + brandApple +
                ", brandMi=" + brandMi +
                ", brandHonor=" + brandHonor +
                ", brandSamsung=" + brandSamsung +
                ", brandMeizu=" + brandMeizu +
                ", brandHuawei=" + brandHuawei +
                ", brandOppo=" + brandOppo +
                ", brandVivo=" + brandVivo +
                ", brandNokia=" + brandNokia +
                ", brandLetv=" + brandLetv +
                ", brandOther=" + brandOther +
                ", \n\tpriceLess400=" + priceLess400 +
                ", price400to1000=" + price400to1000 +
                ", price1001to1700=" + price1001to1700 +
                ", price1701to2800=" + price1701to2800 +
                ", price2801to4500=" + price2801to4500 +
                ", priceGreater4500=" + priceGreater4500 +
                ", \n\tsizeLess3p0=" + sizeLess3p0 +
                ", size3p0to4p5=" + size3p0to4p5 +
                ", size4p6to5p0=" + size4p6to5p0 +
                ", size5p1to5p5=" + size5p1to5p5 +
                ", sizeGreater5p5=" + sizeGreater5p5 +
                ", sizeOther=" + sizeOther +
                ", \n\tram2=" + ram2 +
                ", ram3=" + ram3 +
                ", ram4=" + ram4 +
                ", ram6=" + ram6 +
                ", ramOther=" + ramOther +
                ", \n\tpixelLess500=" + pixelLess500 +
                ", pixel500to1000=" + pixel500to1000 +
                ", pixel1001to1600=" + pixel1001to1600 +
                ", pixelGreater1600=" + pixelGreater1600 +
                ", pixelOther=" + pixelOther +
                ", \n\trom8=" + rom8 +
                ", rom16=" + rom16 +
                ", rom32=" + rom32 +
                ", rom64=" + rom64 +
                ", rom128=" + rom128 +
                ", romOther=" + romOther +
                ", \n\tcpu1=" + cpu1 +
                ", cpu2=" + cpu2 +
                ", cpu4=" + cpu4 +
                ", cpu6=" + cpu6 +
                ", cpu8=" + cpu8 +
                ", cpuOther=" + cpuOther +
                '}';
    }

    private int price1701to2800;

    private int price2801to4500;

    private int priceGreater4500;

    private int sizeLess3p0;

    private int size3p0to4p5;

    private int size4p6to5p0;

    private int size5p1to5p5;

    private int sizeGreater5p5;

    private int sizeOther;

    private int ram2;

    private int ram3;

    private int ram4;

    private int ram6;

    private int ramOther;

    private int pixelLess500;

    private int pixel500to1000;

    private int pixel1001to1600;

    private int pixelGreater1600;

    private int pixelOther;

    private int rom8;

    private int rom16;

    private int rom32;

    private int rom64;

    private int rom128;

    private int romOther;

    private int cpu1;

    private int cpu2;

    private int cpu4;

    private int cpu6;

    private int cpu8;

    private int cpuOther;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBrandApple() {
        return brandApple;
    }

    public void setBrandApple(int brandApple) {
        this.brandApple = brandApple;
    }

    public int getBrandMi() {
        return brandMi;
    }

    public void setBrandMi(int brandMi) {
        this.brandMi = brandMi;
    }

    public int getBrandHonor() {
        return brandHonor;
    }

    public void setBrandHonor(int brandHonor) {
        this.brandHonor = brandHonor;
    }

    public int getBrandSamsung() {
        return brandSamsung;
    }

    public void setBrandSamsung(int brandSamsung) {
        this.brandSamsung = brandSamsung;
    }

    public int getBrandMeizu() {
        return brandMeizu;
    }

    public void setBrandMeizu(int brandMeizu) {
        this.brandMeizu = brandMeizu;
    }

    public int getBrandHuawei() {
        return brandHuawei;
    }

    public void setBrandHuawei(int brandHuawei) {
        this.brandHuawei = brandHuawei;
    }

    public int getBrandOppo() {
        return brandOppo;
    }

    public void setBrandOppo(int brandOppo) {
        this.brandOppo = brandOppo;
    }

    public int getBrandVivo() {
        return brandVivo;
    }

    public void setBrandVivo(int brandVivo) {
        this.brandVivo = brandVivo;
    }

    public int getBrandNokia() {
        return brandNokia;
    }

    public void setBrandNokia(int brandNokia) {
        this.brandNokia = brandNokia;
    }

    public int getBrandLetv() {
        return brandLetv;
    }

    public void setBrandLetv(int brandLetv) {
        this.brandLetv = brandLetv;
    }

    public int getBrandOther() {
        return brandOther;
    }

    public void setBrandOther(int brandOther) {
        this.brandOther = brandOther;
    }

    public int getPriceLess400() {
        return priceLess400;
    }

    public void setPriceLess400(int priceLess400) {
        this.priceLess400 = priceLess400;
    }

    public int getPrice400to1000() {
        return price400to1000;
    }

    public void setPrice400to1000(int price400to1000) {
        this.price400to1000 = price400to1000;
    }

    public int getPrice1001to1700() {
        return price1001to1700;
    }

    public void setPrice1001to1700(int price1001to1700) {
        this.price1001to1700 = price1001to1700;
    }

    public int getPrice1701to2800() {
        return price1701to2800;
    }

    public void setPrice1701to2800(int price1701to2800) {
        this.price1701to2800 = price1701to2800;
    }

    public int getPrice2801to4500() {
        return price2801to4500;
    }

    public void setPrice2801to4500(int price2801to4500) {
        this.price2801to4500 = price2801to4500;
    }

    public int getPriceGreater4500() {
        return priceGreater4500;
    }

    public void setPriceGreater4500(int priceGreater4500) {
        this.priceGreater4500 = priceGreater4500;
    }

    public int getSizeLess3p0() {
        return sizeLess3p0;
    }

    public void setSizeLess3p0(int sizeLess3p0) {
        this.sizeLess3p0 = sizeLess3p0;
    }

    public int getSize3p0to4p5() {
        return size3p0to4p5;
    }

    public void setSize3p0to4p5(int size3p0to4p5) {
        this.size3p0to4p5 = size3p0to4p5;
    }

    public int getSize4p6to5p0() {
        return size4p6to5p0;
    }

    public void setSize4p6to5p0(int size4p6to5p0) {
        this.size4p6to5p0 = size4p6to5p0;
    }

    public int getSize5p1to5p5() {
        return size5p1to5p5;
    }

    public void setSize5p1to5p5(int size5p1to5p5) {
        this.size5p1to5p5 = size5p1to5p5;
    }

    public int getSizeGreater5p5() {
        return sizeGreater5p5;
    }

    public void setSizeGreater5p5(int sizeGreater5p5) {
        this.sizeGreater5p5 = sizeGreater5p5;
    }

    public int getSizeOther() {
        return sizeOther;
    }

    public void setSizeOther(int sizeOther) {
        this.sizeOther = sizeOther;
    }

    public int getRam2() {
        return ram2;
    }

    public void setRam2(int ram2) {
        this.ram2 = ram2;
    }

    public int getRam3() {
        return ram3;
    }

    public void setRam3(int ram3) {
        this.ram3 = ram3;
    }

    public int getRam4() {
        return ram4;
    }

    public void setRam4(int ram4) {
        this.ram4 = ram4;
    }

    public int getRam6() {
        return ram6;
    }

    public void setRam6(int ram6) {
        this.ram6 = ram6;
    }

    public int getRamOther() {
        return ramOther;
    }

    public void setRamOther(int ramOther) {
        this.ramOther = ramOther;
    }

    public int getPixelLess500() {
        return pixelLess500;
    }

    public void setPixelLess500(int pixelLess500) {
        this.pixelLess500 = pixelLess500;
    }

    public int getPixel500to1000() {
        return pixel500to1000;
    }

    public void setPixel500to1000(int pixel500to1000) {
        this.pixel500to1000 = pixel500to1000;
    }

    public int getPixel1001to1600() {
        return pixel1001to1600;
    }

    public void setPixel1001to1600(int pixel1001to1600) {
        this.pixel1001to1600 = pixel1001to1600;
    }

    public int getPixelGreater1600() {
        return pixelGreater1600;
    }

    public void setPixelGreater1600(int pixelGreater1600) {
        this.pixelGreater1600 = pixelGreater1600;
    }

    public int getPixelOther() {
        return pixelOther;
    }

    public void setPixelOther(int pixelOther) {
        this.pixelOther = pixelOther;
    }

    public int getRom8() {
        return rom8;
    }

    public void setRom8(int rom8) {
        this.rom8 = rom8;
    }

    public int getRom16() {
        return rom16;
    }

    public void setRom16(int rom16) {
        this.rom16 = rom16;
    }

    public int getRom32() {
        return rom32;
    }

    public void setRom32(int rom32) {
        this.rom32 = rom32;
    }

    public int getRom64() {
        return rom64;
    }

    public void setRom64(int rom64) {
        this.rom64 = rom64;
    }

    public int getRom128() {
        return rom128;
    }

    public void setRom128(int rom128) {
        this.rom128 = rom128;
    }

    public int getRomOther() {
        return romOther;
    }

    public void setRomOther(int romOther) {
        this.romOther = romOther;
    }

    public int getCpu1() {
        return cpu1;
    }

    public void setCpu1(int cpu1) {
        this.cpu1 = cpu1;
    }

    public int getCpu2() {
        return cpu2;
    }

    public void setCpu2(int cpu2) {
        this.cpu2 = cpu2;
    }

    public int getCpu4() {
        return cpu4;
    }

    public void setCpu4(int cpu4) {
        this.cpu4 = cpu4;
    }

    public int getCpu6() {
        return cpu6;
    }

    public void setCpu6(int cpu6) {
        this.cpu6 = cpu6;
    }

    public int getCpu8() {
        return cpu8;
    }

    public void setCpu8(int cpu8) {
        this.cpu8 = cpu8;
    }

    public int getCpuOther() {
        return cpuOther;
    }

    public void setCpuOther(int cpuOther) {
        this.cpuOther = cpuOther;
    }



    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(float endPrice) {
        this.endPrice = endPrice;
    }

    public void changeProfiles(){
        brandApple=1 - brandApple ;
        brandMi=1 - brandMi ;
        brandHonor=1 - brandHonor ;
        brandSamsung=1 - brandSamsung ;
        brandMeizu=1 - brandMeizu ;
        brandHuawei=1 - brandHuawei ;
        brandOppo=1 - brandOppo ;
        brandVivo=1 - brandVivo ;
        brandNokia=1 - brandNokia ;
        brandLetv=1 - brandLetv ;
        brandOther=1 - brandOther ;
        priceLess400=1 - priceLess400 ;
        price400to1000=1 - price400to1000 ;
        price1001to1700=1 - price1001to1700 ;
        price1701to2800=1 - price1701to2800 ;
        price2801to4500=1 - price2801to4500 ;
        priceGreater4500=1 - priceGreater4500 ;
        sizeLess3p0=1 - sizeLess3p0 ;
        size3p0to4p5=1 - size3p0to4p5 ;
        size4p6to5p0=1 - size4p6to5p0 ;
        size5p1to5p5=1 - size5p1to5p5 ;
        sizeGreater5p5=1 - sizeGreater5p5 ;
        sizeOther=1 - sizeOther ;
        ram2=1 - ram2 ;
        ram3=1 - ram3 ;
        ram4=1 - ram4 ;
        ram6=1 - ram6 ;
        ramOther=1 - ramOther ;
        pixelLess500=1 - pixelLess500 ;
        pixel500to1000=1 - pixel500to1000 ;
        pixel1001to1600=1 - pixel1001to1600 ;
        pixelGreater1600=1 - pixelGreater1600 ;
        pixelOther=1 - pixelOther ;
        rom8=1 - rom8 ;
        rom16=1 - rom16 ;
        rom32=1 - rom32 ;
        rom64=1 - rom64 ;
        rom128=1 - rom128 ;
        romOther=1 - romOther ;
        cpu1=1 - cpu1 ;
        cpu2=1 - cpu2 ;
        cpu4=1 - cpu4 ;
        cpu6=1 - cpu6 ;
        cpu8=1 - cpu8 ;
        cpuOther=1 - cpuOther ;
    }

}