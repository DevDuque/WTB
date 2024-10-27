package br.team.wtb.Screens.Register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.team.wtb.Database.UserDAO;
import br.team.wtb.Model.User;
import br.team.wtb.R;
import br.team.wtb.Screens.Inside.HomeActivity;
import br.team.wtb.Utils.Theme.ThemeManager;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout switchContainer, signLink;

    private EditText loginInput, passwordInput;

    private Button loginBtn;

    private ImageView logo;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager themeManager = ThemeManager.getInstance(this);
        themeManager.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EdgeToEdge.enable(this);

        switchContainer = findViewById(R.id.theme_switch);
        switchContainer.setOnClickListener(v -> {
            themeManager.toggleTheme(LoginActivity.this);
            recreate();
        });

        loginInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.btn_login);
        signLink = findViewById(R.id.option_sign);

        logo = findViewById(R.id.logo);

        userDAO = new UserDAO(this);  // Inicializa o DAO

        // Botões de Login e SignIn respectivamente
        loginBtn.setOnClickListener(v -> loginUser());
        signLink.setOnClickListener(v -> {
            Intent signScreen = new Intent(LoginActivity.this, SignInActivity.class);
            startActivity(signScreen);
        });

        // Deleta os usuários quando segurar na logo
        logo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Deletar todos os usuários
                userDAO.deleteAll();
                Toast.makeText(LoginActivity.this, "Todos os usuários foram deletados.", Toast.LENGTH_SHORT).show();
                return true; // Retorna true para indicar que o evento foi tratado
            }
        });
    }

    private void loginUser() {
        String email = loginInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.error_empty, Toast.LENGTH_SHORT).show();
            return;
        }

        User user = userDAO.login(email, password);  // Tenta realizar o login

        if (user != null) {

            Toast.makeText(this, R.string.success_login, Toast.LENGTH_SHORT).show();
            Intent homeScreen = new Intent(LoginActivity.this, HomeActivity.class);

            Log.d("LoginActivity", "Usuário logado com sucesso: " + user.toString());

            startActivity(homeScreen);
            finish();  // Fecha a tela de login
        } else {
            Toast.makeText(this, R.string.error_login, Toast.LENGTH_SHORT).show();
        }
    }
}
