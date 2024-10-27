package br.team.wtb.Screens.Inside;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.team.wtb.Model.Movie;
import br.team.wtb.R;
import br.team.wtb.Utils.Constants;
import br.team.wtb.Utils.Menu.MenuController;
import br.team.wtb.Utils.Movie.MovieAdapter;
import br.team.wtb.Utils.Movie.MovieRepository;
import br.team.wtb.Utils.Theme.ThemeManager;

public class HomeActivity extends AppCompatActivity {

    // Contêiner para o switch do tema
    private LinearLayout switchContainer;

    //  Contêiner e Botão do Menu
    private ImageButton btnMenu;
    private DrawerLayout drawerLayout;

    private MovieAdapter movieAdapter;
    private RecyclerView moviesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Inicializa o ThemeManager e aplica o tema atual
        ThemeManager themeManager = ThemeManager.getInstance(this);
        themeManager.applyTheme(this);

        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(Constants.KEY_IS_LOGGED_IN, false);

        String userId = preferences.getString(Constants.KEY_USER_ID, "Nenhum ID achado, algo deu errado!");
        Log.d("HomeActivity", "ID do usuário logado: " + userId);


        // Inicializa o contêiner do tema
        switchContainer = findViewById(R.id.theme_switch);

        // Configura o ouvinte de clique para o switch
        switchContainer.setOnClickListener(v -> {

            themeManager.toggleTheme(HomeActivity.this);
            recreate();
        });

        // Inicializa o contêiner do menu e botão
        btnMenu = findViewById(R.id.btn_menu);
        drawerLayout = findViewById(R.id.home_activity);

        // Inicializa o menu com Manager
        MenuController menuController = new MenuController(this);
        menuController.setupMenu();

        // Configura o ouvinte de clique para o botão do menu
        btnMenu.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Inicializa o container de filmes
        moviesContainer = findViewById(R.id.movies_container);
        moviesContainer.setLayoutManager(new LinearLayoutManager(this));

        // Pega o Repository (responsável pelo carregamento e adição de filmes) & Seta no Adapter
        MovieRepository movieRepository = MovieRepository.getInstance();
        movieAdapter = new MovieAdapter(movieRepository.getMovies());
        moviesContainer.setAdapter(movieAdapter);
    }
}