

package ir.hamedanmelk.hamedanmelk.models;

import android.net.Uri;

public class ImageModel {

    Uri imageUri;

    public ImageModel(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}

