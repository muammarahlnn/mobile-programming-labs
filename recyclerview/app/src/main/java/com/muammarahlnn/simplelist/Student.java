package com.muammarahlnn.simplelist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Student, 18/02/2023 21.10 by Muammar Ahlan Abimanyu
 */
public class Student implements Parcelable {

    private String name;
    private String nim;

    protected Student(Parcel in) {
        name = in.readString();
        nim = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
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

    public Student(String name, String nim) {
        this.name = name;
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
