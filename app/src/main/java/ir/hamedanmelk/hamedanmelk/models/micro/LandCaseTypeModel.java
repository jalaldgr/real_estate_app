package ir.hamedanmelk.hamedanmelk.models.micro;

public class LandCaseTypeModel {
    private String id;
    private String Title;

    public LandCaseTypeModel(String id, String title) {
        this.id = id;
        Title = title;
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
}
