package ir.hamedanmelk.hamedanmelk.models;

public class LandInfoModel {
    private String id;
    private String Title;
    private String Address;
    private String Map;
    private String Images;
    private String Videos;
    private String province_id;
    private String city_id;
    private String area_id;
    private String district_id;
    private String land_type_id;
    private String building_condition_id;
    private String BuildingYear;
    private String land_state_id;
    private String SaleTotalPrice;
    private String SaleTotalPriceUnit;
    private String DebtTotalPrice;
    private String DebtTotalPriceUnit;
    private String MortgageTotalPrice;
    private String MortgageTotalPriceUnit;
    private String RentTotalPrice;
    private String RentTotalPriceUnit;
    private String PrePayPrice;
    private String PrePayPriceUnit;
    private String DeliveryDate;
    private String rental_preference_id;
    private String ResidentOwner;
    private String voucher_type_id;
    private String Dong;
    private String density_id;
    private String Space;
    private String space_unit_id;
    private String FoundationSpace;
    private String foundation_space_unit_id;
    private String SpacePrice;
    private String direction_id;
    private String land_view_id;
    private String floor_covering_id;
    private String kitchen_service_id;
    private String RoomCount;
    private String FloorCount;
    private String UnitInFloor;
    private String Floor;
    private String land_case_id;
    private String loan_type_id;
    private String land_situation_id;
    private String user_id;
    private String land_owner_id;
    private String View;
    private String created_at;
    private String updated_at;
    private String LandTypeTitle;
    private String LandStateTitle;
    private String ProvinceTitle;
    private String CityTitle;
    private String AreaTitle;
    private String DistrictTitle;
    private String first_name;
    private String last_name;
    private String UserDescription;

