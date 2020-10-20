package com.ahmad.darwino.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ahmad.darwino.R;
import com.ahmad.darwino.activities.HomeActivity;
import com.ahmad.darwino.interfaces.OnClick;
import com.ahmad.darwino.network.mdoels.Category;
import java.util.List;

public class DialogFilterAdapter extends RecyclerView.Adapter<DialogFilterAdapter.DialogFilterViewHolder> {


    List<Category> _categoryList;
    OnClick _listener;
    Context _context;
    HomeActivity ha = new HomeActivity();


    public DialogFilterAdapter(List<Category> categoryList){
        _categoryList = categoryList;
        //_listener = listener;
    }

    @NonNull
    @Override
    public DialogFilterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        _context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_course_categories, viewGroup, false);

        return new DialogFilterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DialogFilterViewHolder h, final int i) {

        final Category category = _categoryList.get(i);

        h.name_category.setText(category.name);

        if (category.isSelected)
            h.checkBox.setChecked(true);
        else
            h.checkBox.setChecked(false);

        h.name_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_categoryList.get(i).isSelected) {
                    _categoryList.get(i).isSelected = false;
                    h.checkBox.setChecked(false);
                }else {
                    _categoryList.get(i).isSelected = true;
                    h.checkBox.setChecked(true);
                }
            }
        });


        h.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_categoryList.get(i).isSelected)
                    _categoryList.get(i).isSelected = false;
                else
                    _categoryList.get(i).isSelected = true;


            }
        });

    }



    @Override
    public int getItemCount() {
        return _categoryList.size();
    }

    public class DialogFilterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_category;
        CheckBox checkBox;

        public DialogFilterViewHolder(@NonNull View itemView) {
            super(itemView);

            name_category = itemView.findViewById(R.id.category_name);
            checkBox = itemView.findViewById(R.id.category_cheked);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            //_listener.Clicked(getAdapterPosition(), image, name);
        }
    }

}