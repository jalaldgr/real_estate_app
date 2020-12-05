package ir.hamedanmelk.hamedanmelk.models.micro;

public class DistrictModel {
    private String id;
    private String Title;
    private String area_id;

    public DistrictModel(String id, String title, String area_id) {
        this.id = id;
        Title = title;
        this.area_id = area_id;
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

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
