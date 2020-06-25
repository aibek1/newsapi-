package com.hfad.newsapiisubbota.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.newsapiisubbota.R;
import com.hfad.newsapiisubbota.activity.WebAct;
import com.hfad.newsapiisubbota.categories.Const;
import com.hfad.newsapiisubbota.categories.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {

    private Context context;
    private List<News.Article> newsList;
    private String newsType;
    private String url;

    public NewsAdapter(Context context, List<News.Article> newsList, String newsType) {
        this.context = context;
        this.newsList = newsList;
        this.url=url;
        this.newsType = newsType;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

            view = LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.time.setText(newsList.get(position).getPublishedAt());
        holder.mTextView.setText(newsList.get(position).getTitle());
        String av = newsList.get(position).getAuthor();
        /*if (av == null) {
            av="null";
        }

         */


        holder.avtor.setText(av);
        if(newsList.get(position).getUrlToImage()==null)
            holder.mImageView.setImageResource(R.drawable.no_image);
        else{
            Glide.with(context).asBitmap()
                    .load(newsList.get(position).getUrlToImage())
                    .into(holder.mImageView);
        }

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        private TextView mTextView;
        private CardView mCardView;
        private TextView avtor;
        private TextView time;

        public MyHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.image_card);
            mTextView=(TextView)itemView.findViewById(R.id.text_card_c);
            mCardView=(CardView)itemView.findViewById(R.id.card_view_categorii);
            avtor=(TextView)itemView.findViewById(R.id.avtor_list);
            time = (TextView)itemView.findViewById(R.id.time);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    url=newsList.get(getAdapterPosition()).getUrl();
                    Intent intent = new Intent(context, WebAct.class);
                    intent.putExtra(Const.NEWS_TYPE,newsType);
                    intent.putExtra(Const.URL,url);
                    intent.putExtra(Const.POSITION,getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
}
