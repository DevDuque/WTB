package br.team.wtb.Screens.Register;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.team.wtb.Database.UserDAO;
import br.team.wtb.Model.User;
import br.team.wtb.R;
import br.team.wtb.Utils.Theme.ThemeManager;

import java.util.UUID;

public class SignInActivity extends AppCompatActivity {

    private LinearLayout switchContainer, loginLink;
    private EditText loginInput, passwordInput, nameInput, cellphoneInput;
    private Button signBtn;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = ThemeManager.getInstance(this);
        themeManager.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EdgeToEdge.enable(this);

        switchContainer = findViewById(R.id.theme_switch);
        switchContainer.setOnClickListener(v -> {
            themeManager.toggleTheme(SignInActivity.this);
            recreate();
        });

        loginInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        nameInput = findViewById(R.id.name_input);
        cellphoneInput = findViewById(R.id.cellphone_input);
        signBtn = findViewById(R.id.btn_sign);
        loginLink = findViewById(R.id.option_login);

        userDAO = new UserDAO(this);  // Inicializa o DAO

        signBtn.setOnClickListener(view -> registerUser());

        loginLink.setOnClickListener(view -> finish());
    }

    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = loginInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String cellphone = cellphoneInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cellphone.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria um novo usuário e insere no banco
        User user = new User(name, email, cellphone, password);
        boolean success = userDAO.insert(user);

        if (success) {
            Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show();
            Log.d("SignInActivity", "Usuário criado com sucesso: " + user.toString());
            finish();  // Fecha a tela de registro
        } else {
            Toast.makeText(this, "Erro ao criar usuário.", Toast.LENGTH_SHORT).show();
        }
    }
}
