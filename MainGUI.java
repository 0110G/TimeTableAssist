\import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MainGUI{
	public static void main(String args[]){
		MainPage mp = new MainPage();
		//CourseListCodeCorrespondence cLCC = new CourseListCodeCorrespondence("Courses.txt");

	}
}

class MainPage implements ActionListener{

	//static CourseListCodeCorrespondence cLCC;
	JFrame mainPageFrame;
	CoursePanel[] courseTabPanel;
	JTabbedPane tabbedPane;
	JPanel prefPanel;
	TimeTablesCorrespondence tbc;
	JButton searchButton;
	JButton[] prefLabels;
	JLabel[] timeTableOrders;
	ButtonIndexCorresspondence[] btiC;
	//BestPreferencePanel bPF;

	MainPage(){
		//bPF = new BestPreferencePanel();
		courseTabPanel = new CoursePanel[6];

		tbc = new TimeTablesCorrespondence("File.txt");

		int x = tbc.numberOfTimeTables;

		prefLabels = new JButton[x];

		timeTableOrders = new JLabel[x];

		btiC = new ButtonIndexCorresspondence[x];

		for (int i=0 ; i<x ; i++){
			btiC[i] = new ButtonIndexCorresspondence(i,0.0);
		}

		courseTabPanel[0] = new CoursePanel(1,"Mathematics1");
		courseTabPanel[1] = new CoursePanel(2,"Chemistry1");
		courseTabPanel[2] = new CoursePanel(3,"Mechanics");
		courseTabPanel[3] = new CoursePanel(4,"BIOLab");
		courseTabPanel[4] = new CoursePanel(5,"Biology1");
		courseTabPanel[5] = new CoursePanel(6,"Workshop");

		mainPageFrame = new JFrame("TimeTable Assist");
		mainPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPageFrame.setSize(900,600);

		tabbedPane = new JTabbedPane();

		BestPreferencePanel();

		tabbedPane.add("Mathematics1",courseTabPanel[0].GiveCoursePanel());
		tabbedPane.add("Chemistry1",courseTabPanel[1].GiveCoursePanel());
		tabbedPane.add("Mechanics",courseTabPanel[2].GiveCoursePanel());
		tabbedPane.add("BIOLab",courseTabPanel[3].GiveCoursePanel());
		tabbedPane.add("Biology1",courseTabPanel[4].GiveCoursePanel());
		tabbedPane.add("Workshop",courseTabPanel[5].GiveCoursePanel());
		tabbedPane.add("Find Best Preference",prefPanel);

		mainPageFrame.add(tabbedPane);
		mainPageFrame.setVisible(true);
	}

	Preferences GetCourseLists(){
		Preferences pre = new Preferences();
		for (int i=0 ; i<6 ; i++){
			pre.pref1[i] = courseTabPanel[i].GetPre1();
			pre.pref2[i] = courseTabPanel[i].GetPre2();
		}
		return pre;
	}

	private void BestPreferencePanel(){
		prefPanel = new JPanel();
		//prefPanel.setLayout(new GridLayout(0,1,0,0));
		//GridBagConstraints cs = new GridBagConstraints();
		prefPanel.setLayout(new GridLayout(0,1,1,1));

		JPanel[] tempPanel1 = new JPanel[tbc.numberOfTimeTables+1];
		for (int i=0 ; i<=tbc.numberOfTimeTables ; i++){
			tempPanel1[i] = new JPanel();
		}

		searchButton = new JButton("Find Best TimeTable");
		searchButton.addActionListener(this);
		tempPanel1[0].add(searchButton);
		prefPanel.add(tempPanel1[0]);
		
		for (int i=1 ; i<=tbc.numberOfTimeTables ; i++){
			prefLabels[i-1] = new JButton("View TimeTable");
			prefLabels[i-1].addActionListener(this);
			timeTableOrders[i-1] = new JLabel("TimeTable" + Integer.toString(i));
			timeTableOrders[i-1].setFont(new Font("Arial",Font.BOLD,20));
			tempPanel1[i].add(timeTableOrders[i-1]);
			tempPanel1[i].add(prefLabels[i-1]);
			prefPanel.add(tempPanel1[i]);
		}
		
		//tempPanel1.setLayout( new GridLayout(0,1,10,10));
	}

