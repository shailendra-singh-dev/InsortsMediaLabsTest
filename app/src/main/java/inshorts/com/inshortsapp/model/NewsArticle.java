package inshorts.com.inshortsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shailendra on 9/17/2017.
 */

public class NewsArticle implements Parcelable{

    private String ID;
    private String TITLE;
    private String URL;
    private String PUBLISHER;
    private String CATEGORY;
    private String HOSTNAME;
    private long TIMESTAMP;

    protected NewsArticle(Parcel in) {
        ID = in.readString();
        TITLE = in.readString();
        URL = in.readString();
        PUBLISHER = in.readString();
        CATEGORY = in.readString();
        HOSTNAME = in.readString();
        TIMESTAMP = in.readLong();
    }

    public static final Creator<NewsArticle> CREATOR = new Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(TITLE);
        parcel.writeString(URL);
        parcel.writeString(PUBLISHER);
        parcel.writeString(CATEGORY);
        parcel.writeString(HOSTNAME);
        parcel.writeLong(TIMESTAMP);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPUBLISHER() {
        return PUBLISHER;
    }

    public void setPUBLISHER(String PUBLISHER) {
        this.PUBLISHER = PUBLISHER;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getHOSTNAME() {
        return HOSTNAME;
    }

    public void setHOSTNAME(String HOSTNAME) {
        this.HOSTNAME = HOSTNAME;
    }

    public long getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(long TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }
}
