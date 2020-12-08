package ir.hamedanmelk.hamedanmelk.models.micro;

public class EquipmentModel {
    private String id;
    private String Title;
    private String Logo;

    public EquipmentModel(String id, String title, String logo) {
        this.id = id;
        Title = title;
        Logo = logo;
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

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }
}
