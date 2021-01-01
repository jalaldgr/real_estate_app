package ir.hamedanmelk.hamedanmelk.models;

import ir.hamedanmelk.hamedanmelk.tools.Constants;
import okhttp3.MultipartBody;

public class CompanyModel {
    private String id;
    private String Title;
    private String Manager;
    private String Phone;
    private String Address;
    private String company_type_id;
    private String user_id;
    private String Disabled;
    private String Logo;
    private String created_at;

    public CompanyModel(String id, String title, String manager, String phone, String address, String company_type_id, String user_id, String disabled, String logo, String created_at) {
        this.id = id;
        Title = title;
        Manager = manager;
        Phone = phone;
        Address = address;
        this.company_type_id = company_type_id;
        this.user_id = user_id;
        Disabled = disabled;
        Logo = logo;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCompany_type_id() {
        return company_type_id;
    }

    public void setCompany_type_id(String company_type_id) {
        this.company_type_id = company_type_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
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
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_UID, user_id); i++;
        }
        if (Manager !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_MANAGER, Manager);i++;
        }
        if (Address !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_ADDRESS, Address);i++;
        }
        if (company_type_id !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_SUB_COMPANY_TYPE_ID, company_type_id);i++;
        }
        if (Title !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.ADD_COMPANY_TITLE, Title);i++;
        }
        if (Phone !=null){
            muParts[i] = MultipartBody.Part.createFormData(Constants.COMPANY_ADD_PHONE, Phone);i++;
        }
        if (created_at !=null){
            // muParts[i] = MultipartBody.Part.createFormData("created_at", created_at);i++;
        }
        return muParts;
    }
}
