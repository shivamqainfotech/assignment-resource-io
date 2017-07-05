package com.qainfotech.tap.training.resourceio;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {
	
	
	List<Individual> listOfActiveIndividuals = null;
	List<Individual> listOfInactiveIndividuals = null;
	Map<String, Object> IndividualMap = new HashMap<>();
	Map<String, Object> teamMap = null;
	Individual individualObj;
	Team teamObj;

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws YamlException
	 */
	@SuppressWarnings("unchecked")
	public List<Individual> getListOfIndividuals() throws FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		YamlReader reader = new YamlReader(
				new FileReader("D:\\MyWorkspace\\assignment-resource-io-yml\\src\\main\\resources\\db.yaml"));
		Object object = reader.read();
		List<Individual> listOfIndividuals = new ArrayList<>();
		HashMap<String, Object> fullMap = (HashMap<String, Object>) object;
		List<Individual> s = new ArrayList<>();
		s = (List<Individual>) fullMap.get("individuals");

		for (int i = 0; i < s.size(); i++) {
			IndividualMap = (Map<String, Object>) s.get(i);
			individualObj = new Individual(IndividualMap);
			listOfIndividuals.add(individualObj);
		}
		return listOfIndividuals;
	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws YamlException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualById(Integer id)
			throws ObjectNotFoundException, FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		boolean flag=false;
		List<Individual> listOfIndividuals1 = getListOfIndividuals();
		for (int i = 0; i < listOfIndividuals1.size(); i++) {
			while(listOfIndividuals1.get(i).getId().intValue() == id.intValue()) {
				flag=true;
				return listOfIndividuals1.get(i);
			} 
		}
		if(!flag){
			throw new ObjectNotFoundException(Individual.class.getSimpleName(), "id", id.toString());	
		}
		return null;

	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 * @throws YamlException
	 * @throws FileNotFoundException
	 */
	public Individual getIndividualByName(String name)
			throws ObjectNotFoundException, FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		boolean flag = false;
		List<Individual>listOfIndividuals2 = getListOfIndividuals();
		for (int i = 0; i < listOfIndividuals2.size(); i++) {
			while(listOfIndividuals2.get(i).getName().equals(name)) {
				flag = true;
				return listOfIndividuals2.get(i);
			}
		}
		if(!flag){
		throw new ObjectNotFoundException(Individual.class.getSimpleName(), "Name", name);
	}
		return null;
	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 * @throws YamlException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfInactiveIndividuals() throws FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual>listOfIndividuals3 = getListOfIndividuals();
		listOfInactiveIndividuals = new ArrayList<>();
		for (int i = 0; i < listOfIndividuals3.size(); i++) {
			if (listOfIndividuals3.get(i).isActive() == false)
				listOfInactiveIndividuals.add(listOfIndividuals3.get(i));
		}
		return listOfInactiveIndividuals;

	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 * @throws YamlException
	 * @throws FileNotFoundException
	 */
	public List<Individual> getListOfActiveIndividuals() throws FileNotFoundException, YamlException {
		List<Individual>listOfIndividuals4 = getListOfIndividuals();
		listOfActiveIndividuals = new ArrayList<>();
		for (int i = 0; i < listOfIndividuals4.size(); i++) {
			if (listOfIndividuals4.get(i).isActive() == true)
				listOfActiveIndividuals.add(listOfIndividuals4.get(i));
		}
		return listOfActiveIndividuals;

	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws YamlException
	 */
	public List<Team> getListOfTeams() throws FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		YamlReader reader = new YamlReader(
				new FileReader("D:\\MyWorkspace\\assignment-resource-io-yml\\src\\main\\resources\\db.yaml"));
		Object object = reader.read();
		List<Team> listOfTeam = new ArrayList<>();
		HashMap<String, Object> fullMap = (HashMap<String, Object>) object;
		List<Team> teamList = new ArrayList<>();
		teamList = (List<Team>) fullMap.get("teams");
		for (int a = 0; a < teamList.size(); a++) {
			teamMap = (Map<String, Object>) teamList.get(a);

			teamObj = new Team(teamMap);

			listOfTeam.add(teamObj);

		}
		return listOfTeam;
	}
}
