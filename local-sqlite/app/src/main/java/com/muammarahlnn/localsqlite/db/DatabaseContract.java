package com.muammarahlnn.localsqlite.db;

import android.provider.BaseColumns;

/**
 * @author Muammar Ahlan Abimanyu (muammarahlnn)
 * @file DatabaseContract, 28/04/2023 14.16 by Muammar Ahlan Abimanyu
 */
public class DatabaseContract {

    public static String TABLE_NAME = "student";

    public static final class StudentColumns implements BaseColumns {

        public static String NAME = "name";
        public static String NIM = "nim";
    }
}
