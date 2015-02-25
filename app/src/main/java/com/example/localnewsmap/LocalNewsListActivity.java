package com.example.localnewsmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LocalNewsListActivity extends Activity {
    private long dma;
    public final String BASE_URL = "http://mobile-homerun-yql.media.yahoo.com:4080/v2/localnews/newsfeed?dma_id=";
    private ArrayList<News> newsList = new ArrayList<News>();
    private ArrayAdapter<News> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_news_list);
        dma =
                getIntent().getLongExtra("dma", 0);
        Toast.makeText(this, String.valueOf(dma), Toast.LENGTH_SHORT).show();

        fetchLocalNews(String.valueOf(dma));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_news_list, menu);
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

    private void fetchLocalNews(String dmaId) {
        String url = BASE_URL + dmaId;
        AsyncHttpClient client = new AsyncHttpClient();

        adapter = new NewsArrayAdapter(this, newsList);
        ListView lvNewsStream = (ListView) findViewById(R.id.lvNewsStream);
        lvNewsStream.setAdapter(adapter);
        lvNewsStream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                News news = newsList.get(position);
                Intent intent = new Intent(LocalNewsListActivity.this, NewsDetailActivity.class);
                intent.putExtra("news", news);
                startActivity(intent);
            }
        });

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray newsListJSON = null;

                try {
                    newsList.clear();
                    newsListJSON = response.getJSONObject("items").getJSONArray("result");
                    adapter.addAll(News.fromJsonArray(newsListJSON));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
