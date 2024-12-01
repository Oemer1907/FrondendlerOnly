package com.bestfit.demo.views.customer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.CustomerLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Taks")
@Route(value = "customer/taks", layout = CustomerLayout.class)
public class CustomerTaksView extends VerticalLayout {

    private List<Plan> plans = new ArrayList<>();
    private Grid<Plan> planGrid;

    public CustomerTaksView() {
        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        // Title
        H2 header = new H2("My Training Plans");

        // Buttons
        Button addPlanButton = new Button("New Plans", event -> openAddPlanDialog());
        addPlanButton.getStyle().set("background-color", "#FFA500");
        addPlanButton.getStyle().set("color", "white");

        Button manualPlanButton = new Button("Manual Plan", event -> openManualPlanDialog());
        manualPlanButton.getStyle().set("background-color", "#007BFF");
        manualPlanButton.getStyle().set("color", "white");

        // PDF Export Button
        Button pdfExportButton = new Button("Export PDF", event -> {
            // Placeholder for PDF export functionality
            Notification.show("PDF export triggered!", 3000, Notification.Position.MIDDLE);
        });
        pdfExportButton.getStyle().set("background-color", "#FFA500");
        pdfExportButton.getStyle().set("color", "white");

        HorizontalLayout buttonLayout = new HorizontalLayout(addPlanButton, manualPlanButton, pdfExportButton);
        buttonLayout.setSpacing(true);

        // Plan Table
        planGrid = new Grid<>(Plan.class, false);
        planGrid.addColumn(Plan::getCategory).setHeader("Category").setSortable(true);
        planGrid.addColumn(Plan::getMachine).setHeader("Machine");
        planGrid.addColumn(Plan::getSets).setHeader("Sets");
        planGrid.addColumn(Plan::getReps).setHeader("Reps");
        planGrid.addComponentColumn(this::createActions).setHeader("Actions");
        planGrid.setWidthFull();

        // Example Plans
        plans.add(new Plan("Muscle Gain", "Leg Press", 3, 12));
        plans.add(new Plan("Cardio", "Treadmill", 4, 0));
        plans.add(new Plan("Weight Loss", "Elliptical", 3, 15));
        planGrid.setItems(plans);

        // Layout
        add(header, buttonLayout, planGrid);
    }

    private void openAddPlanDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Plan");

        ComboBox<String> categoryField = new ComboBox<>("Category");
        categoryField.setItems("Muscle Gain", "Cardio", "Weight Loss");
        categoryField.setPlaceholder("Select category");

        Button saveButton = new Button("Save Plan", event -> {
            if (categoryField.isEmpty()) {
                Notification.show("Please select a category!", 3000, Notification.Position.MIDDLE);
            } else {
                String category = categoryField.getValue();
                List<Plan> newPlans = new ArrayList<>();
                switch (category) {
                    case "Muscle Gain":
                        newPlans.add(new Plan("Muscle Gain", "Leg Press", 4, 12));
                        newPlans.add(new Plan("Muscle Gain", "Bench Press", 3, 10));
                        break;
                    case "Cardio":
                        newPlans.add(new Plan("Cardio", "Treadmill", 5, 0));
                        newPlans.add(new Plan("Cardio", "Elliptical", 4, 0));
                        break;
                    case "Weight Loss":
                        newPlans.add(new Plan("Weight Loss", "Rowing Machine", 3, 15));
                        newPlans.add(new Plan("Weight Loss", "Cycling", 4, 0));
                        break;
                }
                plans.addAll(newPlans);
                planGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(categoryField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openManualPlanDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add Manual Plan");

        ComboBox<String> categoryField = new ComboBox<>("Category");
        categoryField.setItems("Muscle Gain", "Cardio", "Weight Loss");
        categoryField.setPlaceholder("Select category");

        ComboBox<String> machineField = new ComboBox<>("Machine");
        machineField.setItems("Leg Press", "Treadmill", "Elliptical", "Rowing Machine", "Bench Press");
        machineField.setPlaceholder("Select machine");

        NumberField setField = new NumberField("Sets");
        setField.setPlaceholder("Enter sets (e.g., 3)");
        setField.setMin(1);
        setField.setStep(1);

        NumberField repsField = new NumberField("Reps");
        repsField.setPlaceholder("Enter reps (e.g., 12)");
        repsField.setMin(1);
        repsField.setStep(1);

        Button saveButton = new Button("Save Plan", event -> {
            if (categoryField.isEmpty() || machineField.isEmpty() || setField.isEmpty() || repsField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                String category = categoryField.getValue();
                String machine = machineField.getValue();
                int sets = setField.getValue().intValue();
                int reps = repsField.getValue().intValue();
                plans.add(new Plan(category, machine, sets, reps));
                planGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(categoryField, machineField, setField, repsField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Plan plan) {
        Button editButton = new Button("Edit", event -> {
            Dialog editDialog = new Dialog();
            editDialog.setHeaderTitle("Edit Plan");

            NumberField setField = new NumberField("Sets");
            setField.setValue((double) plan.getSets());

            NumberField repsField = new NumberField("Reps");
            repsField.setValue((double) plan.getReps());

            Button saveButton = new Button("Save", saveEvent -> {
                plan.setSets(setField.getValue().intValue());
                plan.setReps(repsField.getValue().intValue());
                planGrid.getDataProvider().refreshAll();
                editDialog.close();
            });
            saveButton.getStyle().set("background-color", "#FFA500");
            saveButton.getStyle().set("color", "white");

            VerticalLayout editLayout = new VerticalLayout(setField, repsField, saveButton);
            editDialog.add(editLayout);
            editDialog.open();
        });
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", deleteEvent -> {
            plans.remove(plan);
            planGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        HorizontalLayout actions = new HorizontalLayout(editButton, deleteButton);
        actions.setSpacing(true);
        return actions;
    }

    public static class Plan {
        private String category;
        private String machine;
        private int sets;
        private int reps;

        public Plan(String category, String machine, int sets, int reps) {
            this.category = category;
            this.machine = machine;
            this.sets = sets;
            this.reps = reps;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getMachine() {
            return machine;
        }

        public void setMachine(String machine) {
            this.machine = machine;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public int getReps() {
            return reps;
        }

        public void setReps(int reps) {
            this.reps = reps;
        }
    }
}
