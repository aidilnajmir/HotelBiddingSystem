/**
 * This class implements the graphical user interface (GUI) for a hotel room bidding system. 
 * Users can view available rooms and place bids for various room types (Standard, Deluxe, and Suite).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiddingSystemGUI {
    private final HotelRoom chain;  // Bidding chain's starting point
    private JLabel standardLabel;   // Display available Standard room label
    private JLabel deluxeLabel;     // Display available Deluxe room label
    private JLabel suiteLabel;      // Display available Suite room label
    private JTextArea message;      // Display bid results
    
    /**
     * Creates a BiddingSystemGUI object using hotel room objects. 
     * @param standard Standard room object. 
     * @param deluxe Deluxe room object. 
     * @param suite Suite room object.
     */
    public BiddingSystemGUI(HotelRoom standard, HotelRoom deluxe, HotelRoom suite) {
        this.chain = suite;
        suite.setSuccessor(deluxe);
        deluxe.setSuccessor(standard);
        
        displayGUI(standard, deluxe, suite);
    }

    /**
     * Generates and displays the GUI for the bidding system. 
     * @param standard Standard room object. 
     * @param deluxe Deluxe room object. 
     * @param suite Suite room object.
     */
    private void displayGUI(HotelRoom standard, HotelRoom deluxe, HotelRoom suite) {
        JFrame frame = new JFrame("Hotel Room Bidding System");
        message = new JTextArea(6, 30);
        message.setEditable(false);
        JTextField bidField = new JTextField(10);
        JButton bidButton = new JButton("Place Bid");

        bidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double bid = Double.parseDouble(bidField.getText());
                    String result = chain.handleRequest(bid);
                    message.setText(result);
                    updateAvailableRoomsDisplay(standard, deluxe, suite);
                } catch (NumberFormatException ex) {
                    message.setText("Please enter a valid numeric bid.");
                }
            }
        });

        // Available rooms display labels
        standardLabel = new JLabel();
        deluxeLabel = new JLabel();
        suiteLabel = new JLabel();
        updateAvailableRoomsDisplay(standard, deluxe, suite);

        // Labels panel
        JPanel labelPanel = new JPanel(new GridLayout(3, 2));
        labelPanel.add(new JLabel("Available Standard Rooms:"));
        labelPanel.add(standardLabel);
        labelPanel.add(new JLabel("Available Deluxe Rooms:"));
        labelPanel.add(deluxeLabel);
        labelPanel.add(new JLabel("Available Suite Rooms:"));
        labelPanel.add(suiteLabel);

        // Input controls panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Bid: $"));
        inputPanel.add(bidField);
        inputPanel.add(bidButton);

        // Layout
        frame.setLayout(new BorderLayout());
        frame.add(labelPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(new JScrollPane(message), BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Updates the available room labels.
     * @param standard Standard room object.
     * @param deluxe Deluxe room object.
     * @param suite Suite room object.
     */
    private void updateAvailableRoomsDisplay(HotelRoom standard, HotelRoom deluxe, HotelRoom suite) {
        standardLabel.setText(String.valueOf(standard.availableRooms));
        deluxeLabel.setText(String.valueOf(deluxe.availableRooms));
        suiteLabel.setText(String.valueOf(suite.availableRooms));
    }

    // Main method to run the Bidding System GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelRoom standard = new Standard(45);
            HotelRoom deluxe = new Deluxe(15);
            HotelRoom suite = new Suite(10);
            new BiddingSystemGUI(standard, deluxe, suite);
        });
    }
}