package ensa.tps._4_jsf_datatable.bean;

import jakarta.faces.context.FacesContext;
import java.util.Locale;

public class LocaleBean {
    private Locale locale = new Locale("en");

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