	public void actionPerformed(ActionEvent ae){
		int x = tbc.numberOfTimeTables;
		if (ae.getSource() == searchButton){
			//LoginPage lp = new LoginPage();
			Preferences prefRec = GetCourseLists();
			//timeTableOrders[0].setText("1");
			for (int i=0 ; i<x ; i++){
				double res = this.GiveTimeTablePrefValue(prefRec,i);
				//System.out.println(res);
				for (int j=0 ; j<x ; j++){
					if (btiC[j].buttonIndex == i){
						btiC[j].ChangePrefVal(res);
						break;		
					}
				}							
			}
			//System.out.println();
			int maxI = 0;
			for (int i=0 ; i<x-1 ; i++){
				maxI = i;
				for (int j=i+1 ; j<x ; j++){
					if (btiC[maxI].prefVal < btiC[j].prefVal){
						maxI = j;
					}
				}
				ButtonIndexCorresspondence teB = new ButtonIndexCorresspondence(btiC[maxI].buttonIndex, btiC[maxI].prefVal);
				btiC[maxI] = btiC[i];
				btiC[i] = teB;
				//System.out.println(btiC[i].buttonIndex);
			}
			for (int i=0 ; i<x ; i++){
				timeTableOrders[i].setText("TimeTable" + Integer.toString(btiC[i].buttonIndex + 1));
			}
		}
		else{
			System.out.println(1);
			for (int i=0 ; i<x ; i++){
				if (ae.getSource() == prefLabels[i]){
					JFrame f = new JFrame("TimeBle");
					break;

				}
			}
		}			
	}

	private double GiveTimeTablePrefValue(Preferences pref, int timetableIndex){
		boolean[] occ = new boolean[100];
		for (int i=0 ; i<6 ; i++){
			for (int j=0 ; j<8 ; j++){

				occ[this.tbc.ttBs[timetableIndex].tMatrix[i][j]] = true;
			}
		}
		double val = 0;
		for (int i=0 ; i<6 ; i++){
			if (occ[pref.pref1[i]]){
				val += 1;
			}
			if (occ[pref.pref2[i]]){
				val += 0.5;
			}
		}
		return val;
	}
}

class Preferences{
	int[] pref1;
	int[] pref2;
	Preferences(){
		pref1 = new int[10];
		pref2 = new int[10];
	}
}

class ButtonIndexCorresspondence{
	int buttonIndex;
	double prefVal;
	ButtonIndexCorresspondence(int in, double va){
		this.buttonIndex = in;
		this.prefVal = va;
	}
	public void ChangePrefVal(double va){
		this.prefVal = va;
	}
}

