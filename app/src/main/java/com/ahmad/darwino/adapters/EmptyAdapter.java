package com.ahmad.darwino.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmad.darwino.R;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.network.mdoels.Course;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.EmptyViewHolder> {


    String Text;

    public EmptyAdapter(String text) {
        Text = text;
    }


    @NonNull
    @Override
    public EmptyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_empty, viewGroup, false);

        return new EmptyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmptyViewHolder Holder, int i) {

        Holder.textView.setText(Text);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.empty_description);

        }

    }

}