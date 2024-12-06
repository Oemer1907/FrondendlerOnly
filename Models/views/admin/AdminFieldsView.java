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

@PageTitle("Felder")
@Route(value = "admin/fields", layout = AdminLayout.class)
public class AdminFieldsView extends VerticalLayout {

    private List<Field> fields = new ArrayList<>(); // Liste der Felder
    private Grid<Field> fieldGrid; // Tabelle zur Anzeige der Felder

    public AdminFieldsView() {
        setSpacing(true);
        setPadding(true);

        // Button zum Hinzufügen eines neuen Feldes
        Button addFieldButton = new Button("Feld hinzufügen", event -> openAddFieldDialog());
        addFieldButton.getStyle().set("background-color", "#28a745"); // Grüner Hintergrund
        addFieldButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout headerLayout = new HorizontalLayout(addFieldButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END); // Button rechts ausrichten

        // Tabelle für die Felder erstellen
        fieldGrid = new Grid<>(Field.class, false);
        fieldGrid.addColumn(Field::getFieldName).setHeader("Feldname");
        fieldGrid.addColumn(Field::getStatus).setHeader("Status");
        fieldGrid.addComponentColumn(field -> createActions(field)).setHeader("Aktionen");

        // Beispiel-Felder hinzufügen
        fields.add(new Field("Gym Hall 1", "Premium"));
        fields.add(new Field("Cardio Zone", "Standard"));
        fieldGrid.setItems(fields);

        add(headerLayout, fieldGrid);
    }

    // Dialog zum Hinzufügen eines neuen Feldes öffnen
    private void openAddFieldDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Neues Feld hinzufügen");

        // Feldname
        TextField fieldNameField = new TextField("Feldname");
        fieldNameField.setPlaceholder("Feldnamen eingeben");

        // Feldstatus
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setPlaceholder("Status auswählen");

        Button saveButton = new Button("Feld speichern", event -> {
            if (fieldNameField.isEmpty() || statusField.isEmpty()) {
                Notification.show("Alle Felder sind erforderlich!", 3000, Notification.Position.MIDDLE);
            } else {
                String fieldName = fieldNameField.getValue();
                String status = statusField.getValue();

                fields.add(new Field(fieldName, status));
                fieldGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(fieldNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Aktionen für jedes Feld (Bearbeiten, Löschen) erstellen
    private HorizontalLayout createActions(Field field) {
        Button editButton = new Button("Bearbeiten", event -> openEditFieldDialog(field));
        editButton.getStyle().set("background-color", "#007BFF"); // Blauer Hintergrund
        editButton.getStyle().set("color", "white"); // Weißer Text

        Button deleteButton = new Button("Löschen", event -> {
            fields.remove(field); // Feld aus der Liste entfernen
            fieldGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
        });
        deleteButton.getStyle().set("background-color", "#DC3545"); // Roter Hintergrund
        deleteButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    // Dialog zum Bearbeiten eines Feldes öffnen
    private void openEditFieldDialog(Field field) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Feld bearbeiten");

        // Feldname
        TextField fieldNameField = new TextField("Feldname");
        fieldNameField.setValue(field.getFieldName());

        // Feldstatus
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setValue(field.getStatus());

        Button saveButton = new Button("Änderungen speichern", event -> {
            field.setFieldName(fieldNameField.getValue());
            field.setStatus(statusField.getValue());
            fieldGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        VerticalLayout dialogLayout = new VerticalLayout(fieldNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Feldmodell
    public static class Field {
        private String fieldName;
        private String status;

        public Field(String fieldName, String status) {
            this.fieldName = fieldName;
            this.status = status;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
