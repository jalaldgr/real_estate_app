package ir.hamedanmelk.hamedanmelk.models;
import com.google.gson.annotations.SerializedName;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
public class NewLandModel {

    String Latitude;
    String Longitude;
    String Title;
    List<String> ImageFile=new ArrayList<>();
    List<String> UseTypeID;
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
    String Electricy;
    String Phone;
    @SerializedName("Equipment")
    List<String> Equipment;

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

    public List<String> getImageFile() {
        return ImageFile;
    }

    public void setImageFile(List<String> imageFile) {
        ImageFile = imageFile;
    }


    public List<String> getUseTypeID() {
        return UseTypeID;
    }

    public void setUseTypeID(List<String> useTypeID) {
        UseTypeID = useTypeID;
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

    public String getElectricy() {
        return Electricy;
    }

    public void setElectricy(String electricy) {
        Electricy = electricy;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public List<String> getEquipment() {
        return Equipment;
    }

    public void setEquipment(List<String> equipment) {
        Equipment = equipment;
    }

    @Override
    public String toString() {
        return "NewLandModel{" +
                "Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Title='" + Title + '\'' +
                ", ImageFile=" + ImageFile +
                ", UseTypeID=" + UseTypeID +
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
                ", Electricity='" + Electricy + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Equipment=" + Equipment +
                '}';
    }


    public HashMap<String, RequestBody> getHashMap(){

        HashMap<String, RequestBody> LandModelHashMap = new HashMap<>();

        if( Latitude !=null || Latitude !="null")
            LandModelHashMap.put("Latitude", RequestBody.create(MediaType.parse("application/json"),Latitude) );
        if( Longitude !=null || Longitude !="null")
            LandModelHashMap.put("Longitude", RequestBody.create(MediaType.parse("application/json"),Longitude));
        if( Title !=null || Title !="null")
            LandModelHashMap.put("Title", RequestBody.create(MediaType.parse("application/json"),Title));
        if( UseTypeID !=null ){
            for (int i=0; i<UseTypeID.size();i++){
                LandModelHashMap.put("UseTypeID["+i+"]",RequestBody.create(MediaType.parse("application/json"),UseTypeID.get(i)) );
            }
        }
        if( LandStateID !=null )
            LandModelHashMap.put("LandStateID",RequestBody.create(MediaType.parse("application/json"),LandStateID) );
        if( ChkExchanged !=null )
            LandModelHashMap.put("ChkExchanged", RequestBody.create(MediaType.parse("application/json"),ChkExchanged));
        if( UID !=null )
            LandModelHashMap.put("UID", RequestBody.create(MediaType.parse("text/plain"),UID));
        if( Dong !=null )
            LandModelHashMap.put("Dong", RequestBody.create(MediaType.parse("application/json"),Dong));
        if( VoucherTypeID !=null )
            LandModelHashMap.put("VoucherTypeID", RequestBody.create(MediaType.parse("application/json"),VoucherTypeID));
        if( SaleTotalPrice !=null )
            LandModelHashMap.put("SaleTotalPrice", RequestBody.create(MediaType.parse("application/json"),SaleTotalPrice));
        if( PreVoucherTypeID !=null )
            LandModelHashMap.put("PreVoucherTypeID", RequestBody.create(MediaType.parse("application/json"),PreVoucherTypeID));
        if( DeliveryDate !=null )
            LandModelHashMap.put("DeliveryDate", RequestBody.create(MediaType.parse("application/json"),DeliveryDate));
        if( PreSaleTotalPrice !=null )
            LandModelHashMap.put("PreSaleTotalPrice", RequestBody.create(MediaType.parse("application/json"),PreSaleTotalPrice));
        if( ExDong !=null )
            LandModelHashMap.put("ExDong", RequestBody.create(MediaType.parse("application/json"),ExDong));
        if( PreDong !=null )
            LandModelHashMap.put("PreDong", RequestBody.create(MediaType.parse("application/json"),PreDong));
        if( ExVoucherTypeID !=null )
            LandModelHashMap.put("ExVoucherTypeID", RequestBody.create(MediaType.parse("application/json"),ExVoucherTypeID));
        if( Description !=null )
            LandModelHashMap.put("Description", RequestBody.create(MediaType.parse("application/json"),Description));
        if( Address !=null )
            LandModelHashMap.put("Address", RequestBody.create(MediaType.parse("application/json"),Address));
        if( ProvinceID !=null )
            LandModelHashMap.put("ProvinceID", RequestBody.create(MediaType.parse("application/json"),ProvinceID));
        if( CityID !=null )
            LandModelHashMap.put("CityID", RequestBody.create(MediaType.parse("application/json"),CityID));
        if( AreaID !=null )
            LandModelHashMap.put("AreaID", RequestBody.create(MediaType.parse("application/json"),AreaID));
        if( DistrictID !=null )
            LandModelHashMap.put("DistrictID", RequestBody.create(MediaType.parse("application/json"),DistrictID));
        if( LandTypeID !=null )
            LandModelHashMap.put("LandTypeID", RequestBody.create(MediaType.parse("application/json"),LandTypeID));
        if( BuildingConditionID !=null )
            LandModelHashMap.put("BuildingConditionID", RequestBody.create(MediaType.parse("application/json"),BuildingConditionID));
        if( BuildingYear !=null )
            LandModelHashMap.put("BuildingYear", RequestBody.create(MediaType.parse("application/json"),BuildingYear));
        if( DebtTotalPrice !=null )
            LandModelHashMap.put("DebtTotalPrice", RequestBody.create(MediaType.parse("application/json"),DebtTotalPrice));
        if( MortgageTotalPrice !=null )
            LandModelHashMap.put("MortgageTotalPrice", RequestBody.create(MediaType.parse("application/json"),MortgageTotalPrice));
        if( RentTotalPrice !=null )
            LandModelHashMap.put("RentTotalPrice", RequestBody.create(MediaType.parse("application/json"),RentTotalPrice));
        if( PrePayPrice !=null )
            LandModelHashMap.put("PrePayPrice", RequestBody.create(MediaType.parse("application/json"),PrePayPrice));
        if( RentalPreferenceID !=null )
            LandModelHashMap.put("RentalPreferenceID", RequestBody.create(MediaType.parse("application/json"),RentalPreferenceID));
        if( ResidentOwner !=null )
            LandModelHashMap.put("ResidentOwner", RequestBody.create(MediaType.parse("application/json"),ResidentOwner));
        if( FoundationSpace !=null )
            LandModelHashMap.put("FoundationSpace", RequestBody.create(MediaType.parse("application/json"),FoundationSpace));
        if( DirectionID !=null )
            LandModelHashMap.put("DirectionID", RequestBody.create(MediaType.parse("application/json"),DirectionID));
        if( LandViewID !=null )
            LandModelHashMap.put("LandViewID", RequestBody.create(MediaType.parse("application/json"),LandViewID));
        if( FloorCoveringID !=null )
            LandModelHashMap.put("FloorCoveringID", RequestBody.create(MediaType.parse("application/json"),FloorCoveringID));
        if( KitchenServiceID !=null )
            LandModelHashMap.put("KitchenServiceID", RequestBody.create(MediaType.parse("application/json"),KitchenServiceID));
        if( RoomCount !=null )
            LandModelHashMap.put("RoomCount", RequestBody.create(MediaType.parse("application/json"),RoomCount));
        if(  FloorCount !=null) {
            LandModelHashMap.put("FloorCount", RequestBody.create(MediaType.parse("application/json"),FloorCount));
        }
        if( UnitInFloor !=null) {
            LandModelHashMap.put("UnitInFloor", RequestBody.create(MediaType.parse("application/json"),UnitInFloor));
        }
        if(Floor !=null) {
            LandModelHashMap.put("Floor", RequestBody.create(MediaType.parse("application/json"),Floor));
        }
        if( LandCaseID !=null )
            LandModelHashMap.put("LandCaseID", RequestBody.create(MediaType.parse("application/json"),LandCaseID));
        if( LoanTypeID !=null )
            LandModelHashMap.put("LoanTypeID", RequestBody.create(MediaType.parse("application/json"),LoanTypeID));
        if( Water !=null )
            LandModelHashMap.put("Water", RequestBody.create(MediaType.parse("application/json"),Water));
        if( Gas !=null )
            LandModelHashMap.put("Gas", RequestBody.create(MediaType.parse("application/json"),Gas));
        if( Electricy !=null )
            LandModelHashMap.put("Electricy", RequestBody.create(MediaType.parse("application/json"),Electricy));
        if( Phone !=null)
            LandModelHashMap.put("Phone", RequestBody.create(MediaType.parse("application/json"),Phone));
        if( Equipment !=null )
            LandModelHashMap.put("Equipment", RequestBody.create(MediaType.parse("application/json"),String.valueOf(Equipment)));

        return LandModelHashMap;
    }


    public MultipartBody.Part[] getmultipartBodyPart(){

        MultipartBody.Part[] muParts = new MultipartBody.Part[50];
        int i =0;

        if( Latitude !=null || Latitude !="null") {
            muParts[i] = MultipartBody.Part.createFormData("Latitude", Latitude);
            i++;
        }
        if( Longitude !=null || Longitude !="null"){
            muParts[i] = MultipartBody.Part.createFormData("Longitude", Longitude);
            i++;
        }

        if( Title !=null || Title !="null"){
            muParts[i] = MultipartBody.Part.createFormData("Title", Title);
            i++;
        }
        if( UseTypeID !=null ){
            for (int ii=0; ii<UseTypeID.size();ii++){
                muParts[i] = MultipartBody.Part.createFormData("UseTypeID["+ii+"]", UseTypeID.get(ii));
                i++;
            }
        }
        if( LandStateID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("LandStateID", LandStateID);
            i++;
        }
        if( ChkExchanged !=null ){
            muParts[i] = MultipartBody.Part.createFormData("ChkExchanged", ChkExchanged);
            i++;
        }

        if( UID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("UID", UID);
            i++;
        }
        if( Dong !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Dong", Dong);
            i++;
        }
        if( VoucherTypeID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("VoucherTypeID", VoucherTypeID);
            i++;
        }
        if( SaleTotalPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("SaleTotalPrice", SaleTotalPrice);
            i++;
        }
        if( PreVoucherTypeID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("PreVoucherTypeID", PreVoucherTypeID);
            i++;
        }
        if( DeliveryDate !=null ){
            muParts[i] = MultipartBody.Part.createFormData("DeliveryDate", DeliveryDate);
            i++;
        }
        if( PreSaleTotalPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("PreSaleTotalPrice", PreSaleTotalPrice);
            i++;
        }
        if( ExDong !=null ){
            muParts[i] = MultipartBody.Part.createFormData("ExDong", ExDong);
            i++;
        }
        if( PreDong !=null ){
            muParts[i] = MultipartBody.Part.createFormData("PreDong", PreDong);
            i++;
        }
        if( ExVoucherTypeID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("ExVoucherTypeID", ExVoucherTypeID);
            i++;
        }
        if( Description !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Description", Description);
            i++;
        }
        if( Address !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Address", Address);
            i++;
        }
        if( ProvinceID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("ProvinceID", ProvinceID);
            i++;
        }
        if( CityID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("CityID", CityID);
            i++;
        }
        if( AreaID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("AreaID", AreaID);
            i++;
        }
        if( DistrictID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("DistrictID", DistrictID);
            i++;
        }
        if( LandTypeID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("LandTypeID", LandTypeID);
            i++;
        }
        if( BuildingConditionID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("BuildingConditionID", BuildingConditionID);
            i++;
        }
        if( BuildingYear !=null ){
            muParts[i] = MultipartBody.Part.createFormData("BuildingYear", BuildingYear);
            i++;
        }
        if( DebtTotalPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("DebtTotalPrice", DebtTotalPrice);
            i++;
        }
        if( MortgageTotalPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("MortgageTotalPrice", MortgageTotalPrice);
            i++;
        }
        if( RentTotalPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("RentTotalPrice", RentTotalPrice);
            i++;
        }
        if( PrePayPrice !=null ){
            muParts[i] = MultipartBody.Part.createFormData("PrePayPrice", PrePayPrice);
            i++;
        }
        if( RentalPreferenceID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("RentalPreferenceID", RentalPreferenceID);
            i++;
        }
        if( ResidentOwner !=null ){
            muParts[i] = MultipartBody.Part.createFormData("ResidentOwner", ResidentOwner);
            i++;
        }
        if( FoundationSpace !=null ){
            muParts[i] = MultipartBody.Part.createFormData("FoundationSpace", FoundationSpace);
            i++;
        }
        if( DirectionID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("DirectionID", DirectionID);
            i++;
        }
        if( LandViewID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("LandViewID", LandViewID);
            i++;
        }
        if( FloorCoveringID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("FloorCoveringID", FloorCoveringID);
            i++;
        }
        if( KitchenServiceID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("KitchenServiceID", KitchenServiceID);
            i++;
        }
        if( RoomCount !=null ){
            muParts[i] = MultipartBody.Part.createFormData("RoomCount", RoomCount);
            i++;
        }
        if(  FloorCount !=null) {
            muParts[i] = MultipartBody.Part.createFormData("FloorCount", FloorCount);
            i++;
        }
        if( UnitInFloor !=null) {
            muParts[i] = MultipartBody.Part.createFormData("UnitInFloor", UnitInFloor);
            i++;
        }
        if(Floor !=null) {
            muParts[i] = MultipartBody.Part.createFormData("Floor", Floor);
           i++;
        }
        if( LandCaseID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("LandCaseID", LandCaseID);
            i++;
        }
        if( LoanTypeID !=null ){
            muParts[i] = MultipartBody.Part.createFormData("LoanTypeID", LoanTypeID);
            i++;
        }
        if( Water !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Water", Water);
            i++;
        }
        if( Gas !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Gas", Gas);
            i++;
        }
        if( Electricy !=null ){
            muParts[i] = MultipartBody.Part.createFormData("Electricy", Electricy);
            i++;
        }
        if( Phone !=null){
            muParts[i] = MultipartBody.Part.createFormData("Phone", Phone);
            i++;
        }

        if( Equipment !=null ){
            for (int ii=0; ii<Equipment.size();ii++){
                muParts[i] = MultipartBody.Part.createFormData("Equipment["+ii+"]", Equipment.get(ii));
                i++;
            }
        }

        return muParts;
    }


    public MultipartBody.Part[] getImagesHashMap(){
        MultipartBody.Part[] HashImages=null;
        if(ImageFile!=null) {
             HashImages = new MultipartBody.Part[ImageFile.size()];
            for (int i = 0; i < ImageFile.size(); i++) {
                File file = new File(ImageFile.get(i));
                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file);
                HashImages[i] = MultipartBody.Part.createFormData("ImageFile[" + i + "]", file.getName(), surveyBody);
            }
        }
        return HashImages;
    }
}