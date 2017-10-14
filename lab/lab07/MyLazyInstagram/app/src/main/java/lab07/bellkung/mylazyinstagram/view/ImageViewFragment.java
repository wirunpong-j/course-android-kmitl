package lab07.bellkung.mylazyinstagram.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lab07.bellkung.mylazyinstagram.R;
import lab07.bellkung.mylazyinstagram.adapter.PostAdapter;
import lab07.bellkung.mylazyinstagram.model.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewFragment extends Fragment {

    private UserProfile userProfile;
    private int row;

    public ImageViewFragment() {
        // Required empty public constructor
    }

    public static ImageViewFragment newInstance(UserProfile userProfile, int row) {

        Bundle args = new Bundle();
        args.putParcelable("userProfile", userProfile);
        args.putInt("row", row);

        ImageViewFragment fragment = new ImageViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.userProfile = getArguments().getParcelable("userProfile");
        this.row = getArguments().getInt("row");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_view, container, false);

        if (this.userProfile != null) {
            initialView(view);
        }

        return view;
    }

    private void initialView(View view) {
        RecyclerView list = view.findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(view.getContext(), this.row));
        PostAdapter adapter = new PostAdapter(getActivity());
        adapter.setData(this.userProfile.getPosts());
        adapter.setRow(this.row);
        adapter.setUsername(this.userProfile.getUser());
        adapter.setUserImageProfile(this.userProfile.getUrlProfile());
        list.setAdapter(adapter);
    }

}
