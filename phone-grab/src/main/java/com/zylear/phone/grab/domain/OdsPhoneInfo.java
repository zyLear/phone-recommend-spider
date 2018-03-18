package com.zylear.phone.grab.domain;

public class OdsPhoneInfo {
    public OdsPhoneInfo() {
    }

    private String link;

    private String title;

    private String img;

    private String source;

    private Double price;

    private String brand;

    private String size;

    private String ram;

    private String pixel;

    private String rom;

    private String cpu;

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public OdsPhoneInfo(String title, String link, String img, String source, Double price) {
        this.link = link;
        this.title = title;
        this.img = img;
        this.source = source;
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram == null ? null : ram.trim();
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel == null ? null : pixel.trim();
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom == null ? null : rom.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public void setDetail(String brand, String size, String ram, String pixel, String rom, String cpu,String model) {
        this.brand = brand;
        this.size = size;
        this.ram = ram;
        this.pixel = pixel;
        this.rom = rom;
        this.cpu = cpu;
        this.model=model;
    }
}