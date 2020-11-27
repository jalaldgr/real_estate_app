package ir.hamedanmelk.hamedanmelk.ui.newland;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewLandViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewLandViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ثبت آگهی جدید");
    }

    public LiveData<String> getText() {
        return mText;
    }
}