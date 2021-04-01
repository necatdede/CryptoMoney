package com.necatdede.cryptomoney.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.necatdede.cryptomoney.adapter.RecyclerViewAdapter;
import com.necatdede.cryptomoney.model.CryptoModel;
import com.necatdede.cryptomoney.R;
import com.necatdede.cryptomoney.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL="https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        // https://api.nomics.com/v1/prices?key=05f3474a1b2141489f71f0609616003f

        Gson gson=new GsonBuilder().setLenient().create();

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        loadData();



    }

    private void loadData(){
        CryptoAPI cryptoAPI=retrofit.create(CryptoAPI.class);
        compositeDisposable=new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));
    }

    private void handleResponse(List<CryptoModel> cryptoModelList){
        cryptoModels=new ArrayList<>(cryptoModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels,this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}