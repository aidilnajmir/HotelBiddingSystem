/**
 * This class extends the abstract HotelRoom class to represent a Standard hotel room. 
 * It defines Standard room behavior, such as how to handle bids with a predefined minimum bid price.
 */
public class Standard extends HotelRoom {
    
    /** 
     * Creates a Standard room object with a specified number of available rooms. 
     * Standard rooms have a minimum bid price of $80.
     * @param availableRooms Number of available rooms.
     */
    public Standard(int availableRooms) {
        super(availableRooms, 80.0); // Standard room minimum bid
    }

    /**
     * Handles bids for Standard rooms. If a bid exceeds the minimum bid price and there are available rooms, 
     * it is accepted and the number of available rooms is reduced. 
     * If there are no available rooms or the bid amount is insufficient, the bid is forwarded to the next room. 
     * @param bidPrice The customer's bid price.
     * @return A message indicating the bid's acceptance or rejection.
     */
    @Override
    public String handleRequest(double bidPrice) {
        if (bidPrice >= minBidPrice && availableRooms > 0) {
            availableRooms--;
            return "Standard room booked for $" + bidPrice;
        } else if (successor != null) {
            return successor.handleRequest(bidPrice);
        }
        return "Bid rejected. No available rooms/insufficient bid amount.";
    }
}