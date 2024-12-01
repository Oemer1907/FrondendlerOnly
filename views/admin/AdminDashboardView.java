package com.bestfit.demo.views.admin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.AdminLayout;

@Route(value = "admin/dashboard", layout = AdminLayout.class)
public class AdminDashboardView extends Div {

    public AdminDashboardView() {
        setText("Welcome to the Admin Dashboard!");
        addClassName("admin-dashboard-view");
    }
}
