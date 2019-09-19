import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;
import javax.xml.crypto.Data;

public class FlightReservationFrame extends JFrame
{
    //Making Panels that will be added to the Frame
	private JPanel topPanel = new JPanel();
	//The Table will be stored in the middle panel
	private JPanel middlePanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	//components that belong to the top Panel
	private JLabel deptLabel = new JLabel("Departure: ");
    private JComboBox deptBox = new	JComboBox();
    private JLabel destinationLabel = new JLabel("Destination: ");
    private JComboBox destinationBox = new JComboBox();
    private JButton searchButton = new JButton("Search");
    private JButton showAllButton = new JButton("Show All");
    
    //components that belong to bottom Panel
    private JLabel numberOfTickets = new JLabel("Number of Tickets");
    private JTextField field = new JTextField(10);
    private JButton purchaseButton = new JButton("Purchase");
        //Array of Strings that will populate the departure comboBox
    private String[]departures = {"Copenhagen","Dublin",
    		"Edinburgh", "London", "New York", "Oslo", "San Francisco"};
    //Array of Strings that will populate the destination comboBox
    private String[]destinations = {"Copenhagen","Dublin",
		"Edinburgh", "London", "New York", "Oslo", "San Francisco"};
    
	public FlightReservationFrame()
	{
		FlightReservationModel flightTableModel = new FlightReservationModel();
		//Creating the JTable
		JTable table = new JTable(flightTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		//Adding a JScrollPane to the table
        JScrollPane scrl = new JScrollPane(table);
		
        //Creating a date object
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		String stringDate = dateFormat.format(today);
		//setting the date to a JLabel
		JLabel date = new JLabel("Today's Date: "+ stringDate);
		date.setForeground(Color.blue);
		//Passing in the values into the comboBox
		deptBox = new JComboBox(departures);
		destinationBox = new JComboBox(destinations);
       //Makes Dublin the default departure city when the program is launched
		deptBox.setSelectedIndex(1);
	
		//making the sorter
		TableRowSorter<TableModel> sorter = new TableRowSorter(table.getModel());
        //Setting the sorter to the table 
		table.setRowSorter(sorter);
         
         //filtering data
         searchButton.addActionListener(new ActionListener(){

 			@Override
 			public void actionPerformed(ActionEvent e) {
 				
 				if(deptBox.getSelectedItem()==destinationBox.getSelectedItem())
		    	{
		    		 JOptionPane.showMessageDialog(null,"Departure and Destination Cities cannot be the same","Warning Message",JOptionPane.INFORMATION_MESSAGE);
		    		
		    	}
 				else {
 					//Searched online on how to filter 2 columns at once and came along an andFilter 
 					List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
 			         filters.add(RowFilter.regexFilter((String)deptBox.getSelectedItem(),0));
 			         filters.add(RowFilter.regexFilter((String)destinationBox.getSelectedItem(), 1));
 			         RowFilter<Object,Object>example=RowFilter.andFilter(filters);
 			         sorter.setRowFilter(example);
 				}
 				}
     		
     	});
       
         
         
        
         showAllButton.addActionListener(new ActionListener(){

  			@Override
  			public void actionPerformed(ActionEvent e) {
  				//sets it so that all flights display
  				sorter.setRowFilter(null);
  			}
      		
      	});
         
         purchaseButton.addActionListener(new ActionListener(){

   			@Override
   			public void actionPerformed(ActionEvent e) {
   				//no actual functionality yet just used to format field
   			try {
   				int x = Integer.parseInt(field.getText());
   			}
   			catch(Exception z) {
   			 JOptionPane.showMessageDialog(null,"ERROR:You must enter a whole number!","Warning Message",JOptionPane.INFORMATION_MESSAGE);
   			}
   			}
       		
       	});
         
         
        
		add(topPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        //Adding components to the top Panel
        topPanel.add(deptLabel);
        topPanel.add(deptBox );
        topPanel.add(destinationLabel);
        topPanel.add(destinationBox);
        topPanel.add(searchButton);
        topPanel.add(showAllButton);
        
        
       field.setColumns(10);
        //adding a scroll to the table
        middlePanel.add(scrl);
        //adding components to the bottom panel
        bottomPanel.add(numberOfTickets);
        bottomPanel.add(field);
        bottomPanel.add(purchaseButton);
      
        bottomPanel.add(date);
		//set frame properties
		//so that the system closes when exited out an doesn't continue to run in the background	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//setSize(300,300);
		pack();
		//occupies all the space
		
		//Sets title
		setTitle("Flight Reservation");
	
	}
	
	
	
	
		

public static void main(String[] args) {
	
	new FlightReservationFrame();

}
}