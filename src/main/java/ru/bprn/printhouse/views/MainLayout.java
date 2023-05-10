package ru.bprn.printhouse.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;
import ru.bprn.printhouse.components.appnav.AppNav;
import ru.bprn.printhouse.components.appnav.AppNavItem;
import ru.bprn.printhouse.views.about.AboutView;
import ru.bprn.printhouse.views.dictionary.PrintMachineDictionary;
import ru.bprn.printhouse.views.dictionary.QuantityColorsDictionary;
import ru.bprn.printhouse.views.dictionary.TypeOfMaterialDictionary;
import ru.bprn.printhouse.views.dictionary.TypeOfPrinterDictionary;
import ru.bprn.printhouse.views.equipment.printmashine.DigitalPressView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Микротипография");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        var dick = new AppNavItem("Словари");
        dick.addItem(new AppNavItem("Количество цветов", QuantityColorsDictionary.class, LineAwesomeIcon.EDIT_SOLID.create()));
        dick.addItem(new AppNavItem("Печатные устройства", PrintMachineDictionary.class, LineAwesomeIcon.EDIT_SOLID.create()));
        dick.addItem(new AppNavItem("Тип принтера", TypeOfPrinterDictionary.class, LineAwesomeIcon.EDIT_SOLID.create()));
        dick.addItem(new AppNavItem("Тип материала", TypeOfMaterialDictionary.class, LineAwesomeIcon.EDIT_SOLID.create()));
        nav.addItem(dick);

        dick =new AppNavItem("Оборудование");
        dick.addItem(new AppNavItem("ЦПМ", DigitalPressView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        nav.addItem(dick);
        nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}

