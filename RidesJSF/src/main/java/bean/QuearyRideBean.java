package bean;

import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import businessLogic.BLFacade;
import domain.Ride;

@Named("quearyRideBean")
@SessionScoped
public class QuearyRideBean implements Serializable {
    private String origin;
    private String destination;
    private Date date;
    private String dateString;
    private List<Ride> results = new ArrayList<>();

    private BLFacade facade = FacadeBean.getBusinessLogic();
    private static final SimpleDateFormat HTML_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public QuearyRideBean() {
    }

    public String search() {
        // convert dateString to date
        if (dateString != null && !dateString.isEmpty()) {
            try {
                date = HTML_DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                date = null;
            }
        } else {
            date = null;
        }

        try {
            results = facade.getRides(origin, destination, date);
        } catch (Exception e) {
            results = new ArrayList<>();
        }
        return null; // stay on same page and show results
    }

    // getters and setters
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getDateString() { 
        if (date != null) return HTML_DATE_FORMAT.format(date);
        return dateString;
    }
    public void setDateString(String dateString) { 
        this.dateString = dateString; 
        if (dateString != null && !dateString.isEmpty()) {
            try { this.date = HTML_DATE_FORMAT.parse(dateString); } catch (ParseException e) { this.date = null; }
        } else this.date = null;
    }

    public List<Ride> getResults() { return results; }
}
