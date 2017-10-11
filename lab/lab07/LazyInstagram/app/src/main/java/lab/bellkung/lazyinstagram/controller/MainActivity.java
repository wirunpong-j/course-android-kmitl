package lab.bellkung.lazyinstagram.controller;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import lab.bellkung.lazyinstagram.R;
import lab.bellkung.lazyinstagram.api.LazyInstagramAPI;
import lab.bellkung.lazyinstagram.model.UserProfile;
import lab.bellkung.lazyinstagram.view.GridFragment;
import lab.bellkung.lazyinstagram.view.ProfileFragment;
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

        initialUserSpinner();
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
                    updateFragment(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {}
        });
    }

    private void updateFragment(UserProfile userProfile) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.userFragment, ProfileFragment.newInstance(userProfile))
                .addToBackStack(null)
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.imageFragment, GridFragment.newInstance(userProfile))
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

}
