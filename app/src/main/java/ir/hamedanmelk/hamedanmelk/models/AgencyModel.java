package ir.hamedanmelk.hamedanmelk.models;

public class AgencyModel {


    private String  id;
    private String  Title;
    private String  Owner;
    private String  Manager;
    private String  Address;
    private String  province_id;
    private String  city_id;
    private String  area_id;
    private String  district_id;
    private String  user_id;
    private String  Mobile;
    private String  Phone;
    private String  Logo;
    private String  Disabled;
    private String  created_at;
    private String  UserImage;

    public AgencyModel(String id, String title, String owner, String manager, String address, String province_id, String city_id, String area_id, String district_id, String user_id, String mobile, String phone, String logo, String disabled, String created_at, String userImage) {
        this.id = id;
        Title = title;
        Owner = owner;
        Manager = manager;
        Address = address;
        this.province_id = province_id;
        this.city_id = city_id;
        this.area_id = area_id;
        this.district_id = district_id;
        this.user_id = user_id;
        Mobile = mobile;
        Phone = phone;
        Logo = logo;
        Disabled = disabled;
        this.created_at = created_at;
        UserImage = userImage;
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

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }
}
