package com.zeki.marketapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zeki.marketapplication.R;

public class menuActivity extends AppCompatActivity {
FirebaseAuth auth;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signout){
            auth.signOut();
            Intent intentToMain = new Intent(menuActivity.this, MainActivity.class);
            startActivity(intentToMain);
            finish();
        } else if (item.getItemId() == R.id.product) {
            Intent intent = new Intent(menuActivity.this , Products.class);
            Toast.makeText(this, "Ürün bölümüne gidiyorsunuz", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}