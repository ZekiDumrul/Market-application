package com.zeki.marketapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zeki.marketapplication.databinding.RecyclerRowBinding;
import com.zeki.marketapplication.products.ProductDetail;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ProductHolder> {
ArrayList<ProductDetail> productArrayList;
public  BasketAdapter (ArrayList<ProductDetail> productArrayList){
    this.productArrayList = productArrayList;
}

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);


        return new ProductHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
     holder.recyclerRowBinding.recyclerViewCommentText.setText(productArrayList.get(position).price);
     Picasso.get().load(productArrayList.get(position).downloadUrl).into(holder.recyclerRowBinding.recyclerViewImageView);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder{
       RecyclerRowBinding recyclerRowBinding;
        public ProductHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
