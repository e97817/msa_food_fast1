package bo.gob.food_fast.common.config;


import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Locale;

@ApplicationScoped
public class LocaleConfig {
    private static final Logger LOGGER = Logger.getLogger(LocaleConfig.class.getName());

    private final String localeLanguage;
    private final String localeCountry;

    // Constructor con inyección de propiedades
    public LocaleConfig(
            @ConfigProperty(name = "app.locale.language", defaultValue = "en") String localeLanguage,
            @ConfigProperty(name = "app.locale.country", defaultValue = "US") String localeCountry
    ) {
        this.localeLanguage = localeLanguage;
        this.localeCountry = localeCountry;
    }

    public void onStart(@Observes StartupEvent event) {
        configureLocale();
        logLocaleConfiguration();
        setSystemProperties();
    }

    private void configureLocale() {
        Locale configuredLocale;

        try {
            // Creación de locale usando lenguaje y país
            configuredLocale = new Locale(localeLanguage, localeCountry);

            // Establecer locale por defecto
            Locale.setDefault(configuredLocale);
        } catch (Exception e) {
            LOGGER.info("Error al configurar locale: " + e.getMessage());
            // Fallback a US locale en caso de error
            Locale.setDefault(Locale.US);
        }
    }

    private void setSystemProperties() {
        Locale currentLocale = Locale.getDefault();

        // Establecer propiedades del sistema para compatibilidad
        System.setProperty("user.language", currentLocale.getLanguage());
        System.setProperty("user.country", currentLocale.getCountry());

        // Propiedades adicionales de encoding
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("sun.jnu.encoding", "UTF-8");
    }

    private void logLocaleConfiguration() {
        Locale currentLocale = Locale.getDefault();

        LOGGER.info("Configuración de Locale inicializada:");
        LOGGER.info("Lenguaje: " + currentLocale.getLanguage());
        LOGGER.info("País: " + currentLocale.getCountry());
    }
}