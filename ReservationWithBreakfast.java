 
 
 //this is the concrete decorator classes that extends the ReservationDecorator class
 //by using this classes we add details to the reservation object and update the price of the reservation.
 class ReservationWithBreakfast extends ReservationDecorator {
    private double breakfastCost;

    public ReservationWithBreakfast(Reservation decoratedReservation, double breakfastCost) {
        super(decoratedReservation);
        this.breakfastCost = breakfastCost;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + breakfastCost;
    }
}

 class ReservationWithLunch extends ReservationDecorator {
    private double lunchCost;

    public ReservationWithLunch(Reservation decoratedReservation, double lunchCost) {
        super(decoratedReservation);
        this.lunchCost = lunchCost;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + lunchCost;
    }
}
    
     class ReservationWithDinner extends ReservationDecorator {        
    private double dinnerCost;

    public ReservationWithDinner(Reservation decoratedReservation, double dinnerCost) {
        super(decoratedReservation);
        this.dinnerCost = dinnerCost;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + dinnerCost;
    }
}

 class ReservationWithChildren extends ReservationDecorator {        
    private double childCost;

    public ReservationWithChildren(Reservation decoratedReservation, double childCost,int numberOfChildren) {
        super(decoratedReservation);
        this.childCost = childCost*numberOfChildren;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + childCost;
    }
}

class ReservationWithAdults extends ReservationDecorator {        
    private double adultCost;

    public ReservationWithAdults(Reservation decoratedReservation, double adultCost,int numberOfAdults) {
        super(decoratedReservation);
        this.adultCost = adultCost*(numberOfAdults-2);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + adultCost;
    }
}


