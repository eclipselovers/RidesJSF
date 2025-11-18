package bean;
import java.util.*;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import businessLogic.BLFacade;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;


@Named("createRideBean")
@SessionScoped
public class CreateRideBean implements Serializable {
private String origin;
private String destination;
private Date date;
private int seats;
private float price;
private String mail;
private List<String> cities;


private BLFacade facade = FacadeBean.getBusinessLogic();


public CreateRideBean(){
cities = facade.getDepartCities();
}


public String create() throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	facade.createRide(origin, destination, date, seats, price, mail);
return null;
}


public String getOrigin(){return origin;}
public void setOrigin(String o){origin=o;}
public String getDestination(){return destination;}
public void setDestination(String d){destination=d;}
public Date getDate(){return date;}
public void setDate(Date d){date=d;}
public int getSeats(){return seats;}
public void setSeats(int s){seats=s;}
public List<String> getCities(){return cities;}
public float getPrice(){return price;}
public void setPrice(float p){price=p;}
public String getMail(){return mail;}
public void setMail(String m){mail=m;}
}