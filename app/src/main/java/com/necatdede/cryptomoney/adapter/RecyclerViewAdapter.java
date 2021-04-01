package com.necatdede.cryptomoney.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.necatdede.cryptomoney.R;
import com.necatdede.cryptomoney.model.CryptoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {


    Activity activity1;


    private ArrayList<CryptoModel> cryptoList;
    private String[] colors={"#FF0000","#00FF00","#0000FF","#FFFF00","#00FFFF","#FF00FF"};

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList,Activity activity) {
        this.cryptoList = cryptoList;
        activity1=activity;

    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
         View view =layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoList.get(position),colors,position);

    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName,textPrice,textCurrency;
        ImageView logo;

        public RowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CryptoModel cryptoModel,String[] colors,Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 6]));

            textName=itemView.findViewById(R.id.textName);
            textCurrency=itemView.findViewById(R.id.textCurrency);
            textPrice=itemView.findViewById(R.id.textPrice);
            logo=itemView.findViewById(R.id.logo);


            textName.setText(cryptoModel.name);
            textCurrency.setText((cryptoModel.currency));
            textPrice.setText("$"+cryptoModel.price);
            String url =cryptoModel.logo_url;
            String kontrol =url.substring(url.length()-3);
            if (kontrol.matches("svg"))
            {
                SvgLoader.pluck()
                        .with(activity1)
                        .load(cryptoModel.logo_url, logo);
            }
            else if(kontrol.matches("png")){
                Picasso.get().load(cryptoModel.logo_url).into(logo);

            }













        }
    }
}
