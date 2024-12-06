package com.bestfit.demo.views.customer;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.bestfit.demo.layouts.CustomerLayout;

@PageTitle("Startseite")
@Route(value = "customer/home", layout = CustomerLayout.class)
public class CustomerHomeView extends Div {

    public CustomerHomeView() {
        // Hauptcontainer
        setClassName("customer-home-view");
        setWidthFull();
        setHeight("100vh"); // Vollbildhöhe
        getStyle().set("display", "flex");
        getStyle().set("align-items", "center");
        getStyle().set("justify-content", "center");
        getStyle().set("background-image", "url('/images/hero-three-img-3.jpg')");
        getStyle().set("background-size", "cover");
        getStyle().set("background-position", "center");

        // Motivationssatz
        H1 motivationText = new H1("Dein einziges Limit bist du selbst. Fordere dich heraus, Großes zu erreichen!");
        motivationText.getStyle().set("color", "white");
        motivationText.getStyle().set("text-shadow", "2px 2px 4px rgba(0, 0, 0, 0.8)");
        motivationText.getStyle().set("font-size", "3rem");
        motivationText.getStyle().set("text-align", "center");

        add(motivationText);
    }
}
