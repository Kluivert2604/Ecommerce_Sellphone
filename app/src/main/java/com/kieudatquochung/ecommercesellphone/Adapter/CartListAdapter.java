package com.kieudatquochung.ecommercesellphone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.kieudatquochung.ecommercesellphone.Domain.PopularDomain;
import com.kieudatquochung.ecommercesellphone.Helper.ChangNumberItemListener;
import com.kieudatquochung.ecommercesellphone.Helper.ManagementCart;
import com.kieudatquochung.ecommercesellphone.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<PopularDomain> listItemSelected;
    private ManagementCart managementCart;
    ChangNumberItemListener changNumberItemListener;

    public CartListAdapter(ArrayList<PopularDomain> listItemSelected,Context context, ChangNumberItemListener changNumberItemListener) {
        this.listItemSelected = listItemSelected;
        managementCart = new ManagementCart(context);
        this.changNumberItemListener = changNumberItemListener;
    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.feeEachItem.setText(listItemSelected.get(position).getPrice()+ "VND");
        holder.totalEachItem.setText(Math.round((listItemSelected.get(position).getNumberInCart()*listItemSelected.get(position).getPrice())) + "VND");
        holder.num.setText(String.valueOf(listItemSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listItemSelected.get(position).getPicUrl(), "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> {
            managementCart.plusNumberItem(listItemSelected, position, () -> {
                notifyDataSetChanged();
                changNumberItemListener.change();
            });

        });

        holder.minusItem.setOnClickListener(v -> {
            managementCart.minusNumberItem(listItemSelected, position, () -> {
                notifyDataSetChanged();
                changNumberItemListener.change();
            });

        });
    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.txtFeeEachItem);
            totalEachItem = itemView.findViewById(R.id.txtTotalEachItem);
            plusItem = itemView.findViewById(R.id.txtPlusCart);
            minusItem = itemView.findViewById(R.id.txtMinusCart);
            num = itemView.findViewById(R.id.txtNumberItem);
        }
    }
}
