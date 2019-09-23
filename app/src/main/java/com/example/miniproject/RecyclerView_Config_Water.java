package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config_Water {

    private Context mContext;
    private WaterAdapter mWaterAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Water>waters,List<String> keys){
        mContext = context;
        mWaterAdapter = new WaterAdapter(waters,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mWaterAdapter);
    }

    class WaterItemView extends RecyclerView.ViewHolder {
        private TextView mDate;
        private TextView mGlasses;

        private String key;

        public WaterItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.water_list_item,parent,false));

            mDate = (TextView) itemView.findViewById(R.id.date_water_textView);
            mGlasses= (TextView) itemView.findViewById(R.id.glasses_water_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(mContext,WaterDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Date",mDate.getText().toString());
                    intent.putExtra("Glasses",mGlasses.getText().toString());

                    mContext.startActivity(intent);

                }
            });
        }

        public void bind (Water water, String key){
            mDate.setText(water.getDate());
            mGlasses.setText(water.getGlasses());
             this.key=key;
        }

    }

    class WaterAdapter extends RecyclerView.Adapter<WaterItemView>{
        private List<Water> mWaterList;
        private List<String> mkeys;

        public WaterAdapter(List<Water> mWaterList, List<String> mkeys) {
            this.mWaterList = mWaterList;
            this.mkeys = mkeys;
        }

        public WaterAdapter() {
            super();
        }

        @NonNull
        @Override
        public WaterItemView onCreateViewHolder(@NonNull ViewGroup parent, int waterItemView) {
            return new WaterItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull WaterItemView holder, int position) {
            holder.bind(mWaterList.get(position),mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mWaterList.size();
        }
    }
}
