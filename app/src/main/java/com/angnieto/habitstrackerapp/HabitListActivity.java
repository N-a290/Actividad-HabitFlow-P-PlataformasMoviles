package com.angnieto.habitstrackerapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class HabitListActivity extends AppCompatActivity {

    private TextView habitNameConfirmation;
    private TextView habitCategoryConfirmation;
    private TextView habitFrequencyConfirmation;
    private TextView habitDescriptionConfirmation;
    private Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habit_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.habit_list), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de componentes UI
        initViews();

        // Recuperar los datos del Intent y mostrar la información del hábito
        displayHabitData();

        // Configuración del botón para salir de la aplicación
        buttonExit.setOnClickListener(v -> finishAffinity());
    }

    private void initViews() {
        habitNameConfirmation = findViewById(R.id.habitNameConfirmation);
        habitCategoryConfirmation = findViewById(R.id.habitCategoryConfirmation);
        habitFrequencyConfirmation = findViewById(R.id.habitFrequencyConfirmation);
        habitDescriptionConfirmation = findViewById(R.id.habitDescriptionConfirmation);
        buttonExit = findViewById(R.id.button3);
    }

    private void displayHabitData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;

        }

        // Intenta obtener el objeto Habit completo de la información recibida
        final Habit habit;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            habit = intent.getSerializableExtra(HabitActivity.EXTRA_HABIT_OBJECT, Habit.class);
        } else {
            @SuppressWarnings("deprecation")
            Object serializableObj = intent.getSerializableExtra(HabitActivity.EXTRA_HABIT_OBJECT);
            habit = (serializableObj instanceof Habit) ? (Habit) serializableObj : null;
        }

        if (habit != null) {
            habitNameConfirmation.setText(habit.getName());
            habitCategoryConfirmation.setText(habit.getCategory());
            habitFrequencyConfirmation.setText(habit.getFrequency());
            habitDescriptionConfirmation.setText(habit.getDescription());
        } else {
            // Fallback usando las claves individuales si el objeto no se encuentra
            String name = intent.getStringExtra(HabitActivity.EXTRA_HABIT_NAME);
            String category = intent.getStringExtra(HabitActivity.EXTRA_HABIT_CATEGORY);
            String frequency = intent.getStringExtra(HabitActivity.EXTRA_HABIT_FREQUENCY);
            String description = intent.getStringExtra(HabitActivity.EXTRA_HABIT_DESCRIPTION);

            if (name != null) habitNameConfirmation.setText(name);
            if (category != null) habitCategoryConfirmation.setText(category);
            if (frequency != null) habitFrequencyConfirmation.setText(frequency);
            if (description != null) habitDescriptionConfirmation.setText(description);
        }
    }
}
