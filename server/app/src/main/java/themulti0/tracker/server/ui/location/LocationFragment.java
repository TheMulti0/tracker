package themulti0.tracker.server.ui.location;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import themulti0.tracker.server.databinding.FragmentLocationBinding;
import themulti0.tracker.server.models.LocationUpdate;

public class LocationFragment extends Fragment {

    private LocationViewModel locationViewModel;
    private FragmentLocationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationViewModel =
                new ViewModelProvider(this).get(LocationViewModel.class);

        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView lat = binding.lat;
        final TextView lon = binding.lon;
        binding.button.setOnClickListener(this::onOpenLocationClick);

        locationViewModel.getLocation().observe(getViewLifecycleOwner(), location -> {
            lat.setText("Lat: " + location.getLat());

            lon.setText("Lon: " + location.getLon());
        });

        return root;
    }

    public void onOpenLocationClick(View v) {
        LocationUpdate location = this.locationViewModel.getLocation().getValue();

        Uri geoLocation = Uri.parse(String.format(
            "geo:%f,%f",
            location.getLat(),
            location.getLon()));

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}