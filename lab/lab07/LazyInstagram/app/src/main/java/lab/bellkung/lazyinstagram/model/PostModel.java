package lab.bellkung.lazyinstagram.model;

/**
 * Created by BellKunG on 7/10/2017 AD.
 */

public class PostModel {
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
}
