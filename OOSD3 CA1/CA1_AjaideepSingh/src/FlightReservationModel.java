import javax.swing.table.AbstractTableModel;
public class FlightReservationModel extends AbstractTableModel
{
	private String[] columnNames = {"Departure",
            "Destination",
            "Flight Number",
            "Number of Txx","First Class Available"};

	
	private Object[][] data = {
		    {"Dublin", "Copenhagen","SK538" , new Integer(200),new Boolean(false)},
		    {"Dublin", "Oslo","DY1363", new Integer(27),new Boolean(false)},
		    {"San Francisco","Dublin","EI147", new Integer(30),new Boolean(true)},
		    {"Edinburgh","Dublin","EI147", new Integer(30),new Boolean(false)},
		    {"New York","Dublin", "EI109",new Integer(40), new Boolean(true)}
		    
	};
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int row,int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0,c).getClass();
	}
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	public void setValueAt(Object value,int row,int col)
	{
		data[row][col]=value;
		fireTableCellUpdated(row, col);
	}
	public boolean isCellEditable(int row,int col)
	{
		if(col<3)
		{
		return false;
		}
		else 
			return true;
	}
}
