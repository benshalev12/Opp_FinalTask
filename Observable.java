// Purpose: Interface for the Observable class
public interface Observable {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(Reservation res,String message);
}