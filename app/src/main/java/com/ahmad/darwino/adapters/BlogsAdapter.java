package com.ahmad.darwino.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmad.darwino.R;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.network.mdoels.BlogModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.BlogsViewHolder> {


    List<BlogModel> _BlogModels;
    OnClick _listener;
    Context _context;


    public BlogsAdapter(List<BlogModel> blogModels, OnClick listener) {
        _BlogModels = blogModels;
        _listener = listener;
    }



    @NonNull
    @Override
    public BlogsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        _context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_blog, viewGroup, false);
        return new BlogsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogsViewHolder h, int i) {

        BlogModel bm = _BlogModels.get(i);

        h.title.setText(bm.Title);
        h.description.setText(bm.shortDescription.substring(0,135));
        Picasso.with(h.image.getContext()).load(bm.url_iamge).into(h.image);


    }

    @Override
    public int getItemCount() {
        return _BlogModels.size();
    }

    public class BlogsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        TextView title, description;

        public BlogsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.adapter_blogs_image);
            title = itemView.findViewById(R.id.adapter_blogs_title);
            description = itemView.findViewById(R.id.adapter_blogs_description);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            _listener.Clicked(getAdapterPosition(), image, title);
        }
    }

}
