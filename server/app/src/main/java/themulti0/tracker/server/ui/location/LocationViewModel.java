package themulti0.tracker.server.ui.location;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import themulti0.tracker.server.location.ServerClient;
import themulti0.tracker.server.models.LocationUpdate;

public class LocationViewModel extends ViewModel {

    private final MutableLiveData<LocationUpdate> location = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LocationViewModel() {
        ServerClient
            .Create("http://10.0.2.2:8080")
            .thenApply(client -> client
                .getLocation()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.location::setValue));
    }

    public LiveData<LocationUpdate> getLocation() {
        return location;
    }
}