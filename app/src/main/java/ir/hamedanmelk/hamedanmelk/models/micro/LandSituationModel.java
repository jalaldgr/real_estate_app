package ir.hamedanmelk.hamedanmelk.models.micro;

public class LandSituationModel {
    private String id;
    private String Title;
    private String Color;

    public LandSituationModel(String id, String title, String color) {
        this.id = id;
        Title = title;
        Color = color;
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

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
