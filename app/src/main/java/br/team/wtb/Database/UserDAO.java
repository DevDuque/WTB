package br.team.wtb.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.team.wtb.Model.User;

public class UserDAO {
    private final SQLiteDatabase db;

    public UserDAO(Context context) {
        UserDatabaseHelper dbHelper = new UserDatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Método inserir um usuário
    public boolean insert(User user) {
        ContentValues values = new ContentValues();
        values.put("id", user.getId().toString());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("cellphone", user.getCellphone());

        long result = db.insert("users", null, values);
        return result != -1;  // Retorna true se a inserção foi realizada corretamente
    }

    // Método autenticar o login
    public User login(String email, String password) {
        Cursor cursor = db.query(
                "users",
                null,
                "email = ? AND password = ?",
                new String[]{email, password},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String cellphone = cursor.getString(cursor.getColumnIndexOrThrow("cellphone"));
            cursor.close();
            return new User(name, email, cellphone, password);
        }

        if (cursor != null) cursor.close();
        return null;
    }
}
