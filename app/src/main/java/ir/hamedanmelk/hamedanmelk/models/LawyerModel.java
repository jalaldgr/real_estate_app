package ir.hamedanmelk.hamedanmelk.models;

import ir.hamedanmelk.hamedanmelk.tools.Constants;
import okhttp3.MultipartBody;

public class LawyerModel {
    private String  id;
    private String  FullName;
    private String  Image;
    private String  Description;
    private String  Disabled;
    private String  Phone;
    private String  user_id;
    private String created_at;

    public LawyerModel(String id, String fullName, String image, String description, String disabled, String phone, String user_id, String created_at) {
        this.id = id;
        FullName = fullName;
        Image = image;
        Description = description;
        Disabled = disabled;
        Phone = phone;
        this.user_id = user_id;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDisabled() {
        return Disabled;
    }

    public void setDisabled(String disabled) {
        Disabled = disabled;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    public MultipartBody.Part[] getMultipartBody(){
        MultipartBody.Part[] muParts = new MultipartBody.Part[10];
        int i = 0;

        if (FullName !=null){
            muParts[i] = MultipartBody.Part.createFormData("FullName", FullName);i++;
        }
        if (Description !=null){
            muParts[i] = MultipartBody.Part.createFormData("Description", Description);i++;
        }
        if (Phone !=null){
            muParts[i] = MultipartBody.Part.createFormData("Phone", Phone);i++;
        }
        if (user_id !=null){
            muParts[i] = MultipartBody.Part.createFormData("UID", user_id);i++;
        }

        return muParts;
    }

}
