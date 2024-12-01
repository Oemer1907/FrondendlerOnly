package com.bestfit.demo.layouts;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Anchor;

public class AdminLayout extends AppLayout {

    private boolean isDrawerOpen = false;

    public AdminLayout() {
        // CSS ve JS Dosyalarını Dahil Et
        UI.getCurrent().getPage().addStyleSheet("/css/bootstrap.min.css");
        UI.getCurrent().getPage().addStyleSheet("/css/style.css");
        UI.getCurrent().getPage().addJavaScript("/js/main.js");

        // Header
        Div header = createHeader();
        addToNavbar(header);

        // Drawer (Yan Menü)
        Div drawer = createDrawer();
        addToDrawer(drawer);
    }

    private Div createHeader() {
        Div header = new Div();
        header.addClassName("header");

        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer());

        Image logo = new Image("/images/bestfit.png", "Admin Logo");
        logo.addClassName("header-logo");

        Button logoutButton = new Button("Logout");
        logoutButton.addClassName("logout-button");
        logoutButton.addClickListener(event -> UI.getCurrent().navigate("login"));
        logoutButton.getStyle().set("background-color", "#FF0000"); // Kırmızı arka plan
        logoutButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, logoutButton);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        headerLayout.setWidthFull();

        header.add(headerLayout);
        return header;
    }

    private Div createDrawer() {
        Div drawer = new Div();
        drawer.addClassName("drawer");

        VerticalLayout menu = new VerticalLayout();
        menu.add(createDrawerMenuItem("Profile", "/admin/profile"));
        menu.add(createDrawerMenuItem("Members", "/admin/members"));
        menu.add(createDrawerMenuItem("Employees", "/admin/employees"));
        menu.add(createDrawerMenuItem("Managers", "/admin/managers"));
        menu.add(createDrawerMenuItem("Machines", "/admin/machines"));
        menu.add(createDrawerMenuItem("Fields", "/admin/fields"));
        menu.addClassName("drawer-menu");

        drawer.add(menu);
        return drawer;
    }

    private Anchor createDrawerMenuItem(String text, String route) {
        Anchor menuItem = new Anchor(route, text);
        menuItem.addClassName("drawer-menu-item");
        return menuItem;
    }

    private void toggleDrawer() {
        if (isDrawerOpen) {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '-250px'");
        } else {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '0'");
        }
        isDrawerOpen = !isDrawerOpen;
    }
}
