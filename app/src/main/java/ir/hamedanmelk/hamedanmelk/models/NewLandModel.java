package ir.hamedanmelk.hamedanmelk.models;

import java.util.Arrays;
import java.util.List;

public class NewLandModel {

    String Latitude;
    String Longitude;
    String Title;
    String[] ImageFiles;
    String[] UseTypeIDLstArr;
    String LandStateID;
    String ChkExchanged;
    String UID;
    String Dong;
    String VoucherTypeID;
    String SaleTotalPrice;
    String PreDong;
    String PreVoucherTypeID;
    String DeliveryDate;//Current time, pass it null
    String PreSaleTotalPrice;
    String ExDong;
    String ExVoucherTypeID;
    String Description;
    String Address;
    String ProvinceID;
    String CityID;
    String AreaID;
    String DistrictID;
    String LandTypeID;
    String BuildingConditionID;
    String BuildingYear;
    String DebtTotalPrice;
    String MortgageTotalPrice;
    String RentTotalPrice;
    String PrePayPrice;
    String RentalPreferenceID;
    String ResidentOwner;//checkbox
    String FoundationSpace;
    String DirectionID;
    String LandViewID;
    String FloorCoveringID;
    String KitchenServiceID;
    String RoomCount;
    String FloorCount;
    String UnitInFloor;
    String Floor;
    String LandCaseID;
    String LoanTypeID;
    String Water;
    String Gas;
    String Electricity;
    String Phone;
    String EquipmentStr[];

