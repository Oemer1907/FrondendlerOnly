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

@PageTitle("Fields")
@Route(value = "admin/fields", layout = AdminLayout.class)
public class AdminFieldsView extends VerticalLayout {

    private List<Field> fields = new ArrayList<>();
    private Grid<Field> fieldGrid;

    public AdminFieldsView() {
        setSpacing(true);
        setPadding(true);

        // Alan Ekleme Butonu
        Button addFieldButton = new Button("Add Field", event -> openAddFieldDialog());
        addFieldButton.getStyle().set("background-color", "#28a745");
        addFieldButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addFieldButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Grid: Alan Listesi
        fieldGrid = new Grid<>(Field.class, false);
        fieldGrid.addColumn(Field::getFieldName).setHeader("Field Name");
        fieldGrid.addColumn(Field::getStatus).setHeader("Status");
        fieldGrid.addComponentColumn(field -> createActions(field)).setHeader("Actions");

        // Örnek Alanlar
        fields.add(new Field("Gym Hall 1", "Premium"));
        fields.add(new Field("Cardio Zone", "Standard"));
        fieldGrid.setItems(fields);

        add(headerLayout, fieldGrid);
    }

    private void openAddFieldDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Field");

        // Alan İsmi
        TextField fieldNameField = new TextField("Field Name");
        fieldNameField.setPlaceholder("Enter field name");

        // Alan Statüsü
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setPlaceholder("Select status");

        Button saveButton = new Button("Save Field", event -> {
            if (fieldNameField.isEmpty() || statusField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                String fieldName = fieldNameField.getValue();
                String status = statusField.getValue();

                fields.add(new Field(fieldName, status));
                fieldGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(fieldNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Field field) {
        Button editButton = new Button("Edit", event -> openEditFieldDialog(field));
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", event -> {
            fields.remove(field);
            fieldGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    private void openEditFieldDialog(Field field) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Field");

        // Alan İsmi
        TextField fieldNameField = new TextField("Field Name");
        fieldNameField.setValue(field.getFieldName());

        // Alan Statüsü
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setValue(field.getStatus());

        Button saveButton = new Button("Save Changes", event -> {
            field.setFieldName(fieldNameField.getValue());
            field.setStatus(statusField.getValue());
            fieldGrid.getDataProvider().refreshAll();
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(fieldNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Alan Modeli
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
