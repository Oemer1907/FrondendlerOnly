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

@PageTitle("Machines")
@Route(value = "admin/machines", layout = AdminLayout.class)
public class AdminMachinesView extends VerticalLayout {

    private List<Machine> machines = new ArrayList<>();
    private Grid<Machine> machineGrid;

    public AdminMachinesView() {
        setSpacing(true);
        setPadding(true);

        // Makina Ekleme Butonu
        Button addMachineButton = new Button("Add Machine", event -> openAddMachineDialog());
        addMachineButton.getStyle().set("background-color", "#28a745");
        addMachineButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(addMachineButton);
        headerLayout.setWidthFull();
        headerLayout.setJustifyContentMode(JustifyContentMode.END);

        // Grid: Makina Listesi
        machineGrid = new Grid<>(Machine.class, false);
        machineGrid.addColumn(Machine::getAreaName).setHeader("Area Name");
        machineGrid.addColumn(Machine::getMachineName).setHeader("Machine Name");
        machineGrid.addColumn(Machine::getStatus).setHeader("Status");
        machineGrid.addComponentColumn(machine -> createActions(machine)).setHeader("Actions");

        // Örnek Makinalar
        machines.add(new Machine("Gym Floor", "Leg Press", "Premium"));
        machines.add(new Machine("Cardio Section", "Treadmill", "Standard"));
        machineGrid.setItems(machines);

        add(headerLayout, machineGrid);
    }

    private void openAddMachineDialog() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Add New Machine");

        // Alan Seçimi
        ComboBox<String> areaField = new ComboBox<>("Area Name");
        areaField.setItems("Gym Floor", "Cardio Section", "Strength Training", "Free Weights");
        areaField.setPlaceholder("Select area");

        // Makina İsmi
        TextField machineNameField = new TextField("Machine Name");
        machineNameField.setPlaceholder("Enter machine name");

        // Makina Statüsü
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setPlaceholder("Select status");

        Button saveButton = new Button("Save Machine", event -> {
            if (areaField.isEmpty() || machineNameField.isEmpty() || statusField.isEmpty()) {
                Notification.show("All fields are required!", 3000, Notification.Position.MIDDLE);
            } else {
                String areaName = areaField.getValue();
                String machineName = machineNameField.getValue();
                String status = statusField.getValue();

                machines.add(new Machine(areaName, machineName, status));
                machineGrid.getDataProvider().refreshAll();
                dialog.close();
            }
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(areaField, machineNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private HorizontalLayout createActions(Machine machine) {
        Button editButton = new Button("Edit", event -> openEditMachineDialog(machine));
        editButton.getStyle().set("background-color", "#007BFF");
        editButton.getStyle().set("color", "white");

        Button deleteButton = new Button("Delete", event -> {
            machines.remove(machine);
            machineGrid.getDataProvider().refreshAll();
        });
        deleteButton.getStyle().set("background-color", "#DC3545");
        deleteButton.getStyle().set("color", "white");

        HorizontalLayout actionsLayout = new HorizontalLayout(editButton, deleteButton);
        actionsLayout.setSpacing(true);
        return actionsLayout;
    }

    private void openEditMachineDialog(Machine machine) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Edit Machine");

        // Alan Seçimi
        ComboBox<String> areaField = new ComboBox<>("Area Name");
        areaField.setItems("Gym Floor", "Cardio Section", "Strength Training", "Free Weights");
        areaField.setValue(machine.getAreaName());

        // Makina İsmi
        TextField machineNameField = new TextField("Machine Name");
        machineNameField.setValue(machine.getMachineName());

        // Makina Statüsü
        ComboBox<String> statusField = new ComboBox<>("Status");
        statusField.setItems("Premium", "Standard");
        statusField.setValue(machine.getStatus());

        Button saveButton = new Button("Save Changes", event -> {
            machine.setAreaName(areaField.getValue());
            machine.setMachineName(machineNameField.getValue());
            machine.setStatus(statusField.getValue());
            machineGrid.getDataProvider().refreshAll();
            dialog.close();
        });
        saveButton.getStyle().set("background-color", "#FFA500");
        saveButton.getStyle().set("color", "white");

        VerticalLayout dialogLayout = new VerticalLayout(areaField, machineNameField, statusField, saveButton);
        dialog.add(dialogLayout);
        dialog.open();
    }

    // Makina Modeli
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
