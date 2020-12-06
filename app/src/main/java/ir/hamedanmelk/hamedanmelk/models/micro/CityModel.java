package ir.hamedanmelk.hamedanmelk.models.micro;

public class CityModel {
    private String id;
    private String Title;
    private String province_id;

    public CityModel(String id, String title, String province_id) {
        this.id = id;
        Title = title;
        this.province_id = province_id;
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

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
}
