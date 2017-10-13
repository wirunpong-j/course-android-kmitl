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
public class GridFragment extends Fragment {

    private UserProfile userProfile;

    public GridFragment() {
        // Required empty public constructor
    }

    public static GridFragment newInstance(UserProfile userProfile) {

        Bundle args = new Bundle();
        args.putParcelable("userProfile", userProfile);

        GridFragment fragment = new GridFragment();
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
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        if (this.userProfile != null) {
            initialView(view);
        }

        return view;
    }

    private void initialView(View view) {
        RecyclerView list = view.findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        PostAdapter adapter = new PostAdapter(getActivity());
        adapter.setData(this.userProfile.getPosts());
        list.setAdapter(adapter);
    }

}
