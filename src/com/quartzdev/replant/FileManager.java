package com.quartzdev.replant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.UUID;

public class FileManager {
	
	File file;
	File tempFile = new File("plugins" + File.separator + "Replant" + File.separator + "users_temp.txt");
	
	protected FileManager(File file) {
		this.file = file;
		file.getParentFile().mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected boolean getBoolean(UUID id) throws MissingResourceException {
		try {
			FileReader stream = new FileReader(file);
			BufferedReader in = new BufferedReader(stream);
			
			String line;
			while ((line = in.readLine()) != null) {
				if (!line.startsWith("#") || !line.isEmpty()) {
					if (line.startsWith(id.toString())) {
						in.close();
						return Boolean.valueOf(line.split("=")[1]);
					}
				}
			}
			
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new MissingResourceException(null, null, null);
	}
	
	protected void setBoolean(UUID id, boolean b) {
		try {
			FileWriter writeStream = new FileWriter(tempFile);
			BufferedWriter out = new BufferedWriter(writeStream);
			
			FileReader readStream = new FileReader(file);
			BufferedReader in = new BufferedReader(readStream);
			
			String line;
			boolean set = false;
			while ((line = in.readLine()) != null) {
				
				if (!line.startsWith("#") || !line.isEmpty()) {
					
					if (line.startsWith(id.toString())) {
						line = id.toString() + "=" + b;
						set = true;
					}
					
				}
				
				if (line != null) {
					out.write(line);
					out.newLine();
				}
				
			}
			
			if (!set) {
				out.write(id.toString() + "=" + b);
				out.newLine();
			}
			
			in.close();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.delete();
			tempFile.renameTo(file);
		}
	}
	
}
