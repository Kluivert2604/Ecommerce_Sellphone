package com.kieudatquochung.ecommercesellphone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.kieudatquochung.ecommercesellphone.Activity.DetailActivity;
import com.kieudatquochung.ecommercesellphone.Activity.MainActivity;
import com.kieudatquochung.ecommercesellphone.Domain.PopularDomain;
import com.kieudatquochung.ecommercesellphone.R;

import java.util.ArrayList;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.ViewHolder> {
    ArrayList<PopularDomain> items;
    Context context;

    public PopularListAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularListAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(items.get(position).getTitle());
        holder.txtFee.setText(items.get(position).getPrice()+ "VND");
        holder.txtScore.setText(""+items.get(position).getScore());

        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object", items.get(position).getTitle());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtFee, txtScore;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtFee = itemView.findViewById(R.id.txtFee);
            txtScore = itemView.findViewById(R.id.txtScore);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
