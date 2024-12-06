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

@PageTitle("Mitarbeiterliste")
@Route(value = "admin/employees", layout = AdminLayout.class)
public class AdminEmployeeListView extends VerticalLayout {

    private List<Employee> employees = new ArrayList<>(); // Liste der Mitarbeiter
    private Grid<Employee> employeeGrid; // Tabelle zur Anzeige der Mitarbeiter

    public AdminEmployeeListView() {
        setSpacing(true);
        setPadding(true);

        // Button zum Hinzufügen eines Mitarbeiters
        Button addEmployeeButton = new Button("Mitarbeiter hinzufügen", event -> openAddEmployeeDialog());
        addEmployeeButton.getStyle().set("background-color", "#28a745"); // Grüner Hintergrund
        addEmployeeButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout headerLayout = new HorizontalLayout(addEmployeeButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END); // Button rechts ausrichten

        // Tabelle erstellen
        employeeGrid = new Grid<>(Employee.class, false);
        employeeGrid.addColumn(Employee::getFirstName).setHeader("Vorname");
        employeeGrid.addColumn(Employee::getLastName).setHeader("Nachname");
        employeeGrid.addColumn(Employee::getEmail).setHeader("E-Mail");
        employeeGrid.addComponentColumn(employee -> createActions(employee)).setHeader("Aktionen");

        // Beispiel-Mitarbeiter hinzufügen
        employees.add(new Employee("Mike", "Ross", "mike.ross@example.com"));
        employees.add(new Employee("Rachel", "Zane", "rachel.zane@example.com"));
        employeeGrid.setItems(employees);

        add(headerLayout, employeeGrid);
    }

    // Dialog zum Hinzufügen eines neuen Mitarbeiters öffnen
    private void openAddEmployeeDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Neuen Mitarbeiter hinzufügen");

        TextField firstName = new TextField("Vorname");
        firstName.setPlaceholder("Vorname eingeben");

        TextField lastName = new TextField("Nachname");
        lastName.setPlaceholder("Nachname eingeben");

        EmailField email = new EmailField("E-Mail");
        email.setPlaceholder("E-Mail eingeben");

        Button saveButton = new Button("Mitarbeiter speichern", event -> {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                Notification.show("Alle Felder sind erforderlich!", 3000, Notification.Position.MIDDLE);
            } else {
                employees.add(new Employee(firstName.getValue(), lastName.getValue(), email.getValue()));
                employeeGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        dialog.add(new VerticalLayout(firstName, lastName, email, saveButton));
        dialog.open();
    }

    // Aktionen für jeden Mitarbeiter erstellen (Bearbeiten, Löschen)
    private HorizontalLayout createActions(Employee employee) {
        Button editButton = new Button("Bearbeiten", event -> openEditEmployeeDialog(employee));
        editButton.getStyle().set("background-color", "#007BFF"); // Blauer Hintergrund
        editButton.getStyle().set("color", "white"); // Weißer Text

        Button deleteButton = new Button("Löschen", event -> {
            employees.remove(employee); // Mitarbeiter aus der Liste entfernen
            employeeGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
        });
        deleteButton.getStyle().set("background-color", "#DC3545"); // Roter Hintergrund
        deleteButton.getStyle().set("color", "white"); // Weißer Text

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    // Dialog zum Bearbeiten eines Mitarbeiters öffnen
    private void openEditEmployeeDialog(Employee employee) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Mitarbeiter bearbeiten");

        TextField firstName = new TextField("Vorname");
        firstName.setValue(employee.getFirstName());

        TextField lastName = new TextField("Nachname");
        lastName.setValue(employee.getLastName());

        EmailField email = new EmailField("E-Mail");
        email.setValue(employee.getEmail());

        Button saveButton = new Button("Änderungen speichern", event -> {
            employee.setFirstName(firstName.getValue());
            employee.setLastName(lastName.getValue());
            employee.setEmail(email.getValue());
            employeeGrid.getDataProvider().refreshAll(); // Tabelle aktualisieren
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        saveButton.getStyle().set("color", "white"); // Weißer Text

        dialog.add(new VerticalLayout(firstName, lastName, email, saveButton));
        dialog.open();
    }

    // Mitarbeiter-Klasse
    public static class Employee {
        private String firstName;
        private String lastName;
        private String email;

        public Employee(String firstName, String lastName, String email) {
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
