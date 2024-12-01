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

public class EmployeeLayout extends AppLayout {

    private boolean isDrawerOpen = false;

    public EmployeeLayout() {
        UI.getCurrent().getPage().addStyleSheet("/css/bootstrap.min.css");
        UI.getCurrent().getPage().addStyleSheet("/css/style.css");
        UI.getCurrent().getPage().addJavaScript("/js/main.js");

        Div header = createHeader();
        addToNavbar(header);

        Div drawer = createDrawer();
        addToDrawer(drawer);
    }

    private Div createHeader() {
        Div header = new Div();
        header.addClassName("header");

        Button hamburgerMenuButton = new Button(new Icon(VaadinIcon.MENU));
        hamburgerMenuButton.addClassName("hamburger-icon");
        hamburgerMenuButton.addClickListener(event -> toggleDrawer());

        Image logo = new Image("/images/bestfit.png", "Employee Logo");
        logo.addClassName("header-logo");

        Button profileButton = new Button("Profile");
        profileButton.addClassName("profile-button");
        profileButton.addClickListener(event -> UI.getCurrent().navigate("employee/profile"));
        profileButton.getStyle().set("background-color", "#00FF00"); // Yeşil arka plan
        profileButton.getStyle().set("color", "white");

        HorizontalLayout headerLayout = new HorizontalLayout(hamburgerMenuButton, logo, profileButton);
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
        menu.add(createDrawerMenuItem("Profile", "/employee/profile"));
        menu.add(createDrawerMenuItem("Members", "/employee/members"));
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
