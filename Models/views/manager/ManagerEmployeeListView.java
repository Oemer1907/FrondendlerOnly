package com.bestfit.demo.views.manager;

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
import com.bestfit.demo.layouts.ManagerLayout;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Employee List")
@Route(value = "manager/employees", layout = ManagerLayout.class)
public class ManagerEmployeeListView extends VerticalLayout {

    private List<Employee> employees = new ArrayList<>();
    private Grid<Employee> employeeGrid;

    public ManagerEmployeeListView() {
        setSpacing(true);
        setPadding(true);

        // Add Employee Button
        Button addEmployeeButton = new Button("Add Employee", event -> openAddEmployeeDialog());
        addEmployeeButton.getStyle().set("background-color", "#28a745");
        addEmployeeButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addEmployeeButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        employeeGrid = new Grid<>(Employee.class, false);
        employeeGrid.addColumn(Employee::getFirstName).setHeader("First Name");
        employeeGrid.addColumn(Employee::getLastName).setHeader("Last Name");
        employeeGrid.addColumn(Employee::getEmail).setHeader("Email");
        employeeGrid.addComponentColumn(employee -> createActions(employee)).setHeader("Actions");

        // Sample Employees
        employees.add(new Employee("Mike", "Ross", "mike.ross@example.com"));
        employees.add(new Employee("Rachel", "Zane", "rachel.zane@example.com"));
        employeeGrid.setItems(employees);

        add(headerLayout, employeeGrid);
    }

    private void openAddEmployeeDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Employee");

        TextField firstName = new TextField("First Name");
        firstName.setPlaceholder("Enter first name");

        TextField lastName = new TextField("Last Name");
        lastName.setPlaceholder("Enter last name");

        EmailField email = new EmailField("Email");
        email.setPlaceholder("Enter email");

        Button saveButton = new Button("Save Employee", event -> {
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                employees.add(new Employee(firstName.getValue(), lastName.getValue(), email.getValue()));
                employeeGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        dialog.add(new VerticalLayout(firstName, lastName, email, saveButton));
        dialog.open();
    }

    private HorizontalLayout createActions(Employee employee) {
        Button editButton = new Button("Edit", event -> openEditEmployeeDialog(employee));
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", event -> {
            employees.remove(employee);
            employeeGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    private void openEditEmployeeDialog(Employee employee) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Employee");

        TextField firstName = new TextField("First Name");
        firstName.setValue(employee.getFirstName());

        TextField lastName = new TextField("Last Name");
        lastName.setValue(employee.getLastName());

        EmailField email = new EmailField("Email");
        email.setValue(employee.getEmail());

        Button saveButton = new Button("Save Changes", event -> {
            employee.setFirstName(firstName.getValue());
            employee.setLastName(lastName.getValue());
            employee.setEmail(email.getValue());
            employeeGrid.getDataProvider().refreshAll();
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        dialog.add(new VerticalLayout(firstName, lastName, email, saveButton));
        dialog.open();
    }

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
