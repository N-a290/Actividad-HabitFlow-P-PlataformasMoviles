package com.angnieto.habitstrackerapp;

import androidx.annotation.NonNull;
import java.io.Serializable;

/**
 * Modelo de datos que representa un Hábito en la aplicación.
 * Implementa Serializable para permitir su paso a través de Intents en Android.
 */
public class Habit implements Serializable {

    private String name;
    private String category;
    private String frequency;
    private String description;

    // Constructor completo
    public Habit(String name, String category, String frequency, String description) {
        this.name = name;
        this.category = category;
        this.frequency = frequency;
        this.description = description;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Valida que el nombre del hábito no esté vacío ni nulo.
     * @return true si el hábito tiene un nombre válido.
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", frequency='" + frequency + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
