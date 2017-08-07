package cot.com.chat_client;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eli on 07/08/17.
 */

@SuppressLint("ValidFragment")
class IotDevicesFragment extends Fragment {

    public IotDevicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_iot_devices, container, false);
    }

}
