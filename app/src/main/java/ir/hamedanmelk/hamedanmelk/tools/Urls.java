package ir.hamedanmelk.hamedanmelk.tools;
////// Constanst Json URLs Class
public class Urls {
    private static final String BaseURL                                 = "https://hamedanmelk.ir";
    private static final String LoginURL                                = "/AppRequests/Authentication";
    private static final String UserLandLists                           = "/AppRequests/UserLandLists";//لیست اگهی های کاربر
    private static final String TotalLands                              = "/AppRequests/TotalLands";//همه اگهی ها
    private static final String TotalRentLands                          = "/AppRequests/TotalRentLands";//همه اجاره ها
    private static final String TotalSaleLands                          = "/AppRequests/TotalSaleLands";//همه فروش ها
    private static final String TotalPreSaleLands                       = "/AppRequests/TotalPreSaleLands";//پیش فروش
    private static final String TotalExchangeLands                      = "/AppRequests/TotalExchangeLands";//معاوضه
    private static final String TotalParticipationLands                 = "/AppRequests/TotalParticipationLands";//مشارکت در ساخت
    private static final String GetNewsLists                            = "/AppRequests/GetNewsLists";//خبر ها
    private static final String RegisterPublicUser                      = "/AppRequests/RegisterPublicUser";//ثبت نام کاربر
    private static final String GetLandInfo                             = "/AppRequests/GetLandInfo";
    public static String getBaseURL() {
        return BaseURL;
    }

    public static String getLoginURL() {
        return LoginURL;
    }

    public static String getUserLandLists() {
        return UserLandLists;
    }

    public static String getTotalLands() {
        return TotalLands;
    }

    public static String getTotalRentLands() {
        return TotalRentLands;
    }

    public static String getTotalSaleLands() {
        return TotalSaleLands;
    }

    public static String getTotalPreSaleLands() {
        return TotalPreSaleLands;
    }

    public static String getTotalExchangeLands() {
        return TotalExchangeLands;
    }

    public static String getTotalParticipationLands() {
        return TotalParticipationLands;
    }

    public static String getGetNewsLists() {
        return GetNewsLists;
    }

    public static String getRegisterPublicUser() {
        return RegisterPublicUser;
    }

    public static String getGetLandInfo() { return GetLandInfo; }
}