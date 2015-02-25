package com.example.localnewsmap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends Activity {
    private TextView tvTitle;
    private TextView tvPublisher;
    private ImageView ivImage;
    private TextView tvParagraph;
    private TextView tvPublishedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        News news = (News)getIntent().getSerializableExtra("news");

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvPublisher = (TextView)findViewById(R.id.tvPublisher);
        ivImage = (ImageView)findViewById(R.id.ivImage);
        tvParagraph = (TextView)findViewById(R.id.tvParagraph);
        tvPublishedAt = (TextView)findViewById(R.id.tvPublishedAt);

        tvTitle.setText(news.getTitle());
        tvPublisher.setText(news.getPublisher());
        tvPublishedAt.setText(news.getPublishedAt());
        ivImage.setImageResource(0);
        Picasso.with(this).load(news.getImageUrl()).into(ivImage);
        tvParagraph.setText(Html.fromHtml(news.getContent()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
