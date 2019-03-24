import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TimeTableView{
	public static void main(String args[]){
		JFrame jf = new JFrame("Wad");
		TimeTablesCorrespondence tbc = new TimeTablesCorrespondence("File.txt");
		TimeTableWindowView tbvW = new TimeTableWindowView(jf,tbc,0,true);

	}
}


class TimeTableWindowView{
	TimeTableWindowView(JFrame timeTableFrame, TimeTablesCorrespondence tbc, int timeTableIndex, boolean closeOp){
		timeTableFrame.setVisible(true);
		if (closeOp){
			timeTableFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}
		timeTableFrame.setSize(500,500);
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(7,9));

		for (int i=0 ; i<7 ; i++){
			for (int j=0 ; j<9; j++){
				JLabel jl = new JLabel("w");
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jp.add(jl);
			}
		}
		timeTableFrame.add(jp);
	}
}