package com.example.denni.gym;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkoutSessions extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return our layout file
        //cahge R.layout.filename for each and every fragment
        return inflater.inflate(R.layout.woukouts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //title for different fragments can be created at this point
        getActivity().setTitle("Workout Session");
    }
}
