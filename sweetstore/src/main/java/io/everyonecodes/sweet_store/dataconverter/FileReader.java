package io.everyonecodes.sweet_store.dataconverter;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public List<String> read(String fileName) {
		
		try {
			File file = ResourceUtils.getFile("classpath:" + fileName);
			return Files.readAllLines(file.toPath());
		} catch(IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
