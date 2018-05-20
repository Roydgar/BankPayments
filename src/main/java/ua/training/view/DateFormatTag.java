package ua.training.view;

import org.apache.logging.log4j.LogManager;
import ua.training.util.LoggerMessageUtil;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static java.util.Objects.isNull;

public class DateFormatTag extends SimpleTagSupport {
    private String language;
    private LocalDateTime date;

    public void setLocalDateTime(LocalDateTime date) {
        this.date = date;
    }

    public void setLanguage(String langCount) {
        this.language = langCount;
    }

    @Override
    public void doTag() {
        Locale locale = new Locale(language);
        DateTimeFormatter df;

        df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).withLocale(locale);

        if (isNull(date)) {
            date = LocalDateTime.now();
        }

        try {
            getJspContext()
                    .getOut()
                    .write(df.format(date));
        } catch (IOException e) {
            LogManager.getRootLogger().error(LoggerMessageUtil.JstlTagException(), e);
            throw new RuntimeException(e);
        }

    }
}