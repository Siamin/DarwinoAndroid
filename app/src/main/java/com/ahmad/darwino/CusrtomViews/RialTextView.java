package com.ahmad.darwino.CusrtomViews;


import android.content.Context;
import android.graphics.Typeface;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;

public class RialTextView  extends android.support.v7.widget.AppCompatTextView {

    String rawText;

    public RialTextView(Context context) {
        super(context);

        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/irsans.ttf"));
    }

    public RialTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RialTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void setText(CharSequence text, BufferType type) {
        rawText = text.toString();
        String prezzo = text.toString();
        try {

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('ꓹ');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            prezzo = decimalFormat.format(Integer.parseInt(text.toString()));
        }catch (Exception e){
            Log.i("TAG_TextView",e.toString());
        }

        super.setText(prezzo, type);
    }

    @Override
    public CharSequence getText() {

        return rawText;
    }
}