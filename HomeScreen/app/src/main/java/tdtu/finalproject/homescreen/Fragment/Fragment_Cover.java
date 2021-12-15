package tdtu.finalproject.homescreen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import tdtu.finalproject.homescreen.R;

public class Fragment_Cover extends Fragment {

    View view;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cover, container, false);
        imageView = view.findViewById(R.id.ivCover);
        return view;
    }

    public void Play(String image) {
        Picasso.with(getContext()).load(image).into(imageView);
    }
}
