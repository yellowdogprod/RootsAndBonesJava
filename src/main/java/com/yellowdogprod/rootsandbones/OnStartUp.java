package com.yellowdogprod.rootsandbones;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Random;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartController;
import com.yellowdogprod.rootsandbones.parts.PartService;
import com.yellowdogprod.rootsandbones.parts.PartType;
import com.yellowdogprod.rootsandbones.registration.RegistrationRequest;
import com.yellowdogprod.rootsandbones.registration.RegistrationService;
import com.yellowdogprod.rootsandbones.zone.Zone;
import com.yellowdogprod.rootsandbones.zone.ZoneService;

@Component
@Order(1)
public class OnStartUp implements CommandLineRunner {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private PartService partService;

	@Autowired
	private ZoneService zoneService;

	private ArrayList<Part> partsLoaded;

	@Override
	public void run(String... args) throws Exception {

		insertPartData();

		insertZoneData();

		RegistrationRequest req = new RegistrationRequest("root", "root", "root@gmail.com");
		registrationService.register(req);

	}

	void insertPartData() {
		try {
			File myObj = new File("src/main/resources/data/parts.csv");
			Scanner myReader = new Scanner(myObj);
			partsLoaded = new ArrayList<Part>();
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
				newPart.setLevel(Integer.parseInt(data[7]));
				partService.save(newPart);
				partsLoaded.add(newPart);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	void insertZoneData() {
		try {
			File myObj = new File("src/main/resources/data/zones.csv");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String[] data = myReader.nextLine().split(",");
				Zone zone = new Zone();
				zone.setId(Long.parseLong(data[0]));
				zone.setName(data[1]);
				zone.setFlesh(Integer.parseInt(data[2]));
				zone.setBones(Integer.parseInt(data[3]));
				zone.setLeaves(Integer.parseInt(data[4]));
				zone.setRoots(Integer.parseInt(data[5]));
				zone.setLevel(Integer.parseInt(data[6]));
				zone.setParts(new ArrayList<Part>());
				int partsCount = 0;
				int maxParts = 8;
				boolean hasBody = false;
				boolean hasLegs = false;
				boolean hasArms = false;
				boolean hasHead = false;
				Collections.shuffle(partsLoaded);
				for (int x = 0; x < partsLoaded.size(); x++) {
					Part p = partsLoaded.get(x);
					boolean wasAdded = false;
					if (p.getLevel() <= zone.getLevel()) {
						// se ancora non ho raggiunto il numero minimo di parti, aggiungo a prescindere
						// la parte in questione
						switch (p.getType()) {
							case "BODY":
								if (!hasBody) {
									zone.getParts().add(p);
									partsCount++;
									wasAdded = true;
									hasBody = true;
								}
								break;
							case "LEGS":
								if (!hasLegs) {
									zone.getParts().add(p);
									partsCount++;
									wasAdded = true;
									hasLegs = true;
								}
								break;
							case "ARMS":
								if (!hasArms) {
									zone.getParts().add(p);
									partsCount++;
									wasAdded = true;
									hasArms = true;
								}
								break;
							case "HEAD":
								if (!hasHead) {
									zone.getParts().add(p);
									partsCount++;
									wasAdded = true;
									hasHead = true;
								}
								break;
						}

						if (!wasAdded) {
							double r = Math.random();
							double v = new Double(partsLoaded.size() - x) / new Double(partsLoaded.size());
							if (r < v) {
								zone.getParts().add(p);
								partsCount++;
							}
						}
					}

					// se la zona ha caricato il numero massimo di parti mi fermo
					if (partsCount >= maxParts)
						break;
				}

				zoneService.save(zone);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
