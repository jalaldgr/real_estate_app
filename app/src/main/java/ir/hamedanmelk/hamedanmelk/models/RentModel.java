package ir.hamedanmelk.hamedanmelk.models;

public class RentModel {

    private String id;
    private String Title;
    private String land_state_id;
    private String land_situation_id;
    private String View;
    private String LandStateTitle;
    private String LandSituationTitle;
    private String LandSituationColor;
    private String first_name;
    private String last_name;

    public RentModel(String id, String title, String land_state_id, String land_situation_id, String view, String landStateTitle, String landSituationTitle, String landSituationColor, String first_name, String last_name) {
        this.id = id;
        Title = title;
        this.land_state_id = land_state_id;
        this.land_situation_id = land_situation_id;
        View = view;
        LandStateTitle = landStateTitle;
        LandSituationTitle = landSituationTitle;
        LandSituationColor = landSituationColor;
        this.first_name = first_name;
        this.last_name = last_name;
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

    public String getLand_state_id() {
        return land_state_id;
    }

    public void setLand_state_id(String land_state_id) {
        this.land_state_id = land_state_id;
    }

    public String getLand_situation_id() {
        return land_situation_id;
    }

    public void setLand_situation_id(String land_situation_id) {
        this.land_situation_id = land_situation_id;
    }

    public String getView() {
        return View;
    }

    public void setView(String view) {
        View = view;
    }

    public String getLandStateTitle() {
        return LandStateTitle;
    }

    public void setLandStateTitle(String landStateTitle) {
        LandStateTitle = landStateTitle;
    }

    public String getLandSituationTitle() {
        return LandSituationTitle;
    }

    public void setLandSituationTitle(String landSituationTitle) {
        LandSituationTitle = landSituationTitle;
    }

    public String getLandSituationColor() {
        return LandSituationColor;
    }

    public void setLandSituationColor(String landSituationColor) {
        LandSituationColor = landSituationColor;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "RentModel{" +
                "id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", land_state_id='" + land_state_id + '\'' +
                ", land_situation_id='" + land_situation_id + '\'' +
                ", View='" + View + '\'' +
                ", LandStateTitle='" + LandStateTitle + '\'' +
                ", LandSituationTitle='" + LandSituationTitle + '\'' +
                ", LandSituationColor='" + LandSituationColor + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
