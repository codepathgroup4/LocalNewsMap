package com.example.localnewsmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by daisych on 2/22/15.
 */
public class NewsArrayAdapter extends ArrayAdapter<News> {

    public NewsArrayAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
        }
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvPubliser= (TextView) convertView.findViewById(R.id.tvPublisher);
        TextView tvPublishedAt = (TextView) convertView.findViewById(R.id.tvPublishedAt);

        tvTitle.setText(news.getTitle());
        tvPubliser.setText(news.getPublisher());
        tvPublishedAt.setText(news.getPublishedAt());
        ivImage.setImageResource(0);
        Picasso.with(getContext()).load(news.getImageUrl()).into(ivImage);
        return convertView;
    }


}