class CoursePanel extends JFrame implements ItemListener,ActionListener{
	static Preferences pref;
	static CourseListCodeCorrespondence cLCC;
	JScrollPane coursePreferencesScrollPane;
	JScrollPane coursePreferencesScrollPane1;
	DefaultComboBoxModel dModel;
	JComboBox<String> coursePreferences;
	JComboBox<String> coursePreferences1;
	JLabel courseName;
	JLabel instruction;
	JLabel detailLabel;
	JLabel prefMsg1;
	JLabel prefMsg2;
	JLabel courseDetails1;
	JLabel courseDetails2;
	JPanel coursePanel;
	JButton rst;
	 CoursePanel(int courseIntials ,String name){
	 	cLCC = new CourseListCodeCorrespondence("Courses.txt");
	 	int[] arr = new int [11];
	 	int index = 0;
	 	for (int i=10*courseIntials ; i<10*(courseIntials+1) ; i++){
	 		if (cLCC.coursePresent[i]){
	 			arr[index] = i;
	 			index++;
	 		}
	 	}


	 	coursePanel = new JPanel();

	 	coursePanel.setLayout( new GridLayout(0,3,10,10)); 
	 	coursePanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));


	 	instruction = new JLabel("",SwingConstants.CENTER);
	 	//instruction.setFont(new Font("Arial",Font.BOLD,20));
	 	
	 	coursePanel.add(instruction);


	 	courseName = new JLabel(name,SwingConstants.CENTER);														// Add courseName to each tab
	 	courseName.setFont(new Font("Arial",Font.BOLD,40));
	 	coursePanel.add(courseName);

	 	detailLabel = new JLabel("Details",SwingConstants.CENTER);
	 	detailLabel.setFont(new Font("Arial",Font.BOLD,20));
	 	coursePanel.add(detailLabel);


		prefMsg1 = new JLabel("Choose Section Pref1: ");											// Choice name label for each tab
		prefMsg1.setFont(new Font("Arial",Font.BOLD,15));
		prefMsg1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		coursePanel.add(prefMsg1);

		coursePreferences = new JComboBox<String>();										// Combo box : Preference 1
		coursePreferencesScrollPane = new JScrollPane(coursePreferences);
		//coursePreferences.addItem("Any");
		for (int i=0 ; i<index ; i++){
			coursePreferences.addItem(Integer.toString(arr[i]));
		}
		if (index!=0){
			coursePreferences.setSelectedIndex(0);	
		}
		coursePanel.add(coursePreferences);
		//coursePanel.add(coursePreferencesScrollPane);
		coursePreferences.addItemListener(this);
		
		courseDetails1 = new JLabel("Any",SwingConstants.CENTER);
		courseDetails1.setFont(new Font("Arial",Font.BOLD,15));
		courseDetails1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		coursePanel.add(courseDetails1);

		prefMsg2 = new JLabel("Choose Section Pref2: ");											// Choice name label for each tab
		prefMsg2.setFont(new Font("Arial",Font.BOLD,15));
		prefMsg2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		coursePanel.add(prefMsg2);

		coursePreferences1 = new JComboBox<String>();										// Combo box : Preference 2
		coursePreferencesScrollPane1 = new JScrollPane(coursePreferences1);
		//coursePreferences1.addItem("Any");
		for (int i = 0 ; i<index ; i++){
			coursePreferences1.addItem(Integer.toString(arr[i]));
		}
		if (index!=0){
			coursePreferences1.setSelectedIndex(0);	
		}
		coursePanel.add(coursePreferences1);
		coursePreferences1.addItemListener(this);

		courseDetails2 = new JLabel("Any",SwingConstants.CENTER);
		courseDetails2.setFont(new Font("Arial",Font.BOLD,15));
		courseDetails2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		coursePanel.add(courseDetails2);

		JPanel jp = new JPanel();
		rst = new JButton("Reset");
		rst.setSize(new Dimension(20, 20));
		//new JButton("Reset")
		rst.addActionListener(this);
		jp.add(rst);
		coursePanel.add(jp);
	}
	JPanel GiveCoursePanel(){
		return this.coursePanel;
	}

	public void itemStateChanged(ItemEvent e) 
    { 
        // if the state combobox is changed 
        if (e.getSource() == coursePreferences) {

        	if (Integer.parseInt(coursePreferences.getSelectedItem().toString())%10 != 0){
        		int index = Integer.parseInt(coursePreferences.getSelectedItem().toString());
        		courseDetails1.setText("<html>Instructor: "+cLCC.courseList[index].courseInstructor + "<br/>CourseName:" + cLCC.courseList[index].courseName +
  				"<br/>LectureDays: "+cLCC.courseList[index].lectureDays+
        		"<br/>TutorialDays: "+cLCC.courseList[index].tutDays+
        		"<br/>LabDays: "+cLCC.courseList[index].labDays+"</html>");
        	}
        	else{
        		courseDetails1.setText("Any");
        	} 
        } 
        if (e.getSource() == coursePreferences1){
        	if (Integer.parseInt(coursePreferences1.getSelectedItem().toString())%10 != 0){
        		int index = Integer.parseInt(coursePreferences1.getSelectedItem().toString());
        		courseDetails2.setText("<html>Instructor: "+cLCC.courseList[index].courseInstructor + "<br/>CourseName:" + cLCC.courseList[index].courseName +
  				"<br/>LectureDays: "+cLCC.courseList[index].lectureDays+
        		"<br/>TutorialDays: "+cLCC.courseList[index].tutDays+
        		"<br/>LabDays: "+cLCC.courseList[index].labDays+"</html>");
        	}
        	else{
        		courseDetails2.setText("Any");
        	}
        }
        if (e.getSource() == rst){
        	
        	courseDetails1.setText("Any");
        	courseDetails2.setText("Any");
        }
    }

    public void actionPerformed(ActionEvent ae){
    	coursePreferences.setSelectedIndex(0);
    	coursePreferences1.setSelectedIndex(0);
    }

    public int GetPre1(){
    	return Integer.parseInt(this.coursePreferences.getSelectedItem().toString());
    }

    public int GetPre2(){
    	return Integer.parseInt(this.coursePreferences1.getSelectedItem().toString()); 
    }
}

