package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {
	JSONObject individualObj;
	List<Team> teamList = null;
	Map<String, Object> teamMap = null;
	List<Individual> individualList = null;
	JSONParser parser = new JSONParser();

	/**
	 * get a list of individual objects from db json file
	 * 
	 * @return
	 */
	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		Individual obj2 = null;
		teamMap = new HashMap<>();
		try {
			Object obj = parser.parse(
					new FileReader("D:\\Assignment2\\assignment-resource-io-master\\src\\main\\resources\\db.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray myfirst = (JSONArray) jsonObject.get("individuals");
			individualList = new ArrayList<>();
			for (int a = 0; a < myfirst.size(); a++) {
				individualObj = (JSONObject) myfirst.get(a);
				teamMap = (Map<String, Object>) individualObj.clone();
				obj2 = new Individual(teamMap);
				individualList.add(obj2);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return individualList;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		individualList = getListOfIndividuals();
		boolean flag = false;
		Iterator<Individual> itr = individualList.iterator();
		Individual individual = null;
		while (itr.hasNext()) {
			individual = itr.next();
			if (individual.getId() == id.intValue()) {
				flag = true;
				return individual;
			}
		}
		if (!flag) {
			throw new ObjectNotFoundException(this.getClass().getName(), "id", id.toString());
		}
		return null;

	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException, ObjectStreamException {
		boolean flag = false;
		individualList = getListOfIndividuals();
		Iterator<Individual> itr = individualList.iterator();
		Individual individual = null;
		while (itr.hasNext()) {
			individual = itr.next();
			if (individual.getName().equalsIgnoreCase(name)) {
				flag = true;
				return individual;
			}
		}
		if (!flag) {
			throw new ObjectNotFoundException(this.getClass().getName(), "id", name);
		}
		return individual;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		individualList = getListOfIndividuals();
		Iterator<Individual> itr = individualList.iterator();
		List<Individual> inactiveMembers = new ArrayList<>();
		Individual individual = null;
		while (itr.hasNext()) {
			individual = itr.next();
			if (!individual.isActive())
				inactiveMembers.add(individual);
		}
		return inactiveMembers;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		individualList = getListOfIndividuals();
		Iterator<Individual> itr = individualList.iterator();
		List<Individual> activeMembers = new ArrayList<>();
		Individual individual = null;
		while (itr.hasNext()) {
			individual = itr.next();
			if (individual.isActive())
				activeMembers.add(individual);
		}

		return activeMembers;
	}

	/**
	 * get a list of team objects from db json
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {
		JSONObject jsonObj;
		Team teamObject = null;
		try {
			teamList = new ArrayList<Team>();
			Object obj = parser.parse(
					new FileReader("D:\\Assignment2\\assignment-resource-io-master\\src\\main\\resources\\db.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray myfirst = (JSONArray) jsonObject.get("teams");

			teamList = new ArrayList<>();
			teamMap = new HashMap<>();
			for (int a = 0; a < myfirst.size(); a++) {
				jsonObj = (JSONObject) myfirst.get(a);
				teamMap = (Map<String, Object>) jsonObj.clone();
				teamObject = new Team(teamMap);
				teamList.add(teamObject);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return teamList;
	}
}