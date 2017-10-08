package lab.bellkung.lazyinstagram.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import lab.bellkung.lazyinstagram.R;
import lab.bellkung.lazyinstagram.adapter.PostAdapter;
import lab.bellkung.lazyinstagram.api.LazyInstagramAPI;
import lab.bellkung.lazyinstagram.model.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // get profile form api
        getUserProfile("android");
    }

    private void getUserProfile(String name){
        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagramAPI.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyInstagramAPI lazyInstagramAPI = retrofit.create(LazyInstagramAPI.class);

        Call<UserProfile> call = lazyInstagramAPI.getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    display(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {}
        });
    }

    private void display(UserProfile userProfile){
        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText(String.valueOf(userProfile.getPost()));

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText(String.valueOf(userProfile.getFollower()));

        TextView textFollowing = findViewById(R.id.textFollwing);
        textFollowing.setText(String.valueOf(userProfile.getFollowing()));

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        CircularImageView imageProfile = findViewById(R.id.imageProfile);
        imageProfile.setBorderColor(Color.DKGRAY);
        imageProfile.setBorderWidth(2);
        Glide.with(this).load(userProfile.getUrlProfile()).into(imageProfile);

        RecyclerView list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 3));
        PostAdapter adapter = new PostAdapter(this);
        adapter.setData(userProfile.getPosts());
        list.setAdapter(adapter);

    }

}
