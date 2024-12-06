package com.bestfit.demo.views.admin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.AdminLayout;

@Route(value = "admin/settings", layout = AdminLayout.class)
public class AdminSettingsView extends Div {

    public AdminSettingsView() {
        setText("Welcome to the Admin Settings Page!");
        addClassName("admin-settings-view");
    }
}
