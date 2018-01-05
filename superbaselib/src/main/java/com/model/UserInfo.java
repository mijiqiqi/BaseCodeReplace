package com.model;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class UserInfo extends BaseInfo{

        public static  final int SUCCESSFULL=3;
        /**
         *
         */
        private static final long serialVersionUID = 3723925174724417915L;
        private String uid;
        private String nick;
        private String name;
        private String phone;
        private String company_id;
        private int status;//用户状态，0未审核，1等待管理员审核，2等待后台审核，3 成功，-1驳回，-99封禁
        private String invite_uid;
        private String created_at;
        private String updated_at;
        private String is_admin;//是否管理员 0否 1是
        private int sex;
        private String city_id;
        private String alipay;
        private String weixin;
        private String identity_images;
        private int type;//用户类型 04s店员工 1综合店员工 -1 不知道 类型

        private int is_register;
        private String order_out_count;
        private String order_out_success_count;
        private String order_out_fail_count;
        private String order_in_count;
        private String order_in_success_count;
        private String order_in_fail_count;
    public int getIs_register() {
        return is_register;
    }

    public void setIs_register(int is_register) {
        this.is_register = is_register;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }



    public String getInvite_uid() {
        return invite_uid;
    }

    public void setInvite_uid(String invite_uid) {
        this.invite_uid = invite_uid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
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

    public String getIdentity_images() {
        return identity_images;
    }

    public void setIdentity_images(String identity_images) {
        this.identity_images = identity_images;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getStatusName(){
        if(status==0){
            return "未审核";
        }else if(status==1 || status==2){
            return "待审核";
        }else if(status==3){
            return "已审核";
        }else if(status==-1){
            return "驳回";
        }else if(status==-99){
            return "封禁";
        }
        return "未审核";
    }

    public String getOrder_out_count() {
        return order_out_count;
    }

    public void setOrder_out_count(String order_out_count) {
        this.order_out_count = order_out_count;
    }

    public String getOrder_out_success_count() {
        return order_out_success_count;
    }

    public void setOrder_out_success_count(String order_out_success_count) {
        this.order_out_success_count = order_out_success_count;
    }

    public String getOrder_out_fail_count() {
        return order_out_fail_count;
    }

    public void setOrder_out_fail_count(String order_out_fail_count) {
        this.order_out_fail_count = order_out_fail_count;
    }

    public String getOrder_in_count() {
        return order_in_count;
    }

    public void setOrder_in_count(String order_in_count) {
        this.order_in_count = order_in_count;
    }

    public String getOrder_in_success_count() {
        return order_in_success_count;
    }

    public void setOrder_in_success_count(String order_in_success_count) {
        this.order_in_success_count = order_in_success_count;
    }

    public String getOrder_in_fail_count() {
        return order_in_fail_count;
    }

    public void setOrder_in_fail_count(String order_in_fail_count) {
        this.order_in_fail_count = order_in_fail_count;
    }
}
