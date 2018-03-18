package com.zylear.phone.grab.domain;

public class PhoneInfo {
    private Integer id;

    private String title;

    private String link;

    private String img;

    private String source;

    private Double price;

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public PhoneInfo() {
    }

    private String brand;

//    public PhoneInfo(Integer id, String title, String link, String img, String source, Double price, String brand, String size, String ram, String pixel, String rom, String cpu) {
//        this.id = id;
//        this.title = title;
//        this.link = link;
//        this.img = img;
//        this.source = source;
//        this.price = price;
//        this.brand = brand;
//        this.size = size;
//        this.ram = ram;
//        this.pixel = pixel;
//        this.rom = rom;
//        this.cpu = cpu;
//    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    private String size;
    private String ram;
    private String pixel;
    private String rom;
    private String cpu;

    public PhoneInfo(Integer id, String brand, String size, String ram, String pixel, String rom, String cpu) {
        this.id = id;
        this.brand = brand;
        this.size = size;
        this.ram = ram;
        this.pixel = pixel;
        this.rom = rom;
        this.cpu = cpu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PhoneInfo{" +
                "title" + title+"\n"+
                "id=" + id +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", ram='" + ram + '\'' +
                ", pixel='" + pixel + '\'' +
                ", rom='" + rom + '\'' +
                ", cpu='" + cpu + '\'' +
                '}';
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    public PhoneInfo(Integer id, String title, String link, String img, String source, Double price) {
//        this.id = id;
//        this.title = title;
//        this.link = link;
//        this.img = img;
//        this.source = source;
//        this.price = price;
//    }
}