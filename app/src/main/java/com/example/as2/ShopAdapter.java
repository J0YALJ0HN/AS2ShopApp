package com.example.as2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> prodNameList, prodDescList, categoryList, prodListPriceList, prodRetailPriceList, prodQuantityList, prodDateCreatedList;
    private OnItemClickListener clickListener;
    private AtomicInteger quantity;
    private DBHelper DB;

    public ShopAdapter(Context context, ArrayList<String> prodNameList, ArrayList<String> prodDescList, ArrayList<String> categoryList, ArrayList<String> prodListPriceList, ArrayList<String> prodRetailPriceList, ArrayList<String> prodQuantityList, ArrayList<String> prodDateCreatedList) {
        this.context = context;
        this.prodNameList = prodNameList;
        this.prodDescList = prodDescList;
        this.categoryList = categoryList;
        this.prodListPriceList = prodListPriceList;
        this.prodRetailPriceList = prodRetailPriceList;
        this.prodQuantityList = prodQuantityList;
        this.prodDateCreatedList = prodDateCreatedList;
        this.quantity = new AtomicInteger(1);
        this.DB = new DBHelper(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productcard, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.prodName.setText(prodNameList.get(position));
        holder.prodDesc.setText(prodDescList.get(position));
        holder.category.setText(categoryList.get(position));
        holder.prodListPrice.setText(prodListPriceList.get(position));
        holder.prodRetailPrice.setText(prodRetailPriceList.get(position));
        holder.prodQuantity.setText(prodQuantityList.get(position));
        holder.prodDateCreated.setText(prodDateCreatedList.get(position));

        holder.itemView.setOnClickListener(v -> {
            // Show dialog here
            showDialog(holder.getAdapterPosition());
        });
    }


    @Override
    public int getItemCount() {
        return prodNameList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prodName, prodDesc, category, prodListPrice, prodRetailPrice, prodQuantity, prodDateCreated;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.prodName_id);
            prodDesc = itemView.findViewById(R.id.prodDesc_id);
            category = itemView.findViewById(R.id.category_id);
            prodListPrice = itemView.findViewById(R.id.prodListPrice_id);
            prodRetailPrice = itemView.findViewById(R.id.prodRetailPrice_id);
            prodQuantity = itemView.findViewById(R.id.prodQuantity_id);
            prodDateCreated = itemView.findViewById(R.id.prodDateCreated_id);

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(position);
                    }
                }
            });
        }
    }
    private void showDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add to Basket")
                .setMessage("Do you want to add this item to your basket?")
                .setPositiveButton("Add", (dialog, which) -> {
                    String selectedProdName = prodNameList.get(position);
                    String selectedProdRetailPrice = prodRetailPriceList.get(position);
                    String username = getUsernameFromSharedPreferences();

                    // basket table insertion
                    boolean inserted = DB.insertBasketData(username, selectedProdName, selectedProdRetailPrice, quantity.get());

                    if (inserted) {
                        Toast.makeText(context, "Item added to basket", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed to add item to basket", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_to_basket, null);
        builder.setView(dialogView);

        AtomicInteger quantity = new AtomicInteger(1); // initial quantity

        TextView tvQuantity = dialogView.findViewById(R.id.tvQuantity);
        Button btnPlus = dialogView.findViewById(R.id.btnPlus);
        Button btnMinus = dialogView.findViewById(R.id.btnMinus);

        btnPlus.setOnClickListener(v -> {
            quantity.incrementAndGet();
            tvQuantity.setText(String.valueOf(quantity.get()));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity.get() > 0) {
                quantity.decrementAndGet();
                tvQuantity.setText(String.valueOf(quantity.get()));
            }
        });

        builder.show();
    }
    private String getUsernameFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", "");
    }



}
