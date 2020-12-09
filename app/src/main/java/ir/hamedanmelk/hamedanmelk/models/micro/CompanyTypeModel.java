package ir.hamedanmelk.hamedanmelk.models.micro;

public class CompanyTypeModel {
    private String id;
    private String Title;
    private String Order;
    private String parent_id;

    public CompanyTypeModel(String id, String title, String order, String parent_id) {
        this.id = id;
        Title = title;
        Order = order;
        this.parent_id = parent_id;
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

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
}
