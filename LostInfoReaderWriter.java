import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * A class that reads, writes, and modifies the file
 * @author xuefeichen
 *
 */
public class LostInfoReaderWriter {
	
	// create a file
	private File file;
	
	// make a new lost list
	private LostList lostList = new LostList();
	
	// make an ID
	private int id;
	
	// constructor
	public LostInfoReaderWriter(String fileName){
		
		// let file be the file passed in
		file = new File(fileName);
	}
	
	// method to read a file line by line and lost list
	public LostList readFile(){
		
		// create a buffered reader
		BufferedReader reader;		
	
		// try
		try {
			
			// create a new fileReader for the file
			FileReader fileRead = new FileReader(file);
			
			// create a new buffered reader
			reader = new BufferedReader(fileRead);

			// get the content from the current line
			String currentLine = reader.readLine();

			// while there is still line left and the line is not empty
			while(currentLine!=null && currentLine.equals("")==false){
				
				// split the line and store the results to a string array
				String[] properties = currentLine.split("~");
				
				// the id is the first one
				id = Integer.parseInt(properties[0]);
				
				// the isPerson boolean is the second
				boolean isPerson = Boolean.parseBoolean(properties[1]);
				
				// the string path is the third
				String imgPath = properties[2];
				
				// the name 
				String name = null;
				
				// only when the name is not "null"
				if(properties[3].equals("null")==false){
					
					// let the name be the fourth datum
					name = properties[3];
				}
				
				// the description
				String description = null;
				
				// only when the description is not "null"
				if(properties[4].equals("null")==false){
					
					// let the description be the fifth datum
					description = properties[4];
				}
				
				// make a new travel history list
				DoublyLinkedList<String> travelHis = new DoublyLinkedList<String>();
				
				// if the travel history is not "" or "null"
				if(properties.length>5 && properties[5].equals("null")==false){
					
					// insert the string to the travel history doubly linked list
					travelHis.insertFirst(properties[5]);
				}
				
				try{
					
					// get the image from the image path
					Image image = ImageIO.read(new File(imgPath));
					
					// make a resizable image
					Image resizableImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
					
					// make an image icon
					ImageIcon imageIcon = new ImageIcon(resizableImage);
					
					// make a new lost object using the retrieved data
					LostOne newLost = new LostOne(id,imageIcon,name,description,travelHis,isPerson);
					
					// set the image path for the new lost object
					newLost.setImgPath(imgPath);
					
					// make a new single lost panel for the lost object
					SingleLostPanel lostPanel = new SingleLostPanel(newLost);
					
					// add the lost panel to the lost list
					lostList.addLost(lostPanel);
					
				}catch(IOException ex){
					
				}
				
				// move on to the next line
				currentLine = reader.readLine();
				
				// update the id
				id++;
			}
			
			// close the reader
			reader.close();
			
			
		// if the file is not found
		} catch (FileNotFoundException e) {
			
			// print the error message
			System.err.println(e.getMessage());
			
		// if there's input/output error
		} catch (IOException e) {
			
			// print the error message
			System.err.println(e.getMessage());
		}
		
		// return the lost list
		return lostList;
	}
	
	// write a new lost object's properties to the file
	public void writeFile(int id, boolean isPerson, String imgPath, String name, String description,String travelHis){
		
		try {
			
			// make a new file writer, and set append mode to be true
			FileWriter fileWrite = new FileWriter(file.getAbsoluteFile(),true);

			// a string representation of the id
			String idS = String.valueOf(id);
			
			// a string representation of the isPerson boolean
			String isPersonS = String.valueOf(isPerson);
			
			// create a new fileReader for the file
			FileReader fileRead = new FileReader(file);
			
			// make a new buffered reader
			BufferedReader bufferedReader = new BufferedReader(fileRead);
			
			// read the first line
			String currentLine = bufferedReader.readLine();
			
			// make a string with all the properties
			String content = idS+"~"+isPersonS+"~"+imgPath+"~"+name+"~"+description+"~"+travelHis;
			
			// if the first line is ""
			if(currentLine!=null && currentLine.equals("")){
				
				// directly write the content with the line separator
				fileWrite.write(content+"\n");
			
			// otherwise
			}else{
				
				// append the content to the end of the file with the line separator
				fileWrite.append(content+"\n");
			}
			
			// close the writer
			fileWrite.close();
			bufferedReader.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}

	// modify the data of a lost object
	public void modifyFile(int id, boolean isPerson, String imgPath, String name, String description,String travelHis){
		
		// create a buffered reader
		BufferedReader reader;		
		
		// use a print writer
		PrintWriter writer;
		
		// the file name for the new file
		String newFileName = "tempLostInfo.txt";
		
		// make a new file
		File newFile = new File(newFileName);
		
		
		try {
			
			// create a new file reader for the file
			FileReader fileRead = new FileReader(file);
			
			// create a new file writer for the new file, with append mode open
			FileWriter fileWrite = new FileWriter(newFile,true);
			
			// create a new buffered reader
			reader = new BufferedReader(fileRead);
			
			// create a new print writer
			writer = new PrintWriter(fileWrite);
			
			// make a string for all the new properties
			String content = id+"~"+isPerson+"~"+imgPath+"~"+name+"~"+description+"~"+travelHis;
			
			// read the file line by line
			String currentLine = reader.readLine();
			
			// while the current line is not null
			while(currentLine!=null && currentLine.equals("")==false){

				// get the properties
				String[] properties = currentLine.split("~");
				
				// if the id matches the one to be modified
				if(Integer.parseInt(properties[0])==id){
					
					// replace the current line with the new content
					currentLine = currentLine.replace(currentLine, content);					
				}
				
				// write the line to the new file
				writer.println(currentLine);
				
				// move on to the next line
				currentLine = reader.readLine();
			}

			// close the reader and writer
			reader.close();
			
			writer.close();
			
			//delete the old file
			file.delete();
			
			// rename the new file to the old one
			newFile.renameTo(file);
			
		// if the file is not found
		} catch (FileNotFoundException e) {
			
			// print the error message
			System.err.println(e.getMessage());
			
		// if there's input/output error
		} catch (IOException e) {
			
			// print the error message
			System.err.println(e.getMessage());
		}		
	}
	
	// get the current id
	public int getId(){
		
		// return the current id
		return id;
	}
	
	// increase the id by 1
	public void increaseId(){
		
		// increase the id by 1
		id++;
	}
}
