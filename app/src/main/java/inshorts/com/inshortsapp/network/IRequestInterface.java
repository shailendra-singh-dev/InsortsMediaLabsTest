package inshorts.com.inshortsapp.network;

import java.util.List;

import inshorts.com.inshortsapp.model.NewsArticle;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shailendra on 9/17/2017.
 */

public interface IRequestInterface {

    @GET("newsjson")
    Call<List<NewsArticle>> getNewsArticles();

}
