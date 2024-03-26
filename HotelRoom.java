/** 
 * This abstract class defines a hotel room, including available rooms and minimum bid price. 
 * It also defines a successor for handling bids and gives an abstract mechanism for dealing with bids.
 */
public abstract class HotelRoom {
    protected HotelRoom successor;  // Successor room to handle bids
    protected int availableRooms;   // Number of available room of its type
    protected double minBidPrice;   // Minimum bid price of the room type

      /**
       * Creates a HotelRoom object with specified number of rooms and minimum bid price. 
       * @param availableRooms The quantity of available rooms. 
       * @param minBidPrice The minimum bid for this sort of room.
       */
    public HotelRoom(int availableRooms, double minBidPrice) {
        this.availableRooms = availableRooms;
        this.minBidPrice = minBidPrice;
    }

    /** 
     * Sets the successor room to handle bids if this room cannot fulfill them.
     * @param successor The successor room.
     */ 
    public void setSuccessor(HotelRoom successor) {
        this.successor = successor;
    }

    /**
     * An abstract method for accepting bids for this type of room. 
     * @param bidPrice The customer's bid price. 
     * @return A message confirming bid acceptance or rejection.
     */
    public abstract String handleRequest(double bidPrice);
}