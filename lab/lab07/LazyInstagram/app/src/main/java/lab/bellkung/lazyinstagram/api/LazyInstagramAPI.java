package lab.bellkung.lazyinstagram.api;

import lab.bellkung.lazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by BellKunG on 7/10/2017 AD.
 */

public interface LazyInstagramAPI {
    final String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
