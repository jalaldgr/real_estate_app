package ir.hamedanmelk.hamedanmelk.models.micro;

public class LinksModel {
    private String id;
    private String Title;
    private String Link;
    private String Logo;
    private String Order;
    private String Disabled;

    public LinksModel(String id, String title, String link, String logo, String order, String disabled) {
        this.id = id;
        Title = title;
        Link = link;
        Logo = logo;
        Order = order;
        Disabled = disabled;
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

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }
}
