package tn.esprit.tourbuddy.ui.destination;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DestinationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DestinationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is destination fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}