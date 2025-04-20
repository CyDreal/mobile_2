package com.example.main_mobile;

import static java.util.Locale.filter;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_mobile.adapter.Praktikum_5_2_CustomAdapter;

import java.util.ArrayList;

public class Praktikum_5_2 extends AppCompatActivity {

    ArrayList<String> data=new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_praktikum52);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        data.add("Rolex");
        data.add("Bell & Ross");
        data.add("Ulysee Nardin");
        data.add("Piaget SA");
        data.add("Bremont");
        data.add("Blancpain");

        buildRecyclerView(data);

        searchView = findViewById(R.id.searchView_pra_5_2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText.toLowerCase());
                return false;
            }
        });
    }

    public void filter(String text) {
        Log.i("Info Search",""+data.size());
        Log.i("Info Search","data ke 1"+data.get(1));
        ArrayList<String> filteredlist = new ArrayList<>();
        for(int i=0;i<data.size();i++) {
            if(data.get(i).toLowerCase().contains(text)){
                filteredlist.add(data.get(i));
            }
        }
        buildRecyclerView(filteredlist);
    }

    private void buildRecyclerView(ArrayList<String> data) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView_pra_5_2);
        Praktikum_5_2_CustomAdapter adapter = new Praktikum_5_2_CustomAdapter(data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}