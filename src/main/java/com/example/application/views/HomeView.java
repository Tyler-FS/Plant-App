package com.example.application.views;

import com.vaadin.flow.component.html.Header;

import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.icon.Icon;

import com.vaadin.flow.component.sidenav.SideNav;

import com.vaadin.flow.component.sidenav.SideNavItem;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.avatar.Avatar;

import com.vaadin.flow.theme.lumo.LumoUtility;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    public HomeView() {



    
addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.CENTER);
Div div = new Div();
div.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.Padding.MEDIUM, LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.CENTER);
Header header = new Header();
header.addClassNames(LumoUtility.Background.BASE, LumoUtility.BoxSizing.BORDER, LumoUtility.Display.FLEX, LumoUtility.Gap.LARGE, LumoUtility.Height.XLARGE, LumoUtility.JustifyContent.BETWEEN, "px-m", LumoUtility.Width.FULL);
Div div2 = new Div();
div2.addClassNames(LumoUtility.Display.FLEX, LumoUtility.Gap.LARGE, LumoUtility.AlignItems.CENTER, LumoUtility.Overflow.HIDDEN);
Icon hamburgerIcon = new Icon("vaadin:menu");
hamburgerIcon.addClassNames(LumoUtility.IconSize.MEDIUM, LumoUtility.TextColor.PRIMARY);
div2.add(hamburgerIcon);

//<theme-editor-local-classname>
SideNav sidenav = new SideNav();
sidenav.addClassNames(LumoUtility.Overflow.AUTO);
SideNavItem home = new SideNavItem("Home");
Icon icon2 = new Icon("vaadin:home");
home.setPrefixComponent(icon2);
home.setPath("view-1");
home.addClassNames(LumoUtility.Display.INLINE_FLEX);
sidenav.addItem(home);
SideNavItem orders = new SideNavItem("Orders");
Icon icon4 = new Icon("vaadin:cart");
orders.setPrefixComponent(icon4);
orders.setPath("view-3");
orders.addClassNames(LumoUtility.Display.INLINE_FLEX);
sidenav.addItem(orders);
div2.add(sidenav);
header.add(div2);
Div div3 = new Div();
div3.addClassNames(LumoUtility.Display.FLEX, LumoUtility.Gap.SMALL, LumoUtility.AlignItems.CENTER);
Button newEvent = new Button("New event");
newEvent.setThemeName("primary");
Icon icon5 = new Icon("vaadin:plus");
newEvent.setPrefixComponent(icon5);
div3.add(newEvent);
Button button = new Button();
button.setThemeName("icon tertiary");
Icon icon6 = new Icon("vaadin:bell");
button.setPrefixComponent(icon6);
button.setAriaLabel("Notifications");
div3.add(button);
Avatar avatar = new Avatar("Emily Johnson");
avatar.setImage("https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-4.0.3&amp;ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&amp;auto=format&amp;fit=facearea&amp;facepad=2&amp;w=256&amp;h=256&amp;q=80");
div3.add(avatar);
header.add(div3);
div.add(header);
add(div);
}}
