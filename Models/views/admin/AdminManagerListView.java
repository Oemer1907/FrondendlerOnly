package com.bestfit.demo.views.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.AdminLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Manager List")
@Route(value = "admin/managers", layout = AdminLayout.class)
public class AdminManagerListView extends VerticalLayout {

    private List<Manager> managers = new ArrayList<>();
    private Grid<Manager> managerGrid;

    public AdminManagerListView() {
        setSpacing(true);
        setPadding(true);

        // Add Manager Button
        Button addManagerButton = new Button("Add Manager", event -> openAddManagerDialog());
        addManagerButton.getStyle().set("background-color", "#28a745");
        addManagerButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addManagerButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Manager Grid
        managerGrid = new Grid<>(Manager.class, false);
        managerGrid.addColumn(Manager::getFirstName).setHeader("First Name");
        managerGrid.addColumn(Manager::getLastName).setHeader("Last Name");
        managerGrid.addColumn(Manager::getEmail).setHeader("Email");
        managerGrid.addComponentColumn(manager -> createActions(manager)).setHeader("Actions");

        // Sample Managers
        managers.add(new Manager("John", "Doe", "john.doe@example.com"));
        managers.add(new Manager("Jane", "Smith", "jane.smith@example.com"));
        managerGrid.setItems(managers);

        add(headerLayout, managerGrid);
    }

    private void openAddManagerDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Manager");

        TextField firstNameField = new TextField("First Name");
        firstNameField.setPlaceholder("Enter first name");

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setPlaceholder("Enter last name");

        EmailField emailField = new EmailField("Email");
        emailField.setPlaceholder("Enter email");

        Button saveButton = new Button("Save Manager", event -> {
            if (firstNameField.isEmpty() || lastNameField.isEmpty() || emailField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                managers.add(new Manager(firstNameField.getValue(), lastNameField.getValue(), emailField.getValue()));
                managerGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Manager manager) {
        Button editButton = new Button("Edit", event -> openEditManagerDialog(manager));
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", event -> {
            managers.remove(manager);
            managerGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    private void openEditManagerDialog(Manager manager) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Manager");

        TextField firstNameField = new TextField("First Name");
        firstNameField.setValue(manager.getFirstName());

        TextField lastNameField = new TextField("Last Name");
        lastNameField.setValue(manager.getLastName());

        EmailField emailField = new EmailField("Email");
        emailField.setValue(manager.getEmail());

        Button saveButton = new Button("Save Changes", event -> {
            manager.setFirstName(firstNameField.getValue());
            manager.setLastName(lastNameField.getValue());
            manager.setEmail(emailField.getValue());
            managerGrid.getDataProvider().refreshAll();
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(firstNameField, lastNameField, emailField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    public static class Manager {
        private String firstName;
        private String lastName;
        private String email;

        public Manager(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
