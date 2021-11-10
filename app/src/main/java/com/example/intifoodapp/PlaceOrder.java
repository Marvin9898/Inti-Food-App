package com.example.intifoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.intifoodapp.models.MyCartModel;
import com.example.intifoodapp.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class PlaceOrder extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        homeButton = findViewById(R.id.back_to_home);

        List<MyCartModel> myCartModelList = (ArrayList<MyCartModel>) getIntent().getSerializableExtra("itemList");

        if(myCartModelList !=null && myCartModelList.size()>0){
            for (MyCartModel cartModel  : myCartModelList){
                final HashMap<String,Object> cartMap = new HashMap<>();

                String saveCurrentDate, saveCurrentTime;
                Calendar callforDate = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentDate = currentDate.format((callforDate.getTime()));

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format((callforDate.getTime()));


                    cartMap.put("foodName", cartModel.getFoodName());
                    cartMap.put("foodTotalQuantity", cartModel.getTotalQuantity());
                    cartMap.put("totalPrice", cartModel.getTotalPrice());
                    cartMap.put("currentTime", saveCurrentTime);
                    cartMap.put("currentDate", saveCurrentDate);

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(PlaceOrder.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });
    }

    public void onBackPressed(){

    }

}