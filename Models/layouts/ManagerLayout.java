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

public class ManagerLayout extends AppLayout {

    private boolean isDrawerOpen = false; // Status des seitlichen Menüs (geöffnet oder geschlossen)

    public ManagerLayout() {
        // CSS- und JavaScript-Dateien einbinden
        UI.getCurrent().getPage().addStyleSheet("/css/bootstrap.min.css");
        UI.getCurrent().getPage().addStyleSheet("/css/style.css");
        UI.getCurrent().getPage().addJavaScript("/js/main.js");

        // Header erstellen und hinzufügen
        Div header = createHeader();
        addToNavbar(header);

        // Seitliches Menü (Drawer) erstellen und hinzufügen
        Div drawer = createDrawer();
        addToDrawer(drawer);
    }

    private Div createHeader() {
        Div header = new Div();
        header.addClassName("header");

        // Menü-Schaltfläche (Hamburger-Menü) hinzufügen
        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer()); // Menü ein-/ausblenden

        // Logo hinzufügen
        Image logo = new Image("/images/bestfit.png", "Manager Logo");
        logo.addClassName("header-logo");

        // Profil-Schaltfläche hinzufügen
        Button profileButton = new Button("Profile");
        profileButton.addClassName("profile-button");
        profileButton.addClickListener(event -> UI.getCurrent().navigate("manager/profile"));
        profileButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        profileButton.getStyle().set("color", "white");

        // Header-Layout erstellen
        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, profileButton);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Inhalte zentrieren
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Inhalte verteilen
        headerLayout.setWidthFull(); // Breite auf 100% setzen

        header.add(headerLayout);
        return header;
    }

    private Div createDrawer() {
        // Hauptcontainer für das seitliche Menü (Drawer)
        Div drawer = new Div();
        drawer.addClassName("drawer");

        // Menüelemente hinzufügen
        VerticalLayout menu = new VerticalLayout();
        menu.add(createDrawerMenuItem("Profile", "/manager/profile"));
        menu.add(createDrawerMenuItem("Members", "/manager/members"));
        menu.add(createDrawerMenuItem("Employees", "/manager/employees"));
        menu.addClassName("drawer-menu");

        drawer.add(menu);
        return drawer;
    }

    private Anchor createDrawerMenuItem(String text, String route) {
        // Menüpunkt im Drawer erstellen
        Anchor menuItem = new Anchor(route, text);
        menuItem.addClassName("drawer-menu-item");
        return menuItem;
    }

    private void toggleDrawer() {
        // Drawer ein- oder ausblenden
        if (isDrawerOpen) {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '-250px'");
        } else {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '0'");
        }
        isDrawerOpen = !isDrawerOpen;
    }
}
