/**
 * This class extends the abstract HotelRoom class to represent a Suite hotel room. 
 * It defines Suite room behavior, such as how to handle bids with a predefined minimum bid price.
 */
public class Suite extends HotelRoom {
    
    /** 
     * Creates a Suite room object with a specified number of available rooms. 
     * Suite rooms have a minimum bid price of $280.
     * @param availableRooms Number of available rooms.
     */
    public Suite(int availableRooms) {
        super(availableRooms, 280.0); // Suite room minimum bid
    }

    /**
     * Handles bids for Suite rooms. If a bid exceeds the minimum bid price and there are available rooms, 
     * it is accepted and the number of available rooms is reduced. 
     * If there are no available rooms or the bid amount is insufficient, the bid is forwarded to the next room. 
     * @param bidPrice The customer's bid price.
     * @return A message indicating the bid's acceptance or rejection.
     */
    @Override
    public String handleRequest(double bidPrice) {
        if (bidPrice >= minBidPrice && availableRooms > 0) {
            availableRooms--;
            return "Suite room booked for $" + bidPrice;
        } else if (successor != null) {
            return successor.handleRequest(bidPrice);
        }
        return "Bid rejected. No available rooms/insufficient bid amount.";
    }
}