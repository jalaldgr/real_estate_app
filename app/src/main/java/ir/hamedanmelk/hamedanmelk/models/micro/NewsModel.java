package ir.hamedanmelk.hamedanmelk.models.micro;

public class NewsModel {
    private String id;
    private String Title;
    private String Image;

    public NewsModel(String id, String title, String image) {
        this.id = id;
        Title = title;
        Image = image;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}

