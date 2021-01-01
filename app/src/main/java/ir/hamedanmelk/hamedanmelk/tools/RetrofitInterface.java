package ir.hamedanmelk.hamedanmelk.tools;

import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.models.myResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RetrofitInterface {
    @Multipart
    @POST( "/AppRequests/RegisterLand")
    Call<myResponse> UploadNewLand(@Part MultipartBody.Part[] images, @PartMap HashMap<String, RequestBody> newLandModel);
//   Call<myResponse> UploadNewLand(@Body NewLandModel newLandModel, @PartMap HashMap<String, File> Images);

    @Multipart
    @POST( "/AppRequests/CompanyAdd")
    Call<myResponse> AddCompanyRequest(@Part MultipartBody.Part[] Parts, @Part MultipartBody.Part images);

}
