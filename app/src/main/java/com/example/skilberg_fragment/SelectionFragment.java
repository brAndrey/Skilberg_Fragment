package com.example.skilberg_fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectionFragment extends Fragment {

    @Nullable
    private OnColorSelectedListener onColorSelectedListener;

    public SelectionFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selection,container,false);

      // view.setBackgroundColor(Color.RED);

        RadioGroup radioGroup = view.findViewById(R.id.colors_rg);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        return view;
    }

    private final RadioGroup.OnCheckedChangeListener onCheckedChangeListener =
            new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            @ColorRes int colorResId;

            switch (checkedId){
                case R.id.red_rd:
                    colorResId=R.color.colorRed;
                    break;

                case R.id.green_rd:
                    colorResId=R.color.colorGreen;
                    break;

                case R.id.blue_rd:
                    colorResId=R.color.colorBlue;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid id: " + checkedId);
            }
            Context context = getActivity();
            if (context != null){
                int color = context.getResources().getColor(colorResId);
                if (onColorSelectedListener != null){
                    onColorSelectedListener.onColorSelected(color);
                }
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        onColorSelectedListener=(OnColorSelectedListener) context;
    }

    @Override
    public void onDetach() {
        onColorSelectedListener = null;
        super.onDetach();
    }

    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }
}
