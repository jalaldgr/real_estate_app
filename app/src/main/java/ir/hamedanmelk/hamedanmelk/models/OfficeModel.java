package ir.hamedanmelk.hamedanmelk.models;

import android.util.Log;
import android.widget.AdapterView;

import ir.hamedanmelk.hamedanmelk.tools.Constants;
import okhttp3.MultipartBody;

public class OfficeModel {

    private String  id;
    private String  Title;
    private String  Manager;
    private String  No;
    private String  Address;
    private String  Logo;
    private String  Phone;
    private String  Fax;
    private String  Disabled;
    private String  province_id;
    private String  city_id;
    private String  area_id;
    private String  district_id;
    private String  user_id;
    private String  created_at;

    public OfficeModel(String id, String title, String manager, String no, String address, String logo, String phone, String fax, String disabled, String province_id, String city_id, String area_id, String district_id, String user_id, String created_at) {
        this.id = id;
        Title = title;
        Manager = manager;
        No = no;
        Address = address;
        Logo = logo;
        Phone = phone;
        Fax = fax;
        Disabled = disabled;
        this.province_id = province_id;
        this.city_id = city_id;
        this.area_id = area_id;
        this.district_id = district_id;
        this.user_id = user_id;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public MultipartBody.Part[] getMultipartBody(){
        MultipartBody.Part[] muParts = new MultipartBody.Part[10];
        int i = 0;
        if (user_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_UID, user_id); i++;
        }
        if (Title !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_TITLE, Title);i++;
        }
        if (Manager !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_MANAGER, Manager);i++;
        }
        if (No !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_NO, No);i++;
        }
        if (Address !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_ADDRESS, Address);i++;
        }
        if (Phone !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_PHONE, Phone);i++;
        }
        if (Fax !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_FAX, Fax);i++;
        }
        if (province_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_PROVINCE_ID, province_id);i++;
        }
        if (city_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_CITY, city_id);i++;
        }
        if (area_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_AREA_ID, area_id);i++;
        }
        if (district_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_OFFICE_DISTRICID, district_id);i++;
        }
        return muParts;
    }
}
