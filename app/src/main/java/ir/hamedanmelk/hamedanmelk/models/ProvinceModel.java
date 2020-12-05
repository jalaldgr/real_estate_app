package ir.hamedanmelk.hamedanmelk.models;

public class ProvinceModel {
    private String id;
    private String Title;
    private String TelegramChannelName;
    private String Disabled;

    public ProvinceModel(String id, String title, String telegramChannelName, String disabled) {
        this.id = id;
        Title = title;
        TelegramChannelName = telegramChannelName;
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

    public String getTelegramChannelName() {
        return TelegramChannelName;
    }

    public void setTelegramChannelName(String telegramChannelName) {
        TelegramChannelName = telegramChannelName;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }
}
