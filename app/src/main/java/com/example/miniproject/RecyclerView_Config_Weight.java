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

public class RecyclerView_Config_Weight {
    private Context mContext;
    private WeightsAdapter mWeightsAdapter;
    public void setConfig(RecyclerView recyclerView,Context context,List<Weight> weights, List<String> keys){
        mContext = context;
        mWeightsAdapter = new WeightsAdapter(weights, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mWeightsAdapter);

    }

    class WeightItemView extends RecyclerView.ViewHolder {
        private TextView mWeightAmout;
        private TextView mWeightDate;

        private String key;

        public WeightItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.weight_list_item,parent,false));
            mWeightAmout =(TextView) itemView.findViewById(R.id.weight_amount_textView);
            mWeightDate = (TextView) itemView.findViewById(R.id.weightdate_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,delete_update.class);
                    intent.putExtra("key",key);
                    intent.putExtra("WeightAmount",mWeightAmout.getText().toString());
                    intent.putExtra("WeightDate",mWeightDate.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }

        public void bind (Weight weight,String key){
            mWeightAmout.setText(weight.getWeightAmount());
            mWeightDate.setText(weight.getWeightDate());
            this.key = key;
        }
    }
    class WeightsAdapter extends RecyclerView.Adapter<WeightItemView>{
        private List<Weight> mWeightList;
        private List<String> mKeys;

        public WeightsAdapter(List<Weight> mWeightList, List<String> mKeys) {
            this.mWeightList = mWeightList;
            this.mKeys = mKeys;
        }

        public WeightsAdapter() {
            super();
        }

        @NonNull
        @Override
        public WeightItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new WeightItemView (parent);
        }

        @Override
        public void onBindViewHolder(@NonNull WeightItemView holder, int position) {
        holder.bind(mWeightList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mWeightList.size();
        }
    }
}
