package inshorts.com.inshortsapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import inshorts.com.inshortsapp.R;
import inshorts.com.inshortsapp.model.NewsArticle;

/**
 * Created by Shailendra on 9/17/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<NewsArticle> mNewsArticles;

    public DataAdapter(ArrayList<NewsArticle> articles) {
        mNewsArticles = articles;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_news_article, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        NewsArticle newsArticle = mNewsArticles.get(i);
        String title = newsArticle.getTITLE();
        String publisher = newsArticle.getPUBLISHER();

        TextView titleView = viewHolder.getTitleView();
        TextView publisherView = viewHolder.getPublisherView();

        titleView.setText(title);
        publisherView.setText(publisher);
    }

    @Override
    public int getItemCount() {
        return mNewsArticles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mPublisher;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title);
            mPublisher = (TextView) view.findViewById(R.id.publisher);
        }

        public TextView getPublisherView() {
            return mPublisher;
        }

        public TextView getTitleView() {
            return mTitle;
        }
    }
}
