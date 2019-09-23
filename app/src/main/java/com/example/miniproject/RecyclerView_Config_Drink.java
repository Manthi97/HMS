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

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerView_Config_Drink {


    private Context mcontext;
    private DrinksAdapter mDrinksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Drink> drinks, List<String> keys){
        mcontext = context;
        mDrinksAdapter = new DrinksAdapter(drinks,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mDrinksAdapter);
    }

    class DrinkItemView extends RecyclerView.ViewHolder {
        private TextView mDbrand;
        private TextView mDdate;
        private TextView mDname;
        private TextView mDtime;

        private String key;

        public DrinkItemView(ViewGroup parent){
            super(LayoutInflater.from(mcontext).inflate(R.layout.drink_list_item,parent,false));

            mDbrand = (TextView) itemView.findViewById(R.id.Dbrand_txtView);
            mDdate = (TextView)itemView.findViewById(R.id.Ddate_textView);
            mDname = (TextView)itemView.findViewById(R.id.Dname_textView);
            mDtime = (TextView)itemView.findViewById(R.id.Dtime_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext,DrinkDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("Dbrand",mDbrand.getText().toString());
                    intent.putExtra("Ddate",mDdate.getText().toString());
                    intent.putExtra("Dname",mDname.getText().toString());
                    intent.putExtra("Dtime",mDtime.getText().toString());

                    mcontext.startActivity(intent);
                }
            });
        }

        public void bind(Drink drink, String key){
            mDbrand.setText(drink.getDbrand());
            mDdate.setText(drink.getDdate());
            mDname.setText(drink.getDname());
            mDtime.setText(drink.getDtime());
            this.key = key;
        }
    }

    class DrinksAdapter extends RecyclerView.Adapter<DrinkItemView>{
        private List<Drink> mDrinkList;
        private List<String> mkeys;

        public DrinksAdapter(List<Drink> mDrinkList, List<String> mkeys) {
            this.mDrinkList = mDrinkList;
            this.mkeys = mkeys;
        }

        public DrinksAdapter() {
            super();
        }

        @NonNull
        @Override
        public DrinkItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DrinkItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DrinkItemView holder, int position) {
            holder.bind(mDrinkList.get(position),mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mDrinkList.size();
        }
    }
}
