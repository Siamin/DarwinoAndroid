package com.ahmad.darwino.CusrtomViews;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ahmad.darwino.R;

/**
 * Created by AndroidPC on 1/20/2019.
 */

public class Search extends LinearLayout {

    LayoutInflater mInflater;

    public Search(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();

    }

    public Search(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public Search(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }


    public void init()
    {
        final View v = mInflater.inflate(R.layout.layout_search, this, true);
        final ImageView imag_search = (ImageView) v.findViewById(R.id.search_searchicon);
        final EditText editText = (EditText) v.findViewById(R.id.search_edittext);
        final ImageView imag_cancle = (ImageView) v.findViewById(R.id.search_cancle);

        v.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        imag_search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imag_search.setVisibility( imag_search.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                imag_cancle.setVisibility( imag_cancle.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                editText.setVisibility( editText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            }
        });

        imag_cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                imag_search.setVisibility( imag_search.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                imag_cancle.setVisibility( imag_cancle.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                editText.setVisibility( editText.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                v.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
        });

    }


}

