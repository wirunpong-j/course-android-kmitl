package lab07.bellkung.mylazyinstagram.controller;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


import lab07.bellkung.mylazyinstagram.R;
import lab07.bellkung.mylazyinstagram.api.LazyInstagramAPI;
import lab07.bellkung.mylazyinstagram.model.UserProfile;
import lab07.bellkung.mylazyinstagram.view.ImageViewFragment;
import lab07.bellkung.mylazyinstagram.view.ProfileFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private UserProfile userProfile;
    private ConstraintLayout rootView, loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        this.loadingView = findViewById(R.id.loadingView);
        this.rootView = findViewById(R.id.rootView);

        initialUserSpinner();
    }

    private void getUserProfile(String name){
        // start activity indicator
        showLoadingView();

        Retrofit retrofit = getClient();

        LazyInstagramAPI lazyInstagramAPI = retrofit.create(LazyInstagramAPI.class);
        Call<UserProfile> call = lazyInstagramAPI.getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                //end activity indicator
                if (response.isSuccessful()){
                    userProfile = response.body();
                    updateFragment(userProfile, 3);
                    hideLoadingView();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {}
        });
    }

    private void sendPosts(String name, boolean isFollow) {
        showLoadingView();
        Retrofit retrofit = getClient();

        LazyInstagramAPI lazyInstagramAPI = retrofit.create(LazyInstagramAPI.class);
        Call<UserProfile> call = lazyInstagramAPI.postProfile(name, isFollow);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    Log.v("Status : ", response.body().getMessage());
                    updateFragment(userProfile, 3);
                    hideLoadingView();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {}
        });

    }

    private Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        return new Retrofit
                .Builder()
                .client(client)
                .baseUrl(LazyInstagramAPI.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void updateFragment(UserProfile userProfile, int row) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.userFragment, ProfileFragment.newInstance(userProfile))
                .addToBackStack(null)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.imageFragment, ImageViewFragment.newInstance(userProfile, row))
                .addToBackStack(null)
                .commit();
    }

    private void initialUserSpinner() {
        // User Spinner
        Spinner spinner = findViewById(R.id.users_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String username = (String) adapterView.getItemAtPosition(i);
                getUserProfile(username);

                ImageButton gridBtn = findViewById(R.id.gridBtn);
                ImageButton listBtn = findViewById(R.id.listBtn);
                gridBtn.setImageResource(R.drawable.grid_pressed);
                listBtn.setImageResource(R.drawable.list);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array,
                android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

    public void onFollowBtnPressed(View view) {
        this.userProfile.setFollow(!this.userProfile.isFollow());
        sendPosts(this.userProfile.getUser(), this.userProfile.isFollow());
    }

    public void onViewImagePressed(View view) {
        ImageButton gridBtn = findViewById(R.id.gridBtn);
        ImageButton listBtn = findViewById(R.id.listBtn);
        switch (view.getId()) {
            case R.id.gridBtn:
                gridBtn.setImageResource(R.drawable.grid_pressed);
                listBtn.setImageResource(R.drawable.list);
                updateFragment(this.userProfile, 3);
                break;

            case R.id.listBtn:
                gridBtn.setImageResource(R.drawable.grid);
                listBtn.setImageResource(R.drawable.list_pressed);
                updateFragment(this.userProfile, 1);
                break;
        }
    }

    private void showLoadingView() {
        this.loadingView.setVisibility(View.VISIBLE);
        this.rootView.setClickable(false);

    }

    private void hideLoadingView() {
        this.loadingView.setVisibility(View.INVISIBLE);
        this.rootView.setClickable(true);
    }
}
