package br.team.wtb.Utils.Theme;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import br.team.wtb.R;
import br.team.wtb.Utils.Constants;

public class ThemeManager {

    // Nome das preferências compartilhadas
    private static final String PREFS_NAME = Constants.PREFS_NAME;
    // Chave para armazenar o tema da aplicação
    private static final String KEY_THEME = Constants.KEY_THEME;




    // Instância única do ThemeManager
    private static ThemeManager instance;

    // Tema atual da aplicação
    private int currentTheme;

    // Construtor para inicializar o ThemeManager
    private ThemeManager(Context context) {

        // Obtém as preferências compartilhadas e lê o tema atual
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        currentTheme = preferences.getInt(KEY_THEME, R.style.Theme_WTB_Dark);
    }

    // Método para obter a instância única do ThemeManager
    public static ThemeManager getInstance(Context context) {
        if (instance == null) {

            // Inicializa a instância com o contexto da aplicação
            instance = new ThemeManager(context.getApplicationContext());
        }
        return instance;
    }

    // Método para alternar entre os temas claro e escuro
    public void toggleTheme(Context context) {

        // Se o tema atual é o padrão (Escuro), então vira claro, caso contrário, volta pro escuro
        currentTheme = (currentTheme == R.style.Theme_WTB_Dark) ? R.style.Theme_WTB_Light : R.style.Theme_WTB_Dark;
        
        // Salva o novo tema nas preferências compartilhadas
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putInt(KEY_THEME, currentTheme).apply();

        Log.d("ThemeManager", "Current theme saved: " + (currentTheme == R.style.Theme_WTB_Dark ? "Dark" : "Light"));
    }

    // Método para obter o tema atual
    public int getCurrentTheme(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(KEY_THEME, R.style.Theme_WTB_Dark);
    }

    // Método para aplicar o tema na atividade atual
    public void applyTheme(Context context) {
        // Define o tema antes de definir a visualização de conteúdo em cada atividade
        context.setTheme(currentTheme);
    }
}