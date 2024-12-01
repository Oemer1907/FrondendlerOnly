package com.bestfit.demo.views.employee;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.EmployeeLayout;

@Route(value = "employee/tasks", layout = EmployeeLayout.class)
public class EmployeeTasksView extends Div {

    public EmployeeTasksView() {
        setText("Willkommen auf der Aufgaben-Seite für Mitarbeiter!");
        addClassName("employee-tasks-view");
    }
}
