 package com.example.test3.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


 @Entity
@Table(name="buildings") //javax persistance
public class Building {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name="building_id", length = 11, nullable = false)
	private Long id;

	@Column(name="building_objname", length = 45, nullable = false)
	private String name;

	@Column(name="building_address", length = 120, nullable = false)
	private String address;

	 public Set<Section> getSections() {
		 return sections;
	 }

	 public void setSections(Set<Section> sections) {
		 this.sections = sections;
	 }

	 public Set<Measurement> getMeasurements() {
		 return measurements;
	 }

	 public void setMeasurements(Set<Measurement> measurements) {
		 this.measurements = measurements;
	 }

	 public Set<Result> getResults() {
		 return results;
	 }

	 public void setResults(Set<Result> results) {
		 this.results = results;
	 }

	 @Column(name="building_type", length = 45, nullable = false)
	private String type;

	@Column(name="building_config", length = 11, nullable = false)
	private int config;

	@Column(name="building_numberofsecs", length = 11, nullable = false)
	private int numberOfSections;

	@Column(name="building_height", length = 11, nullable = false)
	private int height;

	@Column(name="building_startlev", length = 11, nullable = false)
	private int startLevel;

	@Column(name="username", length = 45, nullable = false)
	private String userName;

	 @Column(name="registration", columnDefinition = "DATE", nullable = false)
	 private Date registerDate;

	@Column(name="organization", length = 45, nullable = false)
	private String organization;

	@OneToMany(mappedBy = "building")
	private Set<Section> sections;

	@OneToMany(mappedBy = "building")
	private Set<Measurement> measurements;

	 @OneToMany(mappedBy = "building")
	 private Set<Result> results;
	
	public Building() {
		
	}

	public Building(long id, String name, String address, String type, int config, int numberOfSections,
			int height, int startLevel, String userName, String organization, Date registerDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.type = type;
		this.config = config;
		this.numberOfSections = numberOfSections;
		this.height = height;
		this.startLevel = startLevel;
		this.userName = userName;
		this.organization = organization;
		this.registerDate = registerDate;
	}
	
	
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	 public String getOrganization() {
		 return organization;
	 }

	 public void setOrganization(String organization) {
		 this.organization = organization;
	 }

	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getConfig() {
		return config;
	}

	public void setConfig(int config) {
		this.config = config;
	}

	public int getNumberOfSections() {
		return numberOfSections;
	}

	public void setNumberOfSections(int numberOfSections) {
		this.numberOfSections = numberOfSections;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}
	
	/*public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public ArrayList<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(ArrayList<Measurement> measurements) {
		this.measurements = measurements;
	}


	public ArrayList<Result> getResults() {
		return results;
	}

	public void setResults(ArrayList<Result> results) {
		this.results = results;
	}

	public void addSection(Section section) {
		sections.add(section);
	}

	public Section getSection(int secNumber) {
		return sections.get(secNumber - 1);
	}

	public void addMeasurement(Measurement meas) {
		measurements.add(meas);
	}
	*/


	/*public Measurement getMeasurement(int sectionNumber, int side, BaseOrTop baseOrTop) {
		//Section section = 
		for(Measurement meas : measurements) {
			if(meas.getSectionNumber() == sectionNumber) {
				if(meas.getSide() == side && meas.getBaseOrTop().equals(baseOrTop)) {
					return meas;
				}
			}
		}
		
		return null;
	}*/
	
	/*public Result getResult(int measureId) {
		for(Result result : results) {
			if(result.getMeasureID() == measureId) return result;
		}
		return null;
	}*/

	/*public void addResultData(Result currentResult) {
		// TODO Auto-generated method stub
		results.add(currentResult);
	}*/
}
