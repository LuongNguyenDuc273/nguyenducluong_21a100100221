package deso1.nguyenducluong.dlu_21a100100221;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<FoodModle> foodList;

    public MyAdapter(Context context, List<FoodModle> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(foodList.get(position).getImage()).into(holder.foodImage);
        holder.foodName.setText(foodList.get(position).getName());
        holder.foodPrice.setText(foodList.get(position).getPrice());

        holder.foodCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("Image", foodList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name", foodList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Price", foodList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("Key", foodList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName, foodPrice, foodEdit;
        CardView foodCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.recImage);
            foodName = itemView.findViewById(R.id.recTitle);
            foodPrice = itemView.findViewById(R.id.recPrice);
            foodCard = itemView.findViewById(R.id.recCard);
            foodEdit = itemView.findViewById(R.id.recEdit);

        }
    }
}


