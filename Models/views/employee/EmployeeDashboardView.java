package com.bestfit.demo.views.employee;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.EmployeeLayout;

@Route(value = "employee/dashboard", layout = EmployeeLayout.class)
public class EmployeeDashboardView extends Div {

    public EmployeeDashboardView() {
        setText("Willkommen auf der Aufgaben-Seite für Mitarbeiter!");
        addClassName("employee-dashboard-view");
    }
}
