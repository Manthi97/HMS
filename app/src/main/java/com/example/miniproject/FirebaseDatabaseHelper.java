package com.example.miniproject;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabse;
    private DatabaseReference mReferenceDrinks;
    private DatabaseReference mReferenceFoods;
    private DatabaseReference mReferenceWeights;
    private DatabaseReference mReferenceWaters;
    private List<Drink> drinks = new ArrayList<>();
    private List<Food> foods = new ArrayList<>();
    private List<Weight> weights = new ArrayList<>();



    public interface  DataStatusDrink{
        void DataIsLoaded(List<Drink>drinks, List<String>keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }
    public interface DataStatusWeight{
        void DataIsLoaded(List<Weight> weights, List<String>keys);
        void DataIsInserted();
        void DataisUpdated();
        void DataIsDeleted();
    }

    public interface DataStatus{
        void DataIsLoaded(List<Food> foods, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FirebaseDatabaseHelper() {

        mDatabse = FirebaseDatabase.getInstance();
        mReferenceFoods = mDatabse.getReference("Food");
        mReferenceWeights = mDatabse.getReference("Weight");
        mReferenceDrinks = mDatabse.getReference("Drink");
        mReferenceWaters = mDatabse.getReference("Water");

    }



    public void readDrinks( final DataStatusDrink dataStatusDrink){
        mReferenceDrinks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                drinks.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Drink drink = keyNode.getValue(Drink.class);
                    drinks.add(drink);
                }
                dataStatusDrink.DataIsLoaded(drinks,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readweight(final DataStatusWeight dataStatusWeight){
        mReferenceWeights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                weights.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Weight weight = keyNode.getValue(Weight.class);
                    weights.add(weight);
                }
                dataStatusWeight.DataIsLoaded(weights, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void readfood(final DataStatus dataStatus){

        mReferenceFoods.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                foods.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());

                    Food food = keyNode.getValue(Food.class);
                    foods.add(food);
                }

                dataStatus.DataIsLoaded(foods,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addfood(Food food,final DataStatus dataStatus){
        String key = mReferenceFoods.push().getKey();
        mReferenceFoods.child(key).setValue(food)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void updateFood(String key, Food food, final DataStatus dataStatus){
        mReferenceFoods.child(key).setValue(food)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                                dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteFood(String key, final DataStatus dataStatus){
        mReferenceFoods.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
    public void addweight(Weight weight , final DataStatusWeight dataStatusWeight){
        String key = mReferenceWeights.push().getKey();
        mReferenceWeights.child(key).setValue(weight)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatusWeight.DataIsInserted();
                    }
                });
    }

    public void updateWeight(String key, Weight weight,final DataStatusWeight dataStatusWeight){
        mReferenceWeights.child(key).setValue(weight).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatusWeight.DataisUpdated();
            }
        });
    }
    public void deleteWeight (String key,final DataStatusWeight dataStatusWeight){
        mReferenceWeights.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatusWeight.DataIsDeleted();

            }
        });
    }

    public void addDrink(Drink drink, final DataStatusDrink dataStatusDrink){
       String key = mReferenceDrinks.push().getKey();
       mReferenceDrinks.child(key).setValue(drink).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
               dataStatusDrink.DataIsInserted();
           }
       });
    }

    public void updateDrink(String key, Drink drink, final  DataStatusDrink dataStatusDrink){
        mReferenceDrinks.child(key).setValue(drink).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatusDrink.DataIsUpdated();
            }
        });
    }
    public void deleteDrink(String  key,final DataStatusDrink dataStatusDrink){
        mReferenceDrinks.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatusDrink.DataIsDeleted();
            }
        });
    }


}
