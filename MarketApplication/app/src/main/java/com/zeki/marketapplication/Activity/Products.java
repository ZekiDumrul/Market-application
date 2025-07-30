package com.zeki.marketapplication.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.zeki.marketapplication.Adapter.BasketAdapter;
import com.zeki.marketapplication.R;
import com.zeki.marketapplication.databinding.ActivityProductsBinding;
import com.zeki.marketapplication.products.ProductDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Products extends AppCompatActivity {

    ActivityProductsBinding binding;
    Uri imageData;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ArrayList<ProductDetail> productArraylist;
    BasketAdapter basketAdapter;

   ActivityResultLauncher <Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        binding.imageView5.setImageURI(imageData);
        binding.imageView6.setImageURI(imageData);
        binding.imageView7.setImageURI(imageData);
        binding.imageView8.setImageURI(imageData);
        productArraylist = new ArrayList<>();

    }

    public void productButtonOnClick (View view){
        UUID uuid = UUID.randomUUID();
        String imageName = "images/" + uuid + ".jpg";
        storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageReference newReference = firebaseStorage.getReference(imageName);
                newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUrl = uri.toString();

                        HashMap<String , Object> addItem = new HashMap<>();
                        binding.imageView5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = "Ikram";
                                String price = binding.textView.getText().toString();

                                addItem.put("downloadUrl" , downloadUrl);
                                addItem.put("price" , price );
                                addItem.put("name" , name);


                            }
                        });
                        binding.imageView6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String name = "Olala";
                                String price = binding.textView2.getText().toString();

                                addItem.put("price" , price);
                                addItem.put("name" , name);
                                addItem.put("downloadurl" , downloadUrl);

                            }
                        });
                        binding.imageView7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String name = "gofret";
                                String price = binding.textView3.getText().toString();

                                addItem.put("price" , price);
                                addItem.put("name" , name);
                                addItem.put("downloadurl" , downloadUrl);
                            }
                        });
                        binding.imageView8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String price = binding.textView4.getText().toString();
                                String name = "bisküvi";
                                addItem.put("price" , price);
                                addItem.put("name" , name);
                                addItem.put("downloadurl" , downloadUrl);
                            }
                        });
                        firebaseFirestore.collection("Products").add(addItem).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Products.this , "Selected items successfully added" , Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Products.this , e.getLocalizedMessage() , Toast.LENGTH_LONG).show();
                            }
                        });
                        firebaseFirestore.collection("Products").add(addItem).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Products.this , "Succes add products" , Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                           Toast.makeText(Products.this , e.getLocalizedMessage() , Toast.LENGTH_LONG) .show();
                            }
                        });


                    }
                });




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Products.this , e.getLocalizedMessage() , Toast.LENGTH_LONG).show();
            }
        });








    }

  public void basketClickButton (View view){
        Intent intent = new Intent(Products.this , Basket.class);
        startActivity(intent);
  }


}