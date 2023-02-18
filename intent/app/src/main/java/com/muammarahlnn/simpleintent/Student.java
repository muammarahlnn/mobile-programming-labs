package com.muammarahlnn.simpleintent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file Student, 17/02/2023 22.21 by Muammar Ahlan Abimanyu
 */
public class Student implements Parcelable {

    private String name;
    private String nim;

    public Student() {
    }

    public Student(String name, String nim) {
        this.name = name;
        this.nim = nim;
    }
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
