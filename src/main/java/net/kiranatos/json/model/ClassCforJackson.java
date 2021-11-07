package net.kiranatos.json.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class ClassCforJackson {
    private Date today;
    private LocalDate tomorrow;

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public LocalDate getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(LocalDate tomorrow) {
        this.tomorrow = tomorrow;
    }

    @Override
    public String toString() {
        return "ClassCforJackson{\n" + 
                "   today=" + today + 
                ",\n   tomorrow=" + tomorrow + '}';
    }
}
