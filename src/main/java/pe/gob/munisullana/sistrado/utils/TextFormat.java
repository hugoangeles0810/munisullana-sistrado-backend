package pe.gob.munisullana.sistrado.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TextFormat {


    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public String formatProcedureDate(Date date) {
        if (date == null) return "";
        return formatter.format(date);
    }

}