class CourseListCodeCorrespondence{
	static Courses[] courseList;
	static String filename;
	static boolean[] coursePresent;
	CourseListCodeCorrespondence(String filename){
		this.filename = filename;
		courseList = new Courses[81];
		coursePresent = new boolean[81];
		for (int i=0 ; i<80 ; i++){
			courseList[i+1] = new Courses("Prof","Subject",i+1,"M W F","NA","NA");
		}
		this.DataBaseRunner();
		//System.out.println(courseList[11].courseInstructor);
		//System.out.println(courseList[12].courseInstructor);
	}
	private void DataBaseRunner(){

		int courseCode = 0;
		String professorName = "";
		String subjectName = "";
		String lectureDays = "";
		String tutDays = "";
		String labDays = "";

		FileInputStream fin = null;
		int i;
		int buffer = 0;
		String temp = "";
		int num = 0;
		try{
			fin = new FileInputStream(this.filename);
			do{
				i = fin.read();
				if (i!=-1){
					if (buffer == 0){
						if ((char)i == '\n'){
							courseCode = num;
							buffer++;
							num = 0;
						}
						else{
							num = num*10 + (char)i - '0';
						}
					}
					else if (buffer == 1){
						if ((char)i == '\n'){
							professorName = temp;
							temp = "";
							buffer++; 
						}
						else{
							temp = temp + (char)i; 
						}
					}
					else if (buffer == 2){
						if ((char)i == '\n'){
							lectureDays = temp;
							temp = "";
							buffer++; 
						}
						else{
							temp = temp + (char)i; 
						}	
					}
					else if (buffer == 3){
						if ((char)i == '\n'){
							tutDays = temp;
							temp = "";
							buffer++; 
						}
						else{
							temp = temp + (char)i; 
						}	
					}
					else if (buffer == 4){
						if ((char)i == '\n'){
							labDays = temp;
							temp = "";
							buffer++; 
						}
						else{
							temp = temp + (char)i; 
						}	
					}
					else if (buffer == 5){
						if ((char)i == '\n'){
							subjectName = temp;
							temp = "";
							buffer++; 
						}
						else{
							temp = temp + (char)i; 
						}
					}
					else{
						if ((char)i == '\n'){
							this.courseList[courseCode] = new Courses(professorName,subjectName,courseCode,lectureDays,tutDays,labDays);
							this.coursePresent[courseCode] = true;
							buffer = 0; 
						}
					}
					
				}
			}while (i!=-1);
		}
		catch(IOException e){
			System.out.println("Error!"+this.filename+"not found");
		}
		finally{
			try{
				if (fin != null){
					fin.close();
				}
			}
			catch (IOException e){
				System.out.println("Error!Unable to close Courses.txt");
			}
		}
	}
}
