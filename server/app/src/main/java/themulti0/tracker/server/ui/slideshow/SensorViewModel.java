package themulti0.tracker.server.ui.slideshow;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import themulti0.tracker.server.location.ServerClient;
import themulti0.tracker.server.models.SensorUpdate;

public class SensorViewModel extends ViewModel {

    private final MutableLiveData<SensorUpdate> sensor = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public SensorViewModel() {
        ServerClient
            .Create("http://10.0.2.2:8080")
            .thenApply(client -> client
                .getSensor()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.sensor::setValue));
    }

    public LiveData<SensorUpdate> getSensor() {
        return sensor;
    }
}