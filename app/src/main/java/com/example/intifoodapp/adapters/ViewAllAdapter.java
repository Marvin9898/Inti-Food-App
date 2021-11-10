package com.example.intifoodapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.intifoodapp.Detail;
import com.example.intifoodapp.R;
import com.example.intifoodapp.models.ViewAllModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> viewAllAdapterList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllAdapterList) {
        this.context = context;
        this.viewAllAdapterList = viewAllAdapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_food,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(viewAllAdapterList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(viewAllAdapterList.get(position).getName());
        holder.description.setText(viewAllAdapterList.get(position).getDescription());
        holder.price.setText(viewAllAdapterList.get(position).getPrice()+" ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("detail",viewAllAdapterList.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        return viewAllAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_description);
            price = itemView.findViewById(R.id.view_price);
        }
    }
}
