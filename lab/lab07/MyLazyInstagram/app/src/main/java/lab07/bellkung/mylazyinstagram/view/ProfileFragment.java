package lab07.bellkung.mylazyinstagram.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import lab07.bellkung.mylazyinstagram.R;
import lab07.bellkung.mylazyinstagram.model.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private UserProfile userProfile;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(UserProfile userProfile) {
        Bundle args = new Bundle();
        args.putParcelable("userProfile", userProfile);

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.userProfile = getArguments().getParcelable("userProfile");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (this.userProfile != null) {
            initailView(view);
        }

        return view;
    }

    private void initailView(View view) {
        TextView textPost = view.findViewById(R.id.textPost);
        textPost.setText(String.valueOf(this.userProfile.getPost()));

        TextView textFollower = view.findViewById(R.id.textFollower);
        textFollower.setText(String.valueOf(this.userProfile.getFollower()));

        TextView textFollowing = view.findViewById(R.id.textFollwing);
        textFollowing.setText(String.valueOf(this.userProfile.getFollowing()));

        TextView textBio = view.findViewById(R.id.textBio);
        textBio.setText(this.userProfile.getBio());

        CircularImageView imageProfile = view.findViewById(R.id.imageProfile);
        imageProfile.setBorderColor(Color.DKGRAY);
        imageProfile.setBorderWidth(2);
        Glide.with(this).load(this.userProfile.getUrlProfile()).into(imageProfile);

        Button followBtn = view.findViewById(R.id.followBtn);
        if (this.userProfile.isFollow()) {
            followBtn.setText("Followed");
        } else {
            followBtn.setText("Follow");
        }

    }

}