package com.okhttp;

public class MyHttpParams extends BaseBean {
    public String getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(String landlordId) {
        this.landlordId = landlordId;
    }

    private String landlordId;
}
