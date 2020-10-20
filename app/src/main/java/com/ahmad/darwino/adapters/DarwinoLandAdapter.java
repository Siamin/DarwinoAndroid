package com.ahmad.darwino.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ahmad.darwino.R;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.network.mdoels.DarwinoLandModel;
import java.util.List;

public class DarwinoLandAdapter extends RecyclerView.Adapter<DarwinoLandAdapter.DarwinoLandViewHolder> {


    List<DarwinoLandModel> _darwinoLandModels;
    OnClick _listener;
    Context _context;


    public DarwinoLandAdapter(List<DarwinoLandModel> darwinoLandModels, OnClick listener) {
        _darwinoLandModels = darwinoLandModels;
        _listener = listener;
    }




    @NonNull
    @Override
    public DarwinoLandViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        _context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_darwinoland, viewGroup, false);

        return new DarwinoLandViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DarwinoLandViewHolder holder, final int i) {
        DarwinoLandModel dlm = _darwinoLandModels.get(i);

        holder.title.setText(dlm.name);
        holder.description.setText(dlm.shortDescription);
        holder.image.setImageDrawable(holder.image.getContext().getResources().getDrawable(dlm.image_id));
//        Picasso.with(holder.image.getContext()).load(dlm.url_image).into(holder.image);


        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _listener.Clicked(i, holder.image, holder.title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _darwinoLandModels.size();
    }

    public class DarwinoLandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        public TextView title, description;
        public Button more;


        public DarwinoLandViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.darwinoland_image);
            title = itemView.findViewById(R.id.darwinoland_title);
            description = itemView.findViewById(R.id.darwinoland_description);
            more = itemView.findViewById(R.id.darwinoland_more);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            _listener.Clicked(getAdapterPosition(), image, title);
        }
    }

}
