package com.v2.coaching.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v2.coaching.Model.Modality;
import com.v2.coaching.R;
import com.v2.coaching.View.HomeViewHolder;

import java.util.List;

/**
 * Created by CaioSChristino on 03/09/17.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    List<Modality> modalities;
    Context mContext;
    AdapterListener mListener;

    public interface AdapterListener {
        View.OnClickListener setOnClickListener();
    }

    public HomeAdapter(AdapterListener mListener, Context context, List<Modality> modalities) {
        this.modalities = modalities;
        this.mContext = context;
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.home_view_holder, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {
        HomeViewHolder holder = (HomeViewHolder) viewHolder;
        Modality modality  = modalities.get(position) ;
        holder.setModalityTitle(modality.getName());
    }

    @Override
    public int getItemCount() {
        return this.modalities.size();
    }
}