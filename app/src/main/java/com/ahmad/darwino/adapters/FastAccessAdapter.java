package com.ahmad.darwino.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmad.darwino.CusrtomViews.MyTextView;
import com.ahmad.darwino.R;
import com.ahmad.darwino.activities.HomeActivity;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.models.FastAccessModel;

import java.util.List;

public class FastAccessAdapter extends RecyclerView.Adapter<FastAccessAdapter.FastAccessViewHolder>{

    private List<FastAccessModel> _models;
    private OnClick _listener;
    private View itemView;
    HomeActivity ha = new HomeActivity();


    public FastAccessAdapter(List<FastAccessModel> models, OnClick listener){
        _models = models;
        _listener = listener;
    }

    @NonNull
    @Override
    public FastAccessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_fast_access, viewGroup, false);
        return new FastAccessViewHolder(itemView, _listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FastAccessViewHolder holder, int i) {

        FastAccessModel model = _models.get(i);

        holder.text.setText(model.text);
        holder.image.setBackgroundResource(model.image);

    }

    @Override
    public int getItemCount() {
        return _models.size();
    }


    public class FastAccessViewHolder extends RecyclerView.ViewHolder{

        private AppCompatImageView image;
        private MyTextView text;

        public FastAccessViewHolder(@NonNull final View itemView, final OnClick listener) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Clicked(getAdapterPosition());
                }
            });

        }
    }

}
