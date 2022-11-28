package themulti0.tracker.server.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import themulti0.tracker.server.databinding.FragmentSensorBinding;
import themulti0.tracker.server.models.SensorUpdate;

public class SensorFragment extends Fragment {

    private SensorViewModel sensorViewModel;
    private FragmentSensorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sensorViewModel =
                new ViewModelProvider(this).get(SensorViewModel.class);

        binding = FragmentSensorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSensor;

        sensorViewModel.getSensor().observe(getViewLifecycleOwner(), sensorUpdate -> {
            onSensorUpdate(textView, sensorUpdate);
        });

        return root;
    }

    private void onSensorUpdate(
            TextView textView,
            SensorUpdate sensorUpdate) {
        
        StringBuilder builder = new StringBuilder();

        builder.append("Sensor: ");
        builder.append(sensorUpdate.getSensor());

        for (float value : sensorUpdate.getValues()) {
            builder.append("\n");
            builder.append(value);
        }
        textView.setText(builder.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}