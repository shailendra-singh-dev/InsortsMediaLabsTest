package inshorts.com.inshortsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import inshorts.com.inshortsapp.R;
import inshorts.com.inshortsapp.adapter.DataAdapter;
import inshorts.com.inshortsapp.adapter.RecycleViewItemTouchListener;
import inshorts.com.inshortsapp.interfaces.RecyclerViewOnItemClickListener;
import inshorts.com.inshortsapp.model.NewsArticle;
import inshorts.com.inshortsapp.network.IRequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static inshorts.com.inshortsapp.activities.NewsArticleDetails.ARG_NEWS_ARTICLE;

public class NewsArticlesList extends AppCompatActivity {

    private static final String BASE_URL = "http://starlord.hackerearth.com/";

    private RecyclerView mRecyclerView;
    private ArrayList<NewsArticle> mNewsArticles;
    private DataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_articles_list);
        initViews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new RecycleViewItemTouchListener(this, new RecyclerViewOnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                NewsArticle newsArticle = mNewsArticles.get(position);
                Intent intent = new Intent(NewsArticlesList.this, NewsArticleDetails.class);
                intent.putExtra(ARG_NEWS_ARTICLE, newsArticle);
                startActivity(intent);
            }
        }));
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRequestInterface request = retrofit.create(IRequestInterface.class);
        Call<List<NewsArticle>> call = request.getNewsArticles();

        call.enqueue(new Callback<List<NewsArticle>>() {
            @Override
            public void onResponse(Call<List<NewsArticle>> call, Response<List<NewsArticle>> response) {
                mNewsArticles = (ArrayList<NewsArticle>) response.body();
                mDataAdapter = new DataAdapter(mNewsArticles);
                mRecyclerView.setAdapter(mDataAdapter);
            }

            @Override
            public void onFailure(Call<List<NewsArticle>> call, Throwable t) {
            }
        });
    }
}
