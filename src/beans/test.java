package beans;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		int num = (int)(Math.random()*999999+1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss:");
		String d = formatter.format(new Date())+num;
		System.out.println(d);
		File folder = new File("/home/koju/Desktop/uploads");
		try {
			if(!folder.exists()) {
				folder.mkdir();
			}
			File file = new File("/home/koju/Desktop/uploads/test.txt");
			if(file.exists()) {
				System.out.println("The file with the same name already exist.!!!");
			}
			else
				file.createNewFile();
		}
		catch(Exception er) {
			System.out.println("Error : "+er.getMessage());
		}
	}
}
