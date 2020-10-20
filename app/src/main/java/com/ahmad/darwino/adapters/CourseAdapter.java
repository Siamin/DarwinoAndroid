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

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    List<Course> _courses;
    OnClick _listener;
    Context _context;

    public CourseAdapter(List<Course> courses, OnClick listener) {
        _courses = courses;
        _listener = listener;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        _context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_course, viewGroup, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int i) {

        Course course = _courses.get(i);

        holder.name.setText(course.name);
        holder.description.setText(course.shortDescription);
        holder.price.setText(String.valueOf(course.price));
        Picasso.with(holder.image.getContext()).load("http://darwino.ir/Image/ById/76").into(holder.image);

    }

    @Override
    public int getItemCount() {
        return _courses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public AppCompatImageView image;
        public TextView name, price, description;


        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.payment_commodity_img);
            name = itemView.findViewById(R.id.payment_commodity_title);
            price = itemView.findViewById(R.id.payment_commodity_price);
            description = itemView.findViewById(R.id.payment_commodity_short_description);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            _listener.Clicked(getAdapterPosition(), image, name);
        }
    }

}
