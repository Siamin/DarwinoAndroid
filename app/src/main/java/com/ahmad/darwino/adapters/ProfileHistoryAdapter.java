package com.ahmad.darwino.adapters;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ahmad.darwino.R;
import com.ahmad.darwino.network.mdoels.HistoryCourses;
import java.util.List;

public class ProfileHistoryAdapter extends RecyclerView.Adapter<ProfileHistoryAdapter.HistoryViewHolder> {


    List<HistoryCourses> _historyCourses;
    Context _context;
    boolean[] Status;


    public ProfileHistoryAdapter(List<HistoryCourses> historyCourses) {
        _historyCourses = historyCourses;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        _context = viewGroup.getContext();
        View itemView = LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.adapter_profile_history, viewGroup, false);

       return new HistoryViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder h, final int i) {

        HistoryCourses hc = _historyCourses.get(i);

        Status[i] = false;

        h.position.setText(String.valueOf((i+1)));
        h.name_cours.setText(hc.name);
        h.teacher.setText("مدرس: "+hc.teacher);
        h.date.setText("تاریخ: "+hc.date);
        h.period.setText("مدت دوره: "+hc.period);
        h.price.setText("پرداخت شده: "+PriceSplitByChar(String.valueOf(hc.price))+" تومان");

        h.name_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status[i]){
                    Status[i] = true;
                    h.dropdown.animate().rotation(-180).setDuration(400).start();
                    h.ll.setVisibility(View.VISIBLE);
                }else{
                    Status[i] = false;
                    h.dropdown.animate().rotation(0).setDuration(400).start();
                    h.ll.setVisibility(View.GONE);
                }
            }
        });

        h.dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Status[i]){
                    Status[i] = true;
                    h.dropdown.animate().rotation(-180).setDuration(400).start();
                    h.ll.setVisibility(View.VISIBLE);
                }else{
                    Status[i] = false;
                    h.dropdown.animate().rotation(0).setDuration(400).start();
                    h.ll.setVisibility(View.GONE);
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    String PriceSplitByChar(String rawText){

        String prezzo = rawText;
        try {

            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('ꓹ');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", symbols);
            prezzo = decimalFormat.format(Integer.parseInt(rawText));
        }catch (Exception e){
            Log.i("TAG_TextView",e.toString());
        }

        return prezzo;
    }

    @Override
    public int getItemCount() {
        Status = new boolean[_historyCourses.size()];
        return _historyCourses.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView position, name_cours, teacher, date, period, price;
        ImageView dropdown;
        LinearLayout ll;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.adapter_profile_history_id);
            name_cours = itemView.findViewById(R.id.adapter_profile_history_name);
            teacher = itemView.findViewById(R.id.adapter_profile_history_teacher);
            date = itemView.findViewById(R.id.adapter_profile_history_date);
            period = itemView.findViewById(R.id.adapter_profile_history_period);
            price = itemView.findViewById(R.id.adapter_profile_history_price);
            dropdown = itemView.findViewById(R.id.adapter_profile_history_dropdown);
            ll = itemView.findViewById(R.id.adapter_profile_history_layout);



        }

    }

}