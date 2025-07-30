package com.zeki.marketapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.zeki.marketapplication.Adapter.BasketAdapter;
import com.zeki.marketapplication.R;
import com.zeki.marketapplication.databinding.ActivityBasketBinding;
import com.zeki.marketapplication.products.ProductDetail;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Basket extends AppCompatActivity {
    ArrayList<ProductDetail> productArraylist;
    BasketAdapter basketAdapter;
    ActivityBasketBinding binding;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        binding = ActivityBasketBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        basketAdapter = new BasketAdapter(productArraylist);
       binding.recyclerView.setAdapter(basketAdapter);
       firebaseFirestore = FirebaseFirestore.getInstance();
    }
 private void  getData(){
      firebaseFirestore.collection("Products").orderBy("date" , Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
          @Override
          public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
         if (error != null){
             Toast.makeText(Basket.this , error.getLocalizedMessage() , Toast.LENGTH_LONG).show();
         }
          if (value != null){
              for (DocumentSnapshot documentSnapshot : value.getDocuments()){
                  Map <String , Object>data = documentSnapshot.getData();
                  String downloadUrl =(String) data.get("downloadUrl");
                  String price = (String) data.get("price");
                  ProductDetail productDetail = new ProductDetail(downloadUrl , price);
                  productArraylist.add(productDetail);
              }
              basketAdapter.notifyDataSetChanged();
          }


          }
      });
 }




}