package lab.bellkung.lazyinstagram.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by BellKunG on 7/10/2017 AD.
 */

public class UserProfile implements Parcelable {

    private String bio;
    private int follower;
    private int following;
    private boolean isFollow;
    private int post;
    private List<PostModel> posts;

    private String user;
    private String urlProfile;

    protected UserProfile(Parcel in) {
        bio = in.readString();
        follower = in.readInt();
        following = in.readInt();
        isFollow = in.readByte() != 0;
        post = in.readInt();
        user = in.readString();
        urlProfile = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bio);
        dest.writeInt(follower);
        dest.writeInt(following);
        dest.writeByte((byte) (isFollow ? 1 : 0));
        dest.writeInt(post);
        dest.writeString(user);
        dest.writeString(urlProfile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }
}
