package com.example.fetch.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fetch.R;
import com.example.fetch.adapter.ItemsAdapter;
import com.example.fetch.model.Item;
import com.example.fetch.model.ListItem;
import com.example.fetch.network.ApiService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // UI components and adapter for RecyclerView
    private RecyclerView itemsRecyclerView;
    private ItemsAdapter itemsAdapter;
    private TextView tvTotalItems, tvFilteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view from the layout resource
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and text views
        itemsRecyclerView = findViewById(R.id.recyclerView);
        tvTotalItems = findViewById(R.id.tvTotalItems);
        tvFilteredItems = findViewById(R.id.tvFilteredItems);

        // Set up RecyclerView with a LinearLayoutManager and the adapter
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsAdapter = new ItemsAdapter(new ArrayList<>());
        itemsRecyclerView.setAdapter(itemsAdapter);

        // Initialize Retrofit for network requests
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the API service and fetch items
        ApiService apiService = retrofit.create(ApiService.class);
        fetchItems(apiService);
    }

    private void fetchItems(ApiService apiService) {
        // Asynchronously fetch items from the API
        apiService.fetchItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Item> items = response.body();
                    // Group items by listId and sort each group by the number in the item name
                    Map<Integer, List<Item>> groupedItems = items.stream()
                            .filter(item -> item.getName() != null && !item.getName().trim().isEmpty())
                            .collect(Collectors.groupingBy(Item::getListId,
                                    Collectors.collectingAndThen(Collectors.toList(),
                                            list -> list.stream().sorted(Comparator.comparingInt(item -> {
                                                        try {
                                                            // Extract and convert the number from the item name for sorting
                                                            return Integer.parseInt(extractNumber(item.getName()));
                                                        } catch (NumberFormatException e) {
                                                            Log.e("SortError", "Failed to parse number from item name", e);
                                                            return 0;
                                                        }
                                                    }))
                                                    .collect(Collectors.toList()))));

                    // Prepare list items for display in RecyclerView
                    List<ListItem> listItems = new ArrayList<>();
                    groupedItems.forEach((listId, itemList) -> {
                        listItems.add(new ListItem("ListId: " + listId)); // Group header
                        itemList.forEach(item -> listItems.add(new ListItem(item))); // Sorted items within the group
                    });

                    // Update UI on the main thread
                    runOnUiThread(() -> {
                        tvTotalItems.setText("Total Items: " + items.size());
                        tvFilteredItems.setText("Filtered Items: " + items.stream().filter(item -> item.getName() != null && !item.getName().trim().isEmpty()).count());
                        itemsAdapter.setItems(listItems);
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                // Log failure and show a user-friendly message
                Log.e("MainActivity", "Failed to fetch items", t);
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to load items. Please check your internet connection and try again.", Toast.LENGTH_LONG).show());
            }
        });
    }

    // Helper method to extract numbers from a string
    private String extractNumber(String name) {
        return name.replaceAll("\\D+", "");
    }
}
