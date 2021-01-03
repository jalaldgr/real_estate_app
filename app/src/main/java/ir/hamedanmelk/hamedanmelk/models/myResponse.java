package ir.hamedanmelk.hamedanmelk.models;

import java.util.HashMap;

import ir.hamedanmelk.hamedanmelk.tools.RetrofitInterface;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class myResponse implements RetrofitInterface{
    String State;
    String Data;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    @Override
    public Call<myResponse> UploadNewLand(MultipartBody.Part[] Parts,MultipartBody.Part images[] ) {
//        public Call<myResponse> UploadNewLand(@Body NewLandModel newLandModel, @PartMap HashMap<String, File> Images) {

            return null;
    }

    @Override
    public Call<myResponse> AddCompanyRequest(MultipartBody.Part[] Parts, MultipartBody.Part images) {
        return null;
    }

    @Override
    public Call<myResponse> AddOfficeRequest(MultipartBody.Part[] Parts, MultipartBody.Part images) {
        return null;
    }

    @Override
    public Call<myResponse> AddLawyerRequest(MultipartBody.Part[] Parts, MultipartBody.Part images) {
        return null;
    }


}
