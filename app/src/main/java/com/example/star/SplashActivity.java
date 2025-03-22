package com.example.star;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Ajustement des marges pour un affichage plein écran
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupération du logo
        ImageView logo = findViewById(R.id.logo);

        // Lancer les animations successivement et passer à MainActivity après
        logo.animate().rotation(360f).setDuration(2000)
                .withEndAction(() -> logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000)
                        .withEndAction(() -> logo.animate().translationYBy(1000f).setDuration(2000)
                                .withEndAction(() -> logo.animate().alpha(0f).setDuration(6000)
                                        .withEndAction(() -> {
                                            // Démarrer MainActivity après l'animation
                                            Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                                            startActivity(intent);
                                            finish(); // Fermer SplashActivity
                                        }))));
    }
}
