package com.example.itea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class FragmentHome extends Fragment {
    TextView text;
    Button buttonToHome2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        text = v.findViewById(R.id.fragment_name_text);
        buttonToHome2 = v.findViewById(R.id.button_to_home2);

        buttonToHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_Home_to_Home2);
            }
        });

        return v;
    }
}
