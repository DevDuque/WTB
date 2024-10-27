package br.team.wtb.Screens.Register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import androidx.appcompat.app.AppCompatActivity;

import br.team.wtb.R;
import br.team.wtb.Screens.Inside.HomeActivity;
import br.team.wtb.Utils.Constants;
import br.team.wtb.Utils.Theme.ThemeManager;

public class SplashActivity extends AppCompatActivity {

    // Duração da SplashScreen (1,5 segundo)
    private static final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inicializa o ThemeManager e aplica o tema atual
        ThemeManager themeManager = ThemeManager.getInstance(this);
        themeManager.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        EdgeToEdge.enable(this);

        // Checando se o usuário já estava logado
        SharedPreferences preferences = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(Constants.KEY_IS_LOGGED_IN, false);

        // Encontra a raiz da SplashScreen
        View splashScreen = findViewById(R.id.splash_activity);


        // Começa uma animação de fade-out depois de SPLASH_DISPLAY_LENGTH
        new Handler().postDelayed(() -> {
            Animation fadeOut = new AlphaAnimation(1, 0);

            // Duração do fade-out (1 segundo)
            fadeOut.setDuration(500);
            fadeOut.setFillAfter(true);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    // Start the appropriate activity based on login status
                    Intent mainIntent;
                    if (isLoggedIn) {
                        mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    } else {
                        mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(mainIntent);
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // Optional transition
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            splashScreen.startAnimation(fadeOut);
        }, SPLASH_DISPLAY_LENGTH);


    }
}