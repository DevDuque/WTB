package br.team.wtb.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.team.wtb.Model.User;
import br.team.wtb.Utils.Database.UserDatabaseHelper;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO implements DAO<User> {
    private final SQLiteDatabase db;

    public UserDAO(Context context) {
        UserDatabaseHelper dbHelper = new UserDatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Método para inserir um usuário
    public User insert(User user) {
        // Verifica se o email já existe antes de inserir
        if (emailExists(user.getEmail())) {
            return null; // Retorna null se o email já existe
        }

        ContentValues values = new ContentValues();
        values.put("id", user.getId().toString());
        values.put("name", user.getName());
        values.put("email", user.getEmail());

        // Encriptografa senha antes de salvar
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        values.put("password", hashedPassword);
        values.put("cellphone", user.getCellphone());

        long result = db.insert("users", null, values);
        return result != -1 ? user : null;  // Retorna o usuario se a inserção foi realizada corretamente
    }


    // Método para listar todos os usuários
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Cursor cursor = db.query("users", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                String cellphone = cursor.getString(cursor.getColumnIndexOrThrow("cellphone"));

                users.add(new User(name, email, password, cellphone));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    // Método autenticar o login
    public User login(String email, String password) {
        // Busca o usuário pelo email
        Cursor cursor = db.query(
                "users",
                null,
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String userID = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String hashedPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String cellphone = cursor.getString(cursor.getColumnIndexOrThrow("cellphone"));
            cursor.close();

            // Checa a senha inserida com a encriptografada
            if (BCrypt.checkpw(password, hashedPassword)) {
                return new User(UUID.fromString(userID), name, email, cellphone, hashedPassword);
            }
        }

        if (cursor != null) cursor.close();
        return null;  // Retorna nulo se não conseguiu autenticar
    }

    // Método para verificar se o email já existe
    public boolean emailExists(String email) {
        Cursor cursor = db.query(
                "users",
                new String[]{"COUNT(*) AS count"},
                "email = ?",
                new String[]{email},
                null,
                null,
                null
        );

        boolean exists = false;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(cursor.getColumnIndexOrThrow("count"));
                exists = count > 0;
            }
            cursor.close();
        }

        return exists;
    }


    // Método para encontrar o usuário pelo ID
    @Override
    public User findByID(UUID id) {
        Cursor cursor = db.query(
                "users",
                null,
                "id = ?",
                new String[]{id.toString()},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String cellphone = cursor.getString(cursor.getColumnIndexOrThrow("cellphone"));
            cursor.close();
            return new User(id, name, email, password, cellphone);
        }

        if (cursor != null) cursor.close();
        return null;
    }

    // Método para atualizar usuário
    @Override
    public boolean update(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        values.put("password", hashedPassword);
        values.put("cellphone", user.getCellphone());

        int result = db.update("users", values, "id = ?", new String[]{user.getId().toString()});
        return result > 0;  // Retorna true se a atualização foi realizada corretamente
    }

    public void deleteAll() {
        db.execSQL("DELETE FROM users");
    }

    // Método para deletar usuário
    @Override
    public boolean delete(UUID id) {
        int result = db.delete("users", "id = ?", new String[]{id.toString()});
        return result > 0;  // Retorna true se a exclusão foi realizada corretamente
    }
}
