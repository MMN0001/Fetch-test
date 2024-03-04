package com.example.fetch.network;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;
import com.example.fetch.model.Item;

public interface ApiService {
    @GET("hiring.json")
    Call<List<Item>> fetchItems();
}
