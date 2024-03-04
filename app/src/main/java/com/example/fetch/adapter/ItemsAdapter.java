package com.example.fetch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fetch.R;
import com.example.fetch.model.Item;
import com.example.fetch.model.ListItem;
import java.util.List;

// Adapter for RecyclerView to display items and headers based on ListItem type
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    // List to hold ListItem objects which can be headers or items
    private List<ListItem> itemList;

    // Constructor initializing the list
    public ItemsAdapter(List<ListItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    // Create new views
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        // Inflate the appropriate layout based on the ListItem type
        if (viewType == ListItem.TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        }
        return new ItemViewHolder(itemView, viewType);
    }

    @Override
    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        // Set the text for the TextView based on whether it's a header or an item
        if (viewType == ListItem.TYPE_HEADER) {
            holder.itemNameTextView.setText(itemList.get(position).getHeader());
        } else {
            Item currentItem = itemList.get(position).getItem();
            holder.itemNameTextView.setText(currentItem.getName());
        }
    }

    @Override
    // Return the size of your dataset (invoked by the layout manager)
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    // Determine the type of view at a certain position
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    // Update the adapter's dataset and refresh the view
    public void setItems(List<ListItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    // Provide a reference to the views for each data item
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView; // TextView for displaying item name or header title

        public ItemViewHolder(View itemView, int viewType) {
            super(itemView);
            // Initialize the TextView based on the type of view
            if (viewType == ListItem.TYPE_HEADER) {
                itemNameTextView = itemView.findViewById(R.id.headerTextView); // Ensure your header_layout.xml has this ID
            } else {
                itemNameTextView = itemView.findViewById(R.id.textViewName); // Ensure your item_layout.xml has this ID
            }
        }
    }
}





