package com.yellowdogprod.rootsandbones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartService;
import com.yellowdogprod.rootsandbones.parts.PartType;
import com.yellowdogprod.rootsandbones.registration.RegistrationRequest;
import com.yellowdogprod.rootsandbones.registration.RegistrationService;

@Component
@Order(1)
public class OnStartUp implements CommandLineRunner{

	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private PartService partService;
	
	@Override
	public void run(String... args) throws Exception {
		
		insertPartData();

		RegistrationRequest req = new RegistrationRequest("root", "root", "root@gmail.com");
		registrationService.register(req);
		
	}
	
	void insertPartData() {
	    try {
	        File myObj = new File("src/main/resources/data/parts.csv");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String[] data = myReader.nextLine().split(",");
	          Part newPart = new Part();
	          newPart.setName(data[0]);
	          newPart.setPrefabName(data[1]);
	          PartType pt = PartType.valueOf(data[2]);
	          newPart.setType(pt.toString());
	          newPart.setFlesh(Integer.parseInt(data[3]));
	          newPart.setBones(Integer.parseInt(data[4]));
	          newPart.setLeaves(Integer.parseInt(data[5]));
	          newPart.setRoots(Integer.parseInt(data[6]));
	          partService.save(newPart);
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	

}
