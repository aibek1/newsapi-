package com.hfad.newsapiisubbota.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.newsapiisubbota.R;
import com.hfad.newsapiisubbota.activity.ListActivitySub;
import com.hfad.newsapiisubbota.categories.Categorii;
import com.hfad.newsapiisubbota.categories.Const;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyHolder> {
    private Context context;
    private List<Categorii> list;
    public CategoriesAdapter(Context context, List<Categorii> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_categor,parent,false);
        return new  MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.mImageView.setImageResource(list.get(position).getImage());
        holder.mTextView.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;
        CardView mCardView;

        public MyHolder( View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.image_card);
            mTextView=(TextView)itemView.findViewById(R.id.text_card_c);
            mCardView=(CardView)itemView.findViewById(R.id.card_view_categorii);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ListActivitySub.class);
                    i.putExtra(Const.NEWS_TYPE, list.get(getAdapterPosition()).getNewsType());
                    context.startActivity(i);
                }
            });

        }
    }
}
