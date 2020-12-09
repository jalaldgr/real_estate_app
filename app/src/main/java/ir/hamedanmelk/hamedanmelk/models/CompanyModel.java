package ir.hamedanmelk.hamedanmelk.models;

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
}
