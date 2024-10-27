package br.team.wtb.Utils.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE users (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "cellphone TEXT NOT NULL);";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela no momento da criação do banco
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualiza o esquema, se necessário
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
