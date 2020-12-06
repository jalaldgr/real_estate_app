package ir.hamedanmelk.hamedanmelk.models.micro;

public class AreaModel {
private String id;
private String Title;
private String city_id;

    public AreaModel(String id, String title, String city_id) {
        this.id = id;
        Title = title;
        this.city_id = city_id;
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

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
