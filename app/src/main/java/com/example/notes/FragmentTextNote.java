package com.example.notes;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTextNote extends Fragment {

    public static final String ARG_PARAM1 = "indexNote";
    public Notes notes;

    public static FragmentTextNote newInstance(Notes notes) {
        FragmentTextNote fragment = new FragmentTextNote();

        //Передача параметров
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_text_note, container, false);

        AppCompatTextView textView = view.findViewById(R.id.text_note);
        //Получение по id изображения из массива
        //TypedArray texts = getResources().obtainTypedArray(R.array.notes_text);
        //int text = texts.getResourceId(indexNote, -1);
        //Получение по id текста из массива
        //String[] texts = getResources().getStringArray(R.array.notes_text);
        //String text = texts[indexNote];
        textView.setText(notes.getTextNote());

        TextView textView1 = view.findViewById(R.id.textView);
        textView1.setText(notes.getNameNote());

        return view;
    }
}