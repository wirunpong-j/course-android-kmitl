package lab07.bellkung.mylazyinstagram.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BellKunG on 13/10/2017 AD.
 */

public class PostsModel implements Parcelable {
    private int comment;
    private int like;
    private String url;

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.comment);
        dest.writeInt(this.like);
        dest.writeString(this.url);
    }

    public PostsModel() {
    }

    protected PostsModel(Parcel in) {
        this.comment = in.readInt();
        this.like = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<PostsModel> CREATOR = new Parcelable.Creator<PostsModel>() {
        @Override
        public PostsModel createFromParcel(Parcel source) {
            return new PostsModel(source);
        }

        @Override
        public PostsModel[] newArray(int size) {
            return new PostsModel[size];
        }
    };
}

