package com.bestfit.demo.views.customer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.CustomerLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Nutrition Plan")
@Route(value = "customer/nutritionplan", layout = CustomerLayout.class)
public class NutritionPlanView extends VerticalLayout {

    private List<Meal> meals = new ArrayList<>();
    private Grid<Meal> mealGrid;
    private int dailyCalorieGoal = 1000; // Daily target calories
    private int totalCarbs = 0;
    private int totalFats = 0;
    private int totalProteins = 0;
    private Span calorieCounter;

    public NutritionPlanView() {
        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        // Title
        H2 header = new H2("My Nutrition Plan");

        // Daily Calorie and Macro Counter
        calorieCounter = new Span(getCalorieSummary());
        calorieCounter.getStyle().set("font-weight", "bold");
        calorieCounter.getStyle().set("font-size", "18px");
        calorieCounter.getStyle().set("color", "green");

        // Add Food Button
        Button addFoodButton = new Button("Add Food", event -> openAddFoodDialog());
        addFoodButton.getStyle().set("background-color", "#007BFF");
        addFoodButton.getStyle().set("color", "white");

        // PDF Export Button
        Button pdfExportButton = new Button("Export PDF", event -> {
            // Placeholder for PDF export functionality
            Notification.show("PDF export triggered!", 3000, Notification.Position.MIDDLE);
        });
        pdfExportButton.getStyle().set("background-color", "#FFA500");
        pdfExportButton.getStyle().set("color", "white");

        HorizontalLayout buttonLayout = new HorizontalLayout(addFoodButton, pdfExportButton);
        buttonLayout.setSpacing(true);

        // Meal Grid
        mealGrid = new Grid<>(Meal.class, false);
        mealGrid.addColumn(Meal::getName).setHeader("Meal Name").setSortable(true);
        mealGrid.addColumn(Meal::getGrams).setHeader("Grams");
        mealGrid.addColumn(Meal::getCarbs).setHeader("Carbs (g)");
        mealGrid.addColumn(Meal::getFats).setHeader("Fats (g)");
        mealGrid.addColumn(Meal::getProteins).setHeader("Proteins (g)");
        mealGrid.addColumn(Meal::getCalories).setHeader("Calories");
        mealGrid.addComponentColumn(meal -> createDeleteButton(meal)).setHeader("Actions");
        mealGrid.setWidthFull();

        // Layout
        add(header, calorieCounter, buttonLayout, mealGrid);
    }

    private void openAddFoodDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add Food");

        // Meal Name
        TextField foodField = new TextField("Meal Name");
        foodField.setPlaceholder("Enter meal name (e.g., Chicken)");

        // Grams
        NumberField gramsField = new NumberField("Grams");
        gramsField.setPlaceholder("Enter grams (e.g., 100)");
        gramsField.setStep(1);
        gramsField.setMin(1);

        // Carbs
        NumberField carbsField = new NumberField("Carbs (g)");
        carbsField.setPlaceholder("Enter carbs (e.g., 20)");
        carbsField.setStep(1);
        carbsField.setMin(0);

        // Fats
        NumberField fatsField = new NumberField("Fats (g)");
        fatsField.setPlaceholder("Enter fats (e.g., 5)");
        fatsField.setStep(1);
        fatsField.setMin(0);

        // Proteins
        NumberField proteinsField = new NumberField("Proteins (g)");
        proteinsField.setPlaceholder("Enter proteins (e.g., 25)");
        proteinsField.setStep(1);
        proteinsField.setMin(0);

        // Calories
        NumberField calorieField = new NumberField("Calories");
        calorieField.setPlaceholder("Enter calories (e.g., 250)");
        calorieField.setStep(1);
        calorieField.setMin(1);

        Button saveButton = new Button("Save Food", event -> {
            if (foodField.isEmpty() || gramsField.isEmpty() || carbsField.isEmpty() || fatsField.isEmpty() || proteinsField.isEmpty() || calorieField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                String name = foodField.getValue();
                int grams = gramsField.getValue().intValue();
                int carbs = carbsField.getValue().intValue();
                int fats = fatsField.getValue().intValue();
                int proteins = proteinsField.getValue().intValue();
                int calories = calorieField.getValue().intValue();

                meals.add(new Meal(name, grams, carbs, fats, proteins, calories));
                mealGrid.setItems(meals);

                dailyCalorieGoal -= calories;
                totalCarbs += carbs;
                totalFats += fats;
                totalProteins += proteins;
                updateCalorieCounter();

                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(foodField, gramsField, carbsField, fatsField, proteinsField, calorieField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void updateCalorieCounter() {
        calorieCounter.setText(getCalorieSummary());
        if (dailyCalorieGoal <= 0) {
            calorieCounter.getStyle().set("color", "blue");
        } else {
            calorieCounter.getStyle().set("color", "green");
        }
    }

    private String getCalorieSummary() {
        return "Remaining Calories: " + dailyCalorieGoal + " kcal | Carbs: " + totalCarbs + "g | Fats: " + totalFats + "g | Proteins: " + totalProteins + "g";
    }

    private Button createDeleteButton(Meal meal) {
        Button deleteButton = new Button("Delete", event -> {
            meals.remove(meal);
            mealGrid.setItems(meals);

            dailyCalorieGoal += meal.getCalories();
            totalCarbs -= meal.getCarbs();
            totalFats -= meal.getFats();
            totalProteins -= meal.getProteins();
            updateCalorieCounter();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");
        return deleteButton;
    }

    // Meal Model
    public static class Meal {
        private String name;
        private int grams;
        private int carbs;
        private int fats;
        private int proteins;
        private int calories;

        public Meal(String name, int grams, int carbs, int fats, int proteins, int calories) {
            this.name = name;
            this.grams = grams;
            this.carbs = carbs;
            this.fats = fats;
            this.proteins = proteins;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public int getGrams() {
            return grams;
        }

        public int getCarbs() {
            return carbs;
        }

        public int getFats() {
            return fats;
        }

        public int getProteins() {
            return proteins;
        }

        public int getCalories() {
            return calories;
        }
    }
}