    public NewLandModel() {
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String[] getImageFiles() {
        return ImageFiles;
    }

    public void setImageFiles(String[] imageFiles) {
        ImageFiles = imageFiles;
    }

    public String[] getUseTypeIDLstArr() {
        return UseTypeIDLstArr;
    }

    public void setUseTypeIDLstArr(String[] useTypeIDLstArr) {
        UseTypeIDLstArr = useTypeIDLstArr;
    }

    public String getLandStateID() {
        return LandStateID;
    }

    public void setLandStateID(String landStateID) {
        LandStateID = landStateID;
    }

    public String getChkExchanged() {
        return ChkExchanged;
    }

    public void setChkExchanged(String chkExchanged) {
        ChkExchanged = chkExchanged;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String dong) {
        Dong = dong;
    }

    public String getVoucherTypeID() {
        return VoucherTypeID;
    }

    public void setVoucherTypeID(String voucherTypeID) {
        VoucherTypeID = voucherTypeID;
    }

    public String getSaleTotalPrice() {
        return SaleTotalPrice;
    }

    public void setSaleTotalPrice(String saleTotalPrice) {
        SaleTotalPrice = saleTotalPrice;
    }

    public String getPreDong() {
        return PreDong;
    }

    public void setPreDong(String preDong) {
        PreDong = preDong;
    }

    public String getPreVoucherTypeID() {
        return PreVoucherTypeID;
    }

    public void setPreVoucherTypeID(String preVoucherTypeID) {
        PreVoucherTypeID = preVoucherTypeID;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getPreSaleTotalPrice() {
        return PreSaleTotalPrice;
    }

    public void setPreSaleTotalPrice(String preSaleTotalPrice) {
        PreSaleTotalPrice = preSaleTotalPrice;
    }

    public String getExDong() {
        return ExDong;
    }

    public void setExDong(String exDong) {
        ExDong = exDong;
    }

    public String getExVoucherTypeID() {
        return ExVoucherTypeID;
    }

    public void setExVoucherTypeID(String exVoucherTypeID) {
        ExVoucherTypeID = exVoucherTypeID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(String provinceID) {
        ProvinceID = provinceID;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getAreaID() {
        return AreaID;
    }

    public void setAreaID(String areaID) {
        AreaID = areaID;
    }

    public String getDistrictID() {
        return DistrictID;
    }

    public void setDistrictID(String districtID) {
        DistrictID = districtID;
    }

    public String getLandTypeID() {
        return LandTypeID;
    }

    public void setLandTypeID(String landTypeID) {
        LandTypeID = landTypeID;
    }

    public String getBuildingConditionID() {
        return BuildingConditionID;
    }

    public void setBuildingConditionID(String buildingConditionID) {
        BuildingConditionID = buildingConditionID;
    }

    public String getBuildingYear() {
        return BuildingYear;
    }

    public void setBuildingYear(String buildingYear) {
        BuildingYear = buildingYear;
    }

    public String getDebtTotalPrice() {
        return DebtTotalPrice;
    }

    public void setDebtTotalPrice(String debtTotalPrice) {
        DebtTotalPrice = debtTotalPrice;
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

    public String getPrePayPrice() {
        return PrePayPrice;
    }

    public void setPrePayPrice(String prePayPrice) {
        PrePayPrice = prePayPrice;
    }

    public String getRentalPreferenceID() {
        return RentalPreferenceID;
    }

    public void setRentalPreferenceID(String rentalPreferenceID) {
        RentalPreferenceID = rentalPreferenceID;
    }

    public String getResidentOwner() {
        return ResidentOwner;
    }

    public void setResidentOwner(String residentOwner) {
        ResidentOwner = residentOwner;
    }

    public String getFoundationSpace() {
        return FoundationSpace;
    }

    public void setFoundationSpace(String foundationSpace) {
        FoundationSpace = foundationSpace;
    }

    public String getDirectionID() {
        return DirectionID;
    }

    public void setDirectionID(String directionID) {
        DirectionID = directionID;
    }

    public String getLandViewID() {
        return LandViewID;
    }

    public void setLandViewID(String landViewID) {
        LandViewID = landViewID;
    }

    public String getFloorCoveringID() {
        return FloorCoveringID;
    }

    public void setFloorCoveringID(String floorCoveringID) {
        FloorCoveringID = floorCoveringID;
    }

    public String getKitchenServiceID() {
        return KitchenServiceID;
    }

    public void setKitchenServiceID(String kitchenServiceID) {
        KitchenServiceID = kitchenServiceID;
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

    public String getLandCaseID() {
        return LandCaseID;
    }

    public void setLandCaseID(String landCaseID) {
        LandCaseID = landCaseID;
    }

    public String getLoanTypeID() {
        return LoanTypeID;
    }

    public void setLoanTypeID(String loanTypeID) {
        LoanTypeID = loanTypeID;
    }

    public String getWater() {
        return Water;
    }

    public void setWater(String water) {
        Water = water;
    }

    public String getGas() {
        return Gas;
    }

    public void setGas(String gas) {
        Gas = gas;
    }

    public String getElectricity() {
        return Electricity;
    }

    public void setElectricity(String electricity) {
        Electricity = electricity;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String[] getEquipmentStr() {
        return EquipmentStr;
    }

    public void setEquipmentStr(String[] equipmentStr) {
        EquipmentStr = equipmentStr;
    }

    @Override
    public String toString() {
        return "NewLandModel{" +
                "Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Title='" + Title + '\'' +
                ", ImageFiles=" + Arrays.toString(ImageFiles) +
                ", UseTypeIDLstArr=" + Arrays.toString(UseTypeIDLstArr) +
                ", LandStateID='" + LandStateID + '\'' +
                ", ChkExchanged='" + ChkExchanged + '\'' +
                ", UID='" + UID + '\'' +
                ", Dong='" + Dong + '\'' +
                ", VoucherTypeID='" + VoucherTypeID + '\'' +
                ", SaleTotalPrice='" + SaleTotalPrice + '\'' +
                ", PreDong='" + PreDong + '\'' +
                ", PreVoucherTypeID='" + PreVoucherTypeID + '\'' +
                ", DeliveryDate='" + DeliveryDate + '\'' +
                ", PreSaleTotalPrice='" + PreSaleTotalPrice + '\'' +
                ", ExDong='" + ExDong + '\'' +
                ", ExVoucherTypeID='" + ExVoucherTypeID + '\'' +
                ", Description='" + Description + '\'' +
                ", Address='" + Address + '\'' +
                ", ProvinceID='" + ProvinceID + '\'' +
                ", CityID='" + CityID + '\'' +
                ", AreaID='" + AreaID + '\'' +
                ", DistrictID='" + DistrictID + '\'' +
                ", LandTypeID='" + LandTypeID + '\'' +
                ", BuildingConditionID='" + BuildingConditionID + '\'' +
                ", BuildingYear='" + BuildingYear + '\'' +
                ", DebtTotalPrice='" + DebtTotalPrice + '\'' +
                ", MortgageTotalPrice='" + MortgageTotalPrice + '\'' +
                ", RentTotalPrice='" + RentTotalPrice + '\'' +
                ", PrePayPrice='" + PrePayPrice + '\'' +
                ", RentalPreferenceID='" + RentalPreferenceID + '\'' +
                ", ResidentOwner='" + ResidentOwner + '\'' +
                ", FoundationSpace='" + FoundationSpace + '\'' +
                ", DirectionID='" + DirectionID + '\'' +
                ", LandViewID='" + LandViewID + '\'' +
                ", FloorCoveringID='" + FloorCoveringID + '\'' +
                ", KitchenServiceID='" + KitchenServiceID + '\'' +
                ", RoomCount='" + RoomCount + '\'' +
                ", FloorCount='" + FloorCount + '\'' +
                ", UnitInFloor='" + UnitInFloor + '\'' +
                ", Floor='" + Floor + '\'' +
                ", LandCaseID='" + LandCaseID + '\'' +
                ", LoanTypeID='" + LoanTypeID + '\'' +
                ", Water='" + Water + '\'' +
                ", Gas='" + Gas + '\'' +
                ", Electricity='" + Electricity + '\'' +
                ", Phone='" + Phone + '\'' +
                ", EquipmentStr=" + Arrays.toString(EquipmentStr) +
                '}';
    }
}
