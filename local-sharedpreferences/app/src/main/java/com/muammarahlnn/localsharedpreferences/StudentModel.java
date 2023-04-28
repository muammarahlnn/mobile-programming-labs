package com.muammarahlnn.localsharedpreferences;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file StudentModel, 28/04/2023 13.24 by Muammar Ahlan Abimanyu
 */
public class StudentModel implements Parcelable {

    private String name, nim;

    public StudentModel() {
    }

    protected StudentModel(Parcel in) {
        name = in.readString();
        nim = in.readString();
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(nim);
    }
}
