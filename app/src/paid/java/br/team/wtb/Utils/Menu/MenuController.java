package br.team.wtb.Utils.Menu;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;

import br.team.wtb.R;
import br.team.wtb.Screens.Inside.AboutActivity;
import br.team.wtb.Screens.Inside.FavoritesActivity;
import br.team.wtb.Screens.Inside.HomeActivity;
import br.team.wtb.Screens.Register.LoginActivity;
import br.team.wtb.Utils.Constants;

public class MenuController {

    private final Activity activity;

    // Construtor para iniciar baseado na Activity
    public MenuController(Activity activity) {
        this.activity = activity;
    }

    // Adicionando os botões do menu com seus ouvintes
    public void setupMenu() {
        TextView homeItem = activity.findViewById(R.id.home_item);
        TextView shareItem = activity.findViewById(R.id.share_item);
        TextView aboutItem = activity.findViewById(R.id.about_item);
        TextView favItem = activity.findViewById(R.id.fav_item);
        TextView exitItem = activity.findViewById(R.id.exit_item);

        homeItem.setOnClickListener(v -> {
            Intent intent = new Intent(activity, HomeActivity.class);
            activity.startActivity(intent);
        });

        shareItem.setOnClickListener(v -> {

        });

        aboutItem.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AboutActivity.class);
            activity.startActivity(intent);
        });

        favItem.setOnClickListener(v -> {
            Intent intent = new Intent(activity, FavoritesActivity.class);
            activity.startActivity(intent);
        });

        exitItem.setOnClickListener(v -> {
            // Clear login state
            SharedPreferences preferences = activity.getSharedPreferences(Constants.PREFS_NAME, Activity.MODE_PRIVATE);
            preferences.edit().putBoolean(Constants.KEY_IS_LOGGED_IN, false).apply();

            Intent intent = new Intent(activity, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
        });
    }
}
