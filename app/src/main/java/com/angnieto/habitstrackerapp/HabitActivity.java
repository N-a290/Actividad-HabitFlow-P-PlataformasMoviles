package com.angnieto.habitstrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class HabitActivity extends AppCompatActivity {

    // Constantes para las claves del Intent Extras
    public static final String EXTRA_HABIT_NAME = "extra_habit_name";
    public static final String EXTRA_HABIT_CATEGORY = "extra_habit_category";
    public static final String EXTRA_HABIT_FREQUENCY = "extra_habit_frequency";
    public static final String EXTRA_HABIT_DESCRIPTION = "extra_habit_description";
    public static final String EXTRA_HABIT_OBJECT = "extra_habit_object";

    // Elementos de la interfaz de usuario
    private TextInputEditText inputName;
    private CheckBox checkBoxSalud;
    private CheckBox checkBoxEstudio;
    private CheckBox checkBoxProductividad;
    private RadioGroup radioGroupFrequency;
    private TextInputEditText inputDescription;
    private Button buttonCreateHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_habit);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.habit), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de componentes UI
        initViews();

        // Listener para el botón Crear
        buttonCreateHabit.setOnClickListener(v -> handleCreateHabit());
    }

    private void initViews() {
        inputName = findViewById(R.id.inputName);
        checkBoxSalud = findViewById(R.id.checkBox);
        checkBoxEstudio = findViewById(R.id.checkBox2);
        checkBoxProductividad = findViewById(R.id.checkBox3);
        radioGroupFrequency = findViewById(R.id.radioGroupFrequency);
        inputDescription = findViewById(R.id.inputDescription);
        buttonCreateHabit = findViewById(R.id.buttonCreateHabit);
    }

    private void handleCreateHabit() {
        // 1. Obtener y validar el nombre del hábito
        String name = inputName.getText() != null ? inputName.getText().toString().trim() : "";
        if (TextUtils.isEmpty(name)) {
            inputName.setError("Por favor ingrese el nombre del hábito");
            inputName.requestFocus();
            return;
        }

        // 2. Obtener categorías seleccionadas
        List<String> selectedCategories = new ArrayList<>();
        if (checkBoxSalud.isChecked()) {
            selectedCategories.add(checkBoxSalud.getText().toString());
        }
        if (checkBoxEstudio.isChecked()) {
            selectedCategories.add(checkBoxEstudio.getText().toString());
        }
        if (checkBoxProductividad.isChecked()) {
            selectedCategories.add(checkBoxProductividad.getText().toString());
        }

        String categoriesString;
        if (selectedCategories.isEmpty()) {
            categoriesString = "General";
        } else {
            categoriesString = TextUtils.join(", ", selectedCategories);
        }

        // 3. Obtener frecuencia seleccionada
        String frequencyString;
        int selectedRadioButtonId = radioGroupFrequency.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            frequencyString = (selectedRadioButton != null) ? selectedRadioButton.getText().toString() : "Diario";
        } else {
            frequencyString = "Diario";
        }

        // 4. Obtener descripción
        String description = inputDescription.getText() != null ? inputDescription.getText().toString().trim() : "";
        if (TextUtils.isEmpty(description)) {
            description = "Sin descripción";
        }

        // 5. Crear objeto Habit con los datos capturados
        Habit habit = new Habit(name, categoriesString, frequencyString, description);

        // 6. Enviar datos mediante Intent a HabitListActivity
        Intent intent = new Intent(HabitActivity.this, HabitListActivity.class);
        intent.putExtra(EXTRA_HABIT_NAME, habit.getName());
        intent.putExtra(EXTRA_HABIT_CATEGORY, habit.getCategory());
        intent.putExtra(EXTRA_HABIT_FREQUENCY, habit.getFrequency());
        intent.putExtra(EXTRA_HABIT_DESCRIPTION, habit.getDescription());
        intent.putExtra(EXTRA_HABIT_OBJECT, habit);

        startActivity(intent);
    }
}
