import java.io.*;
class TimeTablesCorrespondence{
	String filename;
	TimeTables[] ttBs;
	int numberOfTimeTables;

	TimeTablesCorrespondence(String name){
		ttBs = new TimeTables[31];
		this.filename = name;
		this.numberOfTimeTables = 0;
		//
		for (int i=0 ; i<30 ; i++){
			ttBs[i] = new TimeTables();
		}
		DatabaseRunner();
	}

	private void DatabaseRunner(){
		int i,num = 0;
		int x = 0;
		int y = 0;
		int index = 0;
		int buffer = 0;
		FileReader fin = null;
		try{
			fin = new FileReader(this.filename);
			do{
				i = fin.read();
				if (i!=-1){
					if ((char)i == '\n'){
						if (buffer < 6){
							this.ttBs[index].tMatrix[x][y] = num;
							x++;
							y = 0;
							num = 0;
							buffer++;
						}
						else{
							x = 0;
							y = 0;
							num = 0;
							buffer = 0;
							index++;
						}
					}
					else if ((char)i == ' '){
						this.ttBs[index].tMatrix[x][y] = num;
						y++;
						num = 0;
					}
					else{
						num = num*10 + (char)i - '0';
					}
				}
				
			}while(i!=-1);

			// Testing Purposes
			/*for (x=0 ; x<6 ; x++){
				for (y=0 ; y<8 ; y++){
					System.out.print(this.ttBs[2].tMatrix[x][y]+" ");
				}
				System.out.println();
			}*/

			//System.out.println();
			this.numberOfTimeTables = index;
		}
		catch (IOException e){
			System.out.println("Database Not Found");
		}
		finally{
			try{
				if (fin != null){
					fin.close();
				}
			}
			catch(IOException e){
				System.out.println("Error!Cannot close File.txt");
			}
		}
	}
	
}

class TimeTables{
	int[][] tMatrix;
	TimeTables(){
		this.tMatrix = new int [7][9];
	}
}


class TimeTable{
	public static void main(String args[]){
		TimeTablesCorrespondence tts = new TimeTablesCorrespondence("File.txt");
		System.out.println(tts.numberOfTimeTables);
	}
}
class Day{
	Courses[] courses;								 	
}

class Courses{
	String courseInstructor;
	String courseName;
	String lectureDays;
	String tutDays;
	String labDays;
	int courseCode;
	Courses(String instructorName, String courseName, int courseCode, String lectureDays, String tutDays, String labDays){
		this.courseInstructor = instructorName;
		this.courseName = courseName;
		this.lectureDays = lectureDays;
		this.tutDays = tutDays;
		this.labDays = labDays;
		this.courseCode = courseCode;
	}
}