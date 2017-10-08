package lab.bellkung.lazyinstagram.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import lab.bellkung.lazyinstagram.R;
import lab.bellkung.lazyinstagram.controller.CustomOnItemSelectedListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersSpinnerFragment extends Fragment  {

    public UsersSpinnerFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users_spinner, container, false);

        // User Spinner
        Spinner spinner = view.findViewById(R.id.users_spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.user_array,
                android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);


        return view;
    }

}
