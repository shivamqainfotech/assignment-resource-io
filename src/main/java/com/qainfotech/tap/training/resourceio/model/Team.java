package com.qainfotech.tap.training.resourceio.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlException;
import com.qainfotech.tap.training.resourceio.TeamsJsonReader;
import com.qainfotech.tap.training.resourceio.TeamsYamlReader;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;
	TeamsYamlReader rdr=new TeamsYamlReader(); 
	public Team(Map<String, Object> teamMap) throws FileNotFoundException, YamlException {
		// throw new UnsupportedOperationException("Not implemented.");
		this.name = teamMap.get("name").toString();
		this.id = Integer.parseInt(teamMap.get("id").toString());
		this.members=new ArrayList<>();
    	
    	List<Individual>arrayOfIndividuals=rdr.getListOfIndividuals();
    	     	List memberArray=(List) teamMap.get("members");
    	     	Iterator<Individual> itr=arrayOfIndividuals.iterator();
    	     	while(itr.hasNext()){
    	     		Individual individual=itr.next();
    	     		for(int i=0;i<memberArray.size();i++){
    	     			if(individual.getId()==Integer.parseInt(memberArray.get(i).toString())){
    	     				this.members.add(individual);
    	     			}
    	     		}
    	     	}
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", id=" + id + ", members=" + members + "]";
	}

	/**
	 * get team name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 * 
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 * 
	 * @return
	 */
	public List<Individual> getActiveMembers() {
		List<Individual> ListActiveMembers = new ArrayList<>();
		List<Individual> allMembers = new ArrayList<>();
		allMembers=members;
		for(int k=0;k<members.size();k++){
			
			if(allMembers.get(k).isActive()){
				//System.out.println(listOfActiveMembers.get(k));
				ListActiveMembers.add(allMembers.get(k));
				
			}
			
		}
		return ListActiveMembers;
		// throw new UnsupportedOperationException("Not implemented.");
	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 * 
	 * @return
	 */
	public List<Individual> getInactiveMembers() {
		//throw new UnsupportedOperationException("Not implemented.");
		List<Individual> ListInactiveMembers = new ArrayList<>();
		List<Individual> allMembers = new ArrayList<>();
		allMembers=members;
		for(int k=0;k<members.size();k++){
			
			if(!(allMembers.get(k).isActive())){
				//System.out.println(listOfActiveMembers.get(k));
				ListInactiveMembers.add(allMembers.get(k));
				
			}
			
		}
		return ListInactiveMembers;
	}
}
