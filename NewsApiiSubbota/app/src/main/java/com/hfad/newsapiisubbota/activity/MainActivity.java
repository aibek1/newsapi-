package com.hfad.newsapiisubbota.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hfad.newsapiisubbota.R;
import com.hfad.newsapiisubbota.adapters.CategoriesAdapter;
import com.hfad.newsapiisubbota.categories.Categorii;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Categorii> categoriiList;
    RecyclerView recyclerView;
    CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoriiList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.categoriesRecyclerView);
        loadAdapter();
        adapter=new CategoriesAdapter(this,categoriiList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    public void loadAdapter(){
        categoriiList.add(new Categorii(R.drawable.tooopp, "ТОП Новости", "top_news"));
        categoriiList.add(new Categorii(R.drawable.zdorovie, "Здоровье", "health"));
        categoriiList.add(new Categorii(R.drawable.entertainment, "Развлечение", "entertainment"));
        categoriiList.add(new Categorii(R.drawable.spoort, "Спорт", "sports"));
        categoriiList.add(new Categorii(R.drawable.business, "Бизнес", "business"));
        categoriiList.add(new Categorii(R.drawable.tech, "Технология", "technology"));
        categoriiList.add(new Categorii(R.drawable.scince, "Наука", "science"));
        categoriiList.add(new Categorii(R.drawable.politika, "Политика", "politics"));
    }
}
