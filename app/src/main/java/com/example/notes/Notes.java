package com.example.notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notes implements Parcelable {

    private int indexNote;
    private String textNote;
    private String nameNote;
    private Date dateNote = new Date();

    public Notes(int indexNote, String nameNote){
        this.indexNote = indexNote;
        this.nameNote  = nameNote;
    }

    public Notes(int indexNote, String nameNote, String textNote){
        this.indexNote = indexNote;
        this.textNote  = textNote;
        this.nameNote  = nameNote;
    }

    protected Notes(Parcel in) {
        indexNote = in.readInt();
        textNote  = in.readString();
        nameNote  = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(indexNote);
        dest.writeString(textNote);
        dest.writeString(nameNote);
    }

    public int getIndexNote() {
        return indexNote;
    }

    public void setIndexNote(int indexNote) {
        this.indexNote = indexNote;
    }
}
