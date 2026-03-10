package ensa.tps._4_jsf_datatable.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.text.NumberFormat;
import java.util.Locale;

@FacesConverter("currencyConverter")
public class CurrencyConverter implements Converter<Double> {
    private static final double MAD_TO_EUR = 0.0922;
    private static final double MAD_TO_USD = 0.1071;

    @Override
    public Double getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) return null;
        Locale locale = facesContext.getViewRoot().getLocale();
        double value = Double.parseDouble(s.replaceAll("[^0-9.,]", "").replace(",", "."));
        return switch (locale.getLanguage()) {
            case "fr" -> value / MAD_TO_EUR;
            case "en" -> value / MAD_TO_USD;
            default -> value;
        };
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Double aDouble) {
        if (aDouble == null) return "";
        Locale locale = facesContext.getViewRoot().getLocale();
        double convertedValue = switch (locale.getLanguage()) {
            case "fr" -> aDouble * MAD_TO_EUR;
            case "en" -> aDouble * MAD_TO_USD;
            default -> aDouble;
        };
        String currency = switch (locale.getLanguage()) {
            case "fr" -> "EUR";
            case "en" -> "USD";
            default -> "MAD";
        };
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(java.util.Currency.getInstance(currency));
        return formatter.format(convertedValue);
    }
}
