

// This class is the abstract class for the decorator pattern.
// It extends the Reservation class and has a Reservation object as a field. 
//It has the same methods as the Reservation class and it overrides them to call the methods of the Reservation object. 
//It also has an update method that calls the update method of the Reservation object. 

public abstract class ReservationDecorator extends Reservation {
    protected Reservation decoratedReservation;

    //constructor
    public ReservationDecorator(Reservation decoratedReservation) {
        super(decoratedReservation.getHotel(), decoratedReservation.getRoomType(), decoratedReservation.getStartDay(), decoratedReservation.getEndDay(), decoratedReservation.getPrice(), decoratedReservation.hasBreakfast(), decoratedReservation.hasLunch(), decoratedReservation.hasDinner(), decoratedReservation.getNumberOfAdults(), decoratedReservation.getNumberOfChildren(), decoratedReservation.getRoom());
        this.decoratedReservation = decoratedReservation;
    }
    //getters
    @Override
    public Hotel getHotel() {
        return decoratedReservation.getHotel();
    }

    @Override
    public String getRoomType() {
        return decoratedReservation.getRoomType();
    }

    @Override
    public int getStartDay() {
        return decoratedReservation.getStartDay();
    }

    @Override
    public int getEndDay() {
        return decoratedReservation.getEndDay();
    }

    @Override
    public double getPrice() {
        return decoratedReservation.getPrice();
    }

    @Override
    public boolean hasBreakfast() {
        return decoratedReservation.hasBreakfast();
    }

    @Override
    public boolean hasLunch() {
        return decoratedReservation.hasLunch();
    }

    @Override
    public boolean hasDinner() {
        return decoratedReservation.hasDinner();
    }

    @Override
    public int getNumberOfAdults() {
        return decoratedReservation.getNumberOfAdults();
    }

    @Override
    public int getNumberOfChildren() {
        return decoratedReservation.getNumberOfChildren();
    }

    @Override
    public Room getRoom() {
        return decoratedReservation.getRoom();
    }

    @Override
    public void update(String message) {
        decoratedReservation.update(message);
    }
}