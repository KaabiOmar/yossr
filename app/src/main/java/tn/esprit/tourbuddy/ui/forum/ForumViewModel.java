package tn.esprit.tourbuddy.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ForumViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("test");
    }

    public LiveData<String> getText() {
        return mText;
    }
}