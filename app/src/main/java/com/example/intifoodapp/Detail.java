package com.example.intifoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.intifoodapp.models.PopularModel;
import com.example.intifoodapp.models.RecommendedModel;
import com.example.intifoodapp.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Detail extends AppCompatActivity {

    TextView qty;
    int totalQty = 1;
    int totalPrice = 0;
    ImageView detailImg;
    TextView price, description;
    Button addToCart;
    ImageView addItem, removeItem;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;
    PopularModel popularModel =null;
    RecommendedModel recommendedModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }
        if(object instanceof PopularModel){
            popularModel = (PopularModel) object;
        }

        if(object instanceof RecommendedModel){
            recommendedModel = (RecommendedModel) object;
        }

        detailImg = findViewById(R.id.detail_img);
        price = findViewById(R.id.detail_price);
        description = findViewById(R.id.detail_description);
        addToCart = findViewById(R.id.add_to_cart);
        addItem = findViewById(R.id.add_food);
        removeItem = findViewById(R.id.remove_food);
        qty = findViewById(R.id.qty);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        if (viewAllModel !=null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailImg);
            description.setText(viewAllModel.getDescription());
            price.setText("RM"+viewAllModel.getPrice()+" ");

            totalPrice = viewAllModel.getPrice() * totalQty;
        }

        if (popularModel !=null){
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(detailImg);
            description.setText(popularModel.getDescription());
            price.setText("RM"+popularModel.getPrice()+" ");

            totalPrice = popularModel.getPrice() * totalQty;
        }

        if (recommendedModel !=null){
            Glide.with(getApplicationContext()).load(recommendedModel.getImg_url()).into(detailImg);
            description.setText(recommendedModel.getDescription());
            price.setText("RM"+recommendedModel.getPrice()+" ");

            totalPrice = recommendedModel.getPrice() * totalQty;
        }

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQty < 99){
                    totalQty++;
                    qty.setText(String.valueOf(totalQty));
                    if (viewAllModel !=null) {
                        totalPrice = viewAllModel.getPrice() * totalQty;
                    }
                    if (popularModel !=null) {
                        totalPrice = popularModel.getPrice() * totalQty;
                    }
                    if (recommendedModel !=null) {
                        totalPrice = recommendedModel.getPrice() * totalQty;
                    }
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQty > 0){
                    totalQty--;
                    qty.setText(String.valueOf(totalQty));
                    if (viewAllModel !=null) {
                        totalPrice = viewAllModel.getPrice() * totalQty;
                    }
                    if (popularModel !=null) {
                        totalPrice = popularModel.getPrice() * totalQty;
                    }
                    if (recommendedModel !=null) {
                        totalPrice = recommendedModel.getPrice() * totalQty;
                    }
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

    }

    private void addToCart() {


        final HashMap<String,Object> cartMap = new HashMap<>();

        if(viewAllModel !=null) {
            cartMap.put("foodName", viewAllModel.getName());
            cartMap.put("foodPrice", price.getText().toString());
            cartMap.put("totalQuantity", qty.getText().toString());
            cartMap.put("totalPrice", totalPrice);
        }

        if(popularModel !=null) {
            cartMap.put("foodName", popularModel.getName());
            cartMap.put("foodPrice", price.getText().toString());
            cartMap.put("totalQuantity", qty.getText().toString());
            cartMap.put("totalPrice", totalPrice);
        }

        if(recommendedModel !=null) {
            cartMap.put("foodName", recommendedModel.getName());
            cartMap.put("foodPrice", price.getText().toString());
            cartMap.put("totalQuantity", qty.getText().toString());
            cartMap.put("totalPrice", totalPrice);
        }


        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Detail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}