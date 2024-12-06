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

public class MainLayout extends AppLayout {

    private boolean isDrawerOpen = false; // Status des Menüs (geöffnet oder geschlossen)

    public MainLayout() {
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
        // Hauptcontainer für den Header
        Div header = new Div();
        header.addClassName("header");

        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer()); // Öffnen/Schließen des Menüs

        // Logo für den Header
        Image logo = new Image("/images/bestfit.png", "Gym On Logo");
        logo.addClassName("header-logo");

        // Login-Button erstellen
        Button loginButton = new Button("Login");
        loginButton.addClassName("login-button");
        loginButton.addClickListener(event -> UI.getCurrent().navigate("login"));
        loginButton.getStyle().set("background-color", "#FFA500"); // Orangefarbener Hintergrund
        loginButton.getStyle().set("color", "white");

        // Header-Layout erstellen
        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, loginButton);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Zentrierte Ausrichtung der Inhalte
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); // Verteilung der Inhalte
        headerLayout.setWidthFull(); // Breite auf 100% setzen
        headerLayout.addClassName("header-layout"); // Zusätzliche CSS-Klasse

        header.add(headerLayout);
        return header;
    }

    private Div createDrawer() {
        // Hauptcontainer für das seitliche Menü (Drawer)
        Div drawer = new Div();
        drawer.addClassName("drawer");

        // Logo im Drawer
        Image logo = new Image("/images/bestfit.png", "Gym On Logo");
        logo.addClassName("drawer-logo");

        // Menüelemente erstellen
        VerticalLayout menu = new VerticalLayout();
        menu.add(createDrawerMenuItem("Startseite", "/"));
        menu.add(createDrawerMenuItem("TrainingsPlan", "/plans"));
        menu.add(createDrawerMenuItem("Mitgliedschaft", "/services"));
        menu.add(createDrawerMenuItem("Impressum", "/impressum"));
        menu.add(createDrawerMenuItem("Kontakt", "/contact"));
        menu.addClassName("drawer-menu");

        drawer.add(logo, menu);
        return drawer;
    }

    private Anchor createDrawerMenuItem(String text, String route) {
        // Menüelement im Drawer erstellen
        Anchor menuItem = new Anchor(route, text); 
        menuItem.addClassName("drawer-menu-item");
        return menuItem;
    }

    private void toggleDrawer() {
        // Menü (Drawer) ein- oder ausblenden
        if (isDrawerOpen) {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '-250px'");
        } else {
            UI.getCurrent().getPage().executeJs("document.querySelector('.drawer').style.left = '0'");
        }
        isDrawerOpen = !isDrawerOpen;
    }
}
