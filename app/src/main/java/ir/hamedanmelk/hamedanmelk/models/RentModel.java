package ir.hamedanmelk.hamedanmelk.models;

import ir.hamedanmelk.hamedanmelk.tools.Urls;

public class RentModel {

    private String id;
    private String MortgageTotalPrice;
    private String RentTotalPrice;
    private String Title;
    private String land_state_id;
    private String land_situation_id;
    private String View;
    private String Images;
    private String LandStateTitle;
    private String district_id;
    private String LandSituationTitle;
    private String LandSituationColor;
    private String first_name;
    private String last_name;

    public RentModel(String id, String mortgageTotalPrice, String rentTotalPrice, String title, String land_state_id, String land_situation_id, String view, String images, String landStateTitle, String district_id, String landSituationTitle, String landSituationColor, String first_name, String last_name) {
        this.id = id;
        MortgageTotalPrice = mortgageTotalPrice;
        RentTotalPrice = rentTotalPrice;
        Title = title;
        this.land_state_id = land_state_id;
        this.land_situation_id = land_situation_id;
        View = view;
        Images = images;
        LandStateTitle = landStateTitle;
        this.district_id = district_id;
        LandSituationTitle = landSituationTitle;
        LandSituationColor = landSituationColor;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMortgageTotalPrice() {
        return MortgageTotalPrice;
    }

    public void setMortgageTotalPrice(String mortgageTotalPrice) {
        MortgageTotalPrice = mortgageTotalPrice;
    }

    public String getRentTotalPrice() {
        return RentTotalPrice;
    }

    public void setRentTotalPrice(String rentTotalPrice) {
        RentTotalPrice = rentTotalPrice;
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

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    @Override
    public String toString() {
        return "RentModel{" +
                "id='" + id + '\'' +
                ", MortgageTotalPrice='" + MortgageTotalPrice + '\'' +
                ", RentTotalPrice='" + RentTotalPrice + '\'' +
                ", Title='" + Title + '\'' +
                ", land_state_id='" + land_state_id + '\'' +
                ", land_situation_id='" + land_situation_id + '\'' +
                ", View='" + View + '\'' +
                ", Images='" + Images + '\'' +
                ", LandStateTitle='" + LandStateTitle + '\'' +
                ", district_id='" + district_id + '\'' +
                ", LandSituationTitle='" + LandSituationTitle + '\'' +
                ", LandSituationColor='" + LandSituationColor + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
