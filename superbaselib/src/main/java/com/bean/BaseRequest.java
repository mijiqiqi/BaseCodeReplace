package com.bean;

import java.io.Serializable;

public class BaseRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7088897140366737814L;
    private String uid;
    private String version;
    private String phone;
    private String code;
    private String name;
    private String sex;
    private String city_id;
    private String brand_ids;
    private String company_id;
    private String company_name;
    private String alipay;
    private String weixin;
    private String type;
    private String is_admin;
   private String car_id;
   private String outer_color;
   private String inner_color;
   private String price;
   private String time;
   private String is_split;
   private String is_locate;
   private String is_change;
   private String is_insurance;
   private String insurance_price;
   private String is_attach;
   private String attach_price;
   private String targets;
   private String ended_at;
   private String min_money;
   private String max_money;
   private String prior;
   private String username;
   private String contact;
   private String clue_id;
   private String company_licence;
   private String company_contact;
   private String company_address;
   private String identity_images;
   private String oid;
   private String money;
   private String otid;
   private String status;
   private String delayed_at;
   private String reason;

    public String getDelayed_at() {
        return delayed_at;
    }

    public void setDelayed_at(String delayed_at) {
        this.delayed_at = delayed_at;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtid() {
        return otid;
    }

    public void setOtid(String otid) {
        this.otid = otid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getIdentity_images() {
        return identity_images;
    }

    public void setIdentity_images(String identity_images) {
        this.identity_images = identity_images;
    }

    public String getCompany_licence() {
        return company_licence;
    }

    public void setCompany_licence(String company_licence) {
        this.company_licence = company_licence;
    }

    public String getClue_id() {
        return clue_id;
    }

    public void setClue_id(String clue_id) {
        this.clue_id = clue_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getOuter_color() {
        return outer_color;
    }

    public void setOuter_color(String outer_color) {
        this.outer_color = outer_color;
    }

    public String getInner_color() {
        return inner_color;
    }

    public void setInner_color(String inner_color) {
        this.inner_color = inner_color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIs_split() {
        return is_split;
    }

    public void setIs_split(String is_split) {
        this.is_split = is_split;
    }

    public String getIs_locate() {
        return is_locate;
    }

    public void setIs_locate(String is_locate) {
        this.is_locate = is_locate;
    }

    public String getIs_change() {
        return is_change;
    }

    public void setIs_change(String is_change) {
        this.is_change = is_change;
    }

    public String getIs_insurance() {
        return is_insurance;
    }

    public void setIs_insurance(String is_insurance) {
        this.is_insurance = is_insurance;
    }

    public String getInsurance_price() {
        return insurance_price;
    }

    public void setInsurance_price(String insurance_price) {
        this.insurance_price = insurance_price;
    }

    public String getIs_attach() {
        return is_attach;
    }

    public void setIs_attach(String is_attach) {
        this.is_attach = is_attach;
    }

    public String getAttach_price() {
        return attach_price;
    }

    public void setAttach_price(String attach_price) {
        this.attach_price = attach_price;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getEnded_at() {
        return ended_at;
    }

    public void setEnded_at(String ended_at) {
        this.ended_at = ended_at;
    }

    public String getMin_money() {
        return min_money;
    }

    public void setMin_money(String min_money) {
        this.min_money = min_money;
    }

    public String getMax_money() {
        return max_money;
    }

    public void setMax_money(String max_money) {
        this.max_money = max_money;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getBrand_ids() {
        return brand_ids;
    }

    public void setBrand_ids(String brand_ids) {
        this.brand_ids = brand_ids;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
