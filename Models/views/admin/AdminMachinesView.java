package com.bestfit.demo.views.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.AdminLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Maschinen")
@Route(value = "admin/machines", layout = AdminLayout.class)
public class AdminMachinesView extends VerticalLayout {

    private List<Machine> machines = new ArrayList<>(); // Liste der Maschinen
    private Grid<Machine> machineGrid; // Tabelle zur Anzeige der Maschinen

    public AdminMachinesView() {
        setSpacing(true);
        setPadding(true);

        // Button zum Hinzufügen einer neuen Maschine
        Button addMachineButton = new Button("Maschine hinzufügen", event -> openAddMachineDialog());
        addMachineButton.getStyle().set("background-color", "#28a745"); // Grüner Hintergrund
        addMachineButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout headerLayout = new HorizontalLayout(addMachineButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END); // Button rechts ausrichten

        // Tabelle zur Anzeige der Maschinen erstellen
        machineGrid = new Grid<>(Machine.class, false);
        machineGrid.addColumn(Machine::getAreaName).setHeader("Bereichsname");
        machineGrid.addColumn(Machine::getMachineName).setHeader("Maschinenname");
        machineGrid.addColumn(Machine::getStatus).setHeader("Status");
        machineGrid.addComponentColumn(machine -> createActions(machine)).setHeader("Aktionen");

        // Beispiel-Maschinen hinzufügen
        machines.add(new Machine("Gym Floor", "Leg Press", "Premium"));
        machines.add(new Machine("Cardio Section", "Treadmill", "Standard"));
        machineGrid.setItems(machines);

        add(headerLayout, machineGrid);
    }

    // Dialog zum Hinzufügen einer neuen Maschine öffnen
    private void openAddMachineDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Neue Maschine hinzufügen");

        // Bereichsauswahl
        ComboBox<String> areaField = new ComboBox<>("Bereichsname");
        areaField.setItems("Gym Floor", "Cardio Section", "Strength Training", "Free Weights");
        areaField.setPlaceholder("Bereich auswählen");

        // Maschinenname
        TextField machineNameField = new TextField("Maschinenname");
        machineNameField.setPlaceholder("Maschinennamen eingeben");

        // Maschinenstatus
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setPlaceholder("Status auswählen");

        Button saveButton = new Button("Maschine speichern", event -> {
            if (areaField.isEmpty() || machineNameField.isEmpty() || statusField.isEmpty()) {
                Notification.show("Alle Felder sind erforderlich!", 3000, Notification.Position.MIDDLE);
            } else {
                String areaName = areaField.getValue();
                String machineName = machineNameField.getValue();
                String status = statusField.getValue();

                machines.add(new Machine(areaName, machineName, status));
                machineGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(areaField, machineNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Aktionen für jede Maschine (Bearbeiten, Löschen) erstellen
    private HorizontalLayout createActions(Machine machine) {
        Button editButton = new Button("Bearbeiten", event -> openEditMachineDialog(machine));
        editButton.getStyle().set("background-color", "#007BFF"); // Blauer Hintergrund
        editButton.getStyle().set("color", "white"); // Weißer Text

        Button deleteButton = new Button("Löschen", event -> {
            machines.remove(machine); // Maschine aus der Liste entfernen
            machineGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
        });
        deleteButton.getStyle().set("background-color", "#DC3545"); // Roter Hintergrund
        deleteButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    // Dialog zum Bearbeiten einer Maschine öffnen
    private void openEditMachineDialog(Machine machine) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Maschine bearbeiten");

        // Bereichsauswahl
        ComboBox<String> areaField = new ComboBox<>("Bereichsname");
        areaField.setItems("Gym Floor", "Cardio Section", "Strength Training", "Free Weights");
        areaField.setValue(machine.getAreaName());

        // Maschinenname
        TextField machineNameField = new TextField("Maschinenname");
        machineNameField.setValue(machine.getMachineName());

        // Maschinenstatus
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setValue(machine.getStatus());

        Button saveButton = new Button("Änderungen speichern", event -> {
            machine.setAreaName(areaField.getValue());
            machine.setMachineName(machineNameField.getValue());
            machine.setStatus(statusField.getValue());
            machineGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(areaField, machineNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Maschinenmodell
    public static class Machine {
        private String areaName;
        private String machineName;
        private String status;

        public Machine(String areaName, String machineName, String status) {
            this.areaName = areaName;
            this.machineName = machineName;
            this.status = status;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
