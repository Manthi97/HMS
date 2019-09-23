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

public class RecyclerView_Config {

    private Context mContext;
    private FoodAdapter mFoodAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Food>foods, List<String> keys ){
        mContext = context;
        mFoodAdapter = new FoodAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFoodAdapter);
    }

    class FoodItemView extends RecyclerView.ViewHolder {

        private TextView mDate;
        private TextView mMealItems;
        private TextView mMealType;

        private String key;

        public FoodItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.food_list_item,parent,false));

            mDate = (TextView) itemView.findViewById(R.id.Date_txtView);
            mMealItems =(TextView) itemView.findViewById(R.id.meal_items_textView);
            mMealType = (TextView) itemView.findViewById(R.id.meal_type_txtView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,FoodUpdate.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Date",mDate.getText().toString());
                    intent.putExtra("Meal_Items",mMealItems.getText().toString());
                    intent.putExtra("Meal_Type",mMealType.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(Food food, String key){
            mDate.setText(food.getDate());
            mMealItems.setText(food.getMeal_Items());
            mMealType.setText(food.getMeal_Type());
            this.key = key;
        }
    }

    class FoodAdapter extends RecyclerView.Adapter<FoodItemView>{
        private List<Food> mFoodList;
        private List<String>mkeys;

        public FoodAdapter(List<Food> mFoodList, List<String> mkeys) {
            this.mFoodList = mFoodList;
            this.mkeys = mkeys;
        }

        @Override
        public FoodItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FoodItemView(parent);
        }

        @Override
        public void onBindViewHolder(FoodItemView holder, int position) {
            holder.bind(mFoodList.get(position), mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mFoodList.size();
        }
    }
}
