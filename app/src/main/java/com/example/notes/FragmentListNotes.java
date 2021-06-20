package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FragmentListNotes extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Notes currentNotes;
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notes, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаём список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] notes = getResources().getStringArray(R.array.notes);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for(int i=0; i < notes.length; i++){
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int index = i;
            tv.setOnClickListener(v -> {
                String[] texts = getResources().getStringArray(R.array.notes_text);
                String text = texts[index];

                currentNotes = new Notes(index, note, text);
                showNotes(currentNotes);
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNotes);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            currentNotes = savedInstanceState.getParcelable(CURRENT_NOTE);
        }else {
            currentNotes = new Notes(0, getResources().getStringArray(R.array.notes)[0]);
        }

        if (isLandscape){
            showLandNotes(currentNotes);
        }
    }

    private void showNotes(Notes currentNotes) {
        if (isLandscape){
            showLandNotes(currentNotes);
        }else {
            showPortNotes(currentNotes);
        }
    }

    private void showLandNotes(Notes currentNotes) {
        FragmentTextNote detail = FragmentTextNote.newInstance(currentNotes);

        FragmentManager fragmentManager = requireActivity()
                .getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.text_note, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotes (Notes currentNotes){
        Intent intent = new Intent();
        intent.setClass(getActivity(), NotesTextActivity.class);
        intent.putExtra(FragmentTextNote.ARG_PARAM1, currentNotes);
        startActivity(intent);
    }
}