    public LandInfoModel(String id, String title, String address, String map, String images, String videos, String province_id, String city_id, String area_id, String district_id, String land_type_id, String building_condition_id, String buildingYear, String land_state_id, String saleTotalPrice, String saleTotalPriceUnit, String debtTotalPrice, String debtTotalPriceUnit, String mortgageTotalPrice, String mortgageTotalPriceUnit, String rentTotalPrice, String rentTotalPriceUnit, String prePayPrice, String prePayPriceUnit, String deliveryDate, String rental_preference_id, String residentOwner, String voucher_type_id, String dong, String density_id, String space, String space_unit_id, String foundationSpace, String foundation_space_unit_id, String spacePrice, String direction_id, String land_view_id, String floor_covering_id, String kitchen_service_id, String roomCount, String floorCount, String unitInFloor, String floor, String land_case_id, String loan_type_id, String land_situation_id, String user_id, String land_owner_id, String view, String created_at, String updated_at, String landTypeTitle, String landStateTitle, String provinceTitle, String cityTitle, String areaTitle, String districtTitle, String first_name, String last_name, String userDescription) {
        this.id = id;
        Title = title;
        Address = address;
        Map = map;
        Images = images;
        Videos = videos;
        this.province_id = province_id;
        this.city_id = city_id;
        this.area_id = area_id;
        this.district_id = district_id;
        this.land_type_id = land_type_id;
        this.building_condition_id = building_condition_id;
        BuildingYear = buildingYear;
        this.land_state_id = land_state_id;
        SaleTotalPrice = saleTotalPrice;
        SaleTotalPriceUnit = saleTotalPriceUnit;
        DebtTotalPrice = debtTotalPrice;
        DebtTotalPriceUnit = debtTotalPriceUnit;
        MortgageTotalPrice = mortgageTotalPrice;
        MortgageTotalPriceUnit = mortgageTotalPriceUnit;
        RentTotalPrice = rentTotalPrice;
        RentTotalPriceUnit = rentTotalPriceUnit;
        PrePayPrice = prePayPrice;
        PrePayPriceUnit = prePayPriceUnit;
        DeliveryDate = deliveryDate;
        this.rental_preference_id = rental_preference_id;
        ResidentOwner = residentOwner;
        this.voucher_type_id = voucher_type_id;
        Dong = dong;
        this.density_id = density_id;
        Space = space;
        this.space_unit_id = space_unit_id;
        FoundationSpace = foundationSpace;
        this.foundation_space_unit_id = foundation_space_unit_id;
        SpacePrice = spacePrice;
        this.direction_id = direction_id;
        this.land_view_id = land_view_id;
        this.floor_covering_id = floor_covering_id;
        this.kitchen_service_id = kitchen_service_id;
        RoomCount = roomCount;
        FloorCount = floorCount;
        UnitInFloor = unitInFloor;
        Floor = floor;
        this.land_case_id = land_case_id;
        this.loan_type_id = loan_type_id;
        this.land_situation_id = land_situation_id;
        this.user_id = user_id;
        this.land_owner_id = land_owner_id;
        View = view;
        this.created_at = created_at;
        this.updated_at = updated_at;
        LandTypeTitle = landTypeTitle;
        LandStateTitle = landStateTitle;
        ProvinceTitle = provinceTitle;
        CityTitle = cityTitle;
        AreaTitle = areaTitle;
        DistrictTitle = districtTitle;
        this.first_name = first_name;
        this.last_name = last_name;
        UserDescription = userDescription;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMap() {
        return Map;
    }

    public void setMap(String map) {
        Map = map;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getVideos() {
        return Videos;
    }

    public void setVideos(String videos) {
        Videos = videos;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getLand_type_id() {
        return land_type_id;
    }

    public void setLand_type_id(String land_type_id) {
        this.land_type_id = land_type_id;
    }

    public String getBuilding_condition_id() {
        return building_condition_id;
    }

    public void setBuilding_condition_id(String building_condition_id) {
        this.building_condition_id = building_condition_id;
    }

    public String getBuildingYear() {
        return BuildingYear;
    }

    public void setBuildingYear(String buildingYear) {
        BuildingYear = buildingYear;
    }

    public String getLand_state_id() {
        return land_state_id;
    }

    public void setLand_state_id(String land_state_id) {
        this.land_state_id = land_state_id;
    }

    public String getSaleTotalPrice() {
        return SaleTotalPrice;
    }

    public void setSaleTotalPrice(String saleTotalPrice) {
        SaleTotalPrice = saleTotalPrice;
    }

    public String getSaleTotalPriceUnit() {
        return SaleTotalPriceUnit;
    }

    public void setSaleTotalPriceUnit(String saleTotalPriceUnit) {
        SaleTotalPriceUnit = saleTotalPriceUnit;
    }

    public String getDebtTotalPrice() {
        return DebtTotalPrice;
    }

    public void setDebtTotalPrice(String debtTotalPrice) {
        DebtTotalPrice = debtTotalPrice;
    }

    public String getDebtTotalPriceUnit() {
        return DebtTotalPriceUnit;
    }

    public void setDebtTotalPriceUnit(String debtTotalPriceUnit) {
        DebtTotalPriceUnit = debtTotalPriceUnit;
    }

    public String getMortgageTotalPrice() {
        return MortgageTotalPrice;
    }

    public void setMortgageTotalPrice(String mortgageTotalPrice) {
        MortgageTotalPrice = mortgageTotalPrice;
    }

    public String getMortgageTotalPriceUnit() {
        return MortgageTotalPriceUnit;
    }

    public void setMortgageTotalPriceUnit(String mortgageTotalPriceUnit) {
        MortgageTotalPriceUnit = mortgageTotalPriceUnit;
    }

    public String getRentTotalPrice() {
        return RentTotalPrice;
    }

    public void setRentTotalPrice(String rentTotalPrice) {
        RentTotalPrice = rentTotalPrice;
    }

    public String getRentTotalPriceUnit() {
        return RentTotalPriceUnit;
    }

    public void setRentTotalPriceUnit(String rentTotalPriceUnit) {
        RentTotalPriceUnit = rentTotalPriceUnit;
    }

    public String getPrePayPrice() {
        return PrePayPrice;
    }

    public void setPrePayPrice(String prePayPrice) {
        PrePayPrice = prePayPrice;
    }

    public String getPrePayPriceUnit() {
        return PrePayPriceUnit;
    }

    public void setPrePayPriceUnit(String prePayPriceUnit) {
        PrePayPriceUnit = prePayPriceUnit;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getRental_preference_id() {
        return rental_preference_id;
    }

    public void setRental_preference_id(String rental_preference_id) {
        this.rental_preference_id = rental_preference_id;
    }

    public String getResidentOwner() {
        return ResidentOwner;
    }

    public void setResidentOwner(String residentOwner) {
        ResidentOwner = residentOwner;
    }

    public String getVoucher_type_id() {
        return voucher_type_id;
    }

    public void setVoucher_type_id(String voucher_type_id) {
        this.voucher_type_id = voucher_type_id;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String dong) {
        Dong = dong;
    }

    public String getDensity_id() {
        return density_id;
    }

    public void setDensity_id(String density_id) {
        this.density_id = density_id;
    }

    public String getSpace() {
        return Space;
    }

    public void setSpace(String space) {
        Space = space;
    }

    public String getSpace_unit_id() {
        return space_unit_id;
    }

    public void setSpace_unit_id(String space_unit_id) {
        this.space_unit_id = space_unit_id;
    }

    public String getFoundationSpace() {
        return FoundationSpace;
    }

    public void setFoundationSpace(String foundationSpace) {
        FoundationSpace = foundationSpace;
    }

    public String getFoundation_space_unit_id() {
        return foundation_space_unit_id;
    }

    public void setFoundation_space_unit_id(String foundation_space_unit_id) {
        this.foundation_space_unit_id = foundation_space_unit_id;
    }

    public String getSpacePrice() {
        return SpacePrice;
    }

    public void setSpacePrice(String spacePrice) {
        SpacePrice = spacePrice;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    public String getLand_view_id() {
        return land_view_id;
    }

    public void setLand_view_id(String land_view_id) {
        this.land_view_id = land_view_id;
    }

    public String getFloor_covering_id() {
        return floor_covering_id;
    }

    public void setFloor_covering_id(String floor_covering_id) {
        this.floor_covering_id = floor_covering_id;
    }

    public String getKitchen_service_id() {
        return kitchen_service_id;
    }

    public void setKitchen_service_id(String kitchen_service_id) {
        this.kitchen_service_id = kitchen_service_id;
    }

    public String getRoomCount() {
        return RoomCount;
    }

    public void setRoomCount(String roomCount) {
        RoomCount = roomCount;
    }

    public String getFloorCount() {
        return FloorCount;
    }

    public void setFloorCount(String floorCount) {
        FloorCount = floorCount;
    }

    public String getUnitInFloor() {
        return UnitInFloor;
    }

    public void setUnitInFloor(String unitInFloor) {
        UnitInFloor = unitInFloor;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getLand_case_id() {
        return land_case_id;
    }

    public void setLand_case_id(String land_case_id) {
        this.land_case_id = land_case_id;
    }

    public String getLoan_type_id() {
        return loan_type_id;
    }

    public void setLoan_type_id(String loan_type_id) {
        this.loan_type_id = loan_type_id;
    }

    public String getLand_situation_id() {
        return land_situation_id;
    }

    public void setLand_situation_id(String land_situation_id) {
        this.land_situation_id = land_situation_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLand_owner_id() {
        return land_owner_id;
    }

    public void setLand_owner_id(String land_owner_id) {
        this.land_owner_id = land_owner_id;
    }

    public String getView() {
        return View;
    }

    public void setView(String view) {
        View = view;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getLandTypeTitle() {
        return LandTypeTitle;
    }

    public void setLandTypeTitle(String landTypeTitle) {
        LandTypeTitle = landTypeTitle;
    }

    public String getLandStateTitle() {
        return LandStateTitle;
    }

    public void setLandStateTitle(String landStateTitle) {
        LandStateTitle = landStateTitle;
    }

    public String getProvinceTitle() {
        return ProvinceTitle;
    }

    public void setProvinceTitle(String provinceTitle) {
        ProvinceTitle = provinceTitle;
    }

    public String getCityTitle() {
        return CityTitle;
    }

    public void setCityTitle(String cityTitle) {
        CityTitle = cityTitle;
    }

    public String getAreaTitle() {
        return AreaTitle;
    }

    public void setAreaTitle(String areaTitle) {
        AreaTitle = areaTitle;
    }

    public String getDistrictTitle() {
        return DistrictTitle;
    }

    public void setDistrictTitle(String districtTitle) {
        DistrictTitle = districtTitle;
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

    public String getUserDescription() {
        return UserDescription;
    }

    public void setUserDescription(String userDescription) {
        UserDescription = userDescription;
    }

    @Override
    public String toString() {
        return "LandInfoModel{" +
                "id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", Address='" + Address + '\'' +
                ", Map='" + Map + '\'' +
                ", Images='" + Images + '\'' +
                ", Videos='" + Videos + '\'' +
                ", province_id='" + province_id + '\'' +
                ", city_id='" + city_id + '\'' +
                ", area_id='" + area_id + '\'' +
                ", district_id='" + district_id + '\'' +
                ", land_type_id='" + land_type_id + '\'' +
                ", building_condition_id='" + building_condition_id + '\'' +
                ", BuildingYear='" + BuildingYear + '\'' +
                ", land_state_id='" + land_state_id + '\'' +
                ", SaleTotalPrice='" + SaleTotalPrice + '\'' +
                ", SaleTotalPriceUnit='" + SaleTotalPriceUnit + '\'' +
                ", DebtTotalPrice='" + DebtTotalPrice + '\'' +
                ", DebtTotalPriceUnit='" + DebtTotalPriceUnit + '\'' +
                ", MortgageTotalPrice='" + MortgageTotalPrice + '\'' +
                ", MortgageTotalPriceUnit='" + MortgageTotalPriceUnit + '\'' +
                ", RentTotalPrice='" + RentTotalPrice + '\'' +
                ", RentTotalPriceUnit='" + RentTotalPriceUnit + '\'' +
                ", PrePayPrice='" + PrePayPrice + '\'' +
                ", PrePayPriceUnit='" + PrePayPriceUnit + '\'' +
                ", DeliveryDate='" + DeliveryDate + '\'' +
                ", rental_preference_id='" + rental_preference_id + '\'' +
                ", ResidentOwner='" + ResidentOwner + '\'' +
                ", voucher_type_id='" + voucher_type_id + '\'' +
                ", Dong='" + Dong + '\'' +
                ", density_id='" + density_id + '\'' +
                ", Space='" + Space + '\'' +
                ", space_unit_id='" + space_unit_id + '\'' +
                ", FoundationSpace='" + FoundationSpace + '\'' +
                ", foundation_space_unit_id='" + foundation_space_unit_id + '\'' +
                ", SpacePrice='" + SpacePrice + '\'' +
                ", direction_id='" + direction_id + '\'' +
                ", land_view_id='" + land_view_id + '\'' +
                ", floor_covering_id='" + floor_covering_id + '\'' +
                ", kitchen_service_id='" + kitchen_service_id + '\'' +
                ", RoomCount='" + RoomCount + '\'' +
                ", FloorCount='" + FloorCount + '\'' +
                ", UnitInFloor='" + UnitInFloor + '\'' +
                ", Floor='" + Floor + '\'' +
                ", land_case_id='" + land_case_id + '\'' +
                ", loan_type_id='" + loan_type_id + '\'' +
                ", land_situation_id='" + land_situation_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", land_owner_id='" + land_owner_id + '\'' +
                ", View='" + View + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", LandTypeTitle='" + LandTypeTitle + '\'' +
                ", LandStateTitle='" + LandStateTitle + '\'' +
                ", ProvinceTitle='" + ProvinceTitle + '\'' +
                ", CityTitle='" + CityTitle + '\'' +
                ", AreaTitle='" + AreaTitle + '\'' +
                ", DistrictTitle='" + DistrictTitle + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", UserDescription='" + UserDescription + '\'' +
                '}';
    }
}
