package com.example.star;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.star.adapter.StarAdapter;
import com.example.star.beans.Star;
import com.example.star.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
    }
    public void init(){
        service.create(new Star("kate bosworth", "http://www.stars￾photos.com/resize.php?id=801", 3.5f));
        service.create(new Star("george clooney", "http://www.stars￾photos.com/resize.php?id=1191", 3));
        service.create(new Star("michelle rodriguez",
                "http://www.stars￾photos.com/resize.php?id=1120", 5));
        service.create(new Star("george clooney", "http://www.stars￾photos.com/resize.php?id=1193", 1));
        service.create(new Star("louise bouroin", "http://www.stars￾photos.com/resize.php?id=1185", 5));
        service.create(new Star("louise bouroin", "http://www.stars￾photos.com/resize.php?id=1184", 1));
    }
}