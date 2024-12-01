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

    private boolean isDrawerOpen = false; 

    public MainLayout() {
       
        UI.getCurrent().getPage().addStyleSheet("/css/bootstrap.min.css");
        UI.getCurrent().getPage().addStyleSheet("/css/style.css");
        UI.getCurrent().getPage().addJavaScript("/js/main.js");

        Div header = createHeader();
        addToNavbar(header);

        Div drawer = createDrawer();
        addToDrawer(drawer);
    }

    private Div createHeader() {
        // Header ana kapsayıcı
        Div header = new Div();
        header.addClassName("header");

        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer()); 

    
        Image logo = new Image("/images/bestfit.png", "Gym On Logo");
        logo.addClassName("header-logo");

        Button loginButton = new Button("Login");
        loginButton.addClassName("login-button");
        loginButton.addClickListener(event -> UI.getCurrent().navigate("login"));
        loginButton.getStyle().set("background-color", "#FFA500"); 
        loginButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, loginButton);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        headerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN); 
        headerLayout.setWidthFull();
        headerLayout.addClassName("header-layout"); // Ek CSS için sınıf

        header.add(headerLayout);
        return header;
    }

    private Div createDrawer() {
        Div drawer = new Div();
        drawer.addClassName("drawer");

        Image logo = new Image("/images/bestfit.png", "Gym On Logo");
        logo.addClassName("drawer-logo");

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
