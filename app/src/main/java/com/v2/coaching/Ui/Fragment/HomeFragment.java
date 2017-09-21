package com.v2.coaching.Ui.Fragment;

import android.view.View;

import com.v2.coaching.Adapter.HomeAdapter;
import com.v2.coaching.Model.Modality;
import com.v2.coaching.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class HomeFragment extends BaseFragment implements HomeAdapter.AdapterListener {
    HomeAdapter mAdapter = new HomeAdapter(this, getActivity(), getMock());


    @Override
    void initViews() {

    }

    private List<Modality> getMock() {
        List<Modality> modalities = new ArrayList<>();
        modalities.add(new Modality("jiu jisu"));
        modalities.add(new Modality("jiu jisu"));
        modalities.add(new Modality("jiu jisu"));
        modalities.add(new Modality("jiu jisu"));
        return modalities;
    }

    @Override
    int setContent() {
        return R.layout.home_fragment;
    }

    @Override
    public View.OnClickListener setOnClickListener() {
        return null;
    }
}