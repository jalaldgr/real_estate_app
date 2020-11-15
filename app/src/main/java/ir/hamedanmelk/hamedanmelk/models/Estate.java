package ir.hamedanmelk.hamedanmelk.models;

public class Estate {
    private String EstateTitle;
    private String EstateType;
    private String EstateAddress;
    //private String EstateArea;
    //private String EstateFoundationArea;
    private String EstatePrice;
    private int EstatePictures;

    public Estate(String estateTitle, String estateAddress, String estatePrice) {
        EstateTitle = estateTitle;
        EstateAddress = estateAddress;
        EstatePrice = estatePrice;
    }

    public Estate(String estateTitle, String estateType, String estateAddress, String estatePrice, int estatePictures) {
        EstateTitle = estateTitle;
        EstateType = estateType;
        EstateAddress = estateAddress;
        EstatePrice = estatePrice;
        EstatePictures = estatePictures;
    }

    public String getEstateTitle() {
        return EstateTitle;
    }

    public void setEstateTitle(String estateTitle) {
        EstateTitle = estateTitle;
    }

    public String getEstateType() {
        return EstateType;
    }

    public void setEstateType(String estateType) {
        EstateType = estateType;
    }

    public String getEstateAddress() {
        return EstateAddress;
    }

    public void setEstateAddress(String estateAddress) {
        EstateAddress = estateAddress;
    }

    public String getEstatePrice() {
        return EstatePrice;
    }

    public void setEstatePrice(String estatePrice) {
        EstatePrice = estatePrice;
    }

    public int getEstatePictures() {
        return EstatePictures;
    }

    public void setEstatePictures(int estatePictures) {
        EstatePictures = estatePictures;
    }
}
