import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainGUI{
	public static void main(String args[]){
		MainPage mp = new MainPage();
	}
}

class MainPage{

	JFrame mainPageFrame;
	CoursePanel[] courseTabPanel;
	JTabbedPane tabbedPane;

	MainPage(){

		courseTabPanel = new CoursePanel[6];
		courseTabPanel[0] = new CoursePanel("Mathematics1");
		courseTabPanel[1] = new CoursePanel("Chemistry1");
		courseTabPanel[2] = new CoursePanel("Mechanics");
		courseTabPanel[3] = new CoursePanel("BIOLab");
		courseTabPanel[4] = new CoursePanel("Biology1");
		courseTabPanel[5] = new CoursePanel("Workshop");

		mainPageFrame = new JFrame("TimeTable Assist");
		mainPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPageFrame.setSize(600,600);

		tabbedPane = new JTabbedPane();

		tabbedPane.add("Mathematics1",courseTabPanel[0].GiveCoursePanel());
		tabbedPane.add("Chemistry1",courseTabPanel[1].GiveCoursePanel());
		tabbedPane.add("Mechanics",courseTabPanel[2].GiveCoursePanel());
		tabbedPane.add("BIOLab",courseTabPanel[3].GiveCoursePanel());
		tabbedPane.add("Biology1",courseTabPanel[4].GiveCoursePanel());
		tabbedPane.add("Workshop",courseTabPanel[5].GiveCoursePanel());

		mainPageFrame.add(tabbedPane);
		mainPageFrame.setVisible(true);
	}

}

class CoursePanel{
	JLabel courseName;
	JPanel coursePanel;
	 CoursePanel(String name){
		courseName = new JLabel(name);
		coursePanel = new JPanel();
		coursePanel.add(courseName);
	}
	JPanel GiveCoursePanel(){
		return this.coursePanel;
	}
}