package br.team.wtb.Screens;

import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;
import androidx.appcompat.app.AppCompatActivity;

import br.team.wtb.R;
import br.team.wtb.Utils.ThemeManager;

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
                    // Começa a LoginActivity
                    Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            splashScreen.startAnimation(fadeOut);
        }, SPLASH_DISPLAY_LENGTH);


    }
}