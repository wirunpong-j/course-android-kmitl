package lab07.bellkung.mylazyinstagram.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lab07.bellkung.mylazyinstagram.R;
import lab07.bellkung.mylazyinstagram.model.UserProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private UserProfile userProfile;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(UserProfile userProfile) {
        Bundle args = new Bundle();
        args.putParcelable("userProfile", userProfile);

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

}
