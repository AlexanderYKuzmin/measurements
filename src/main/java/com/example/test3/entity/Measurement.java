package com.example.test3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="measures")
public class Measurement {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "measure_id")
	private Long id;
	//private String objName;

	@Column(name = "measure_date")
	private Date date;

	@Column(name = "measure_contractor")
	private String contractor;

	@Column(name = "measure_number")
	private int number;

	@Column(name = "measure_side")
	private int side;

	@Column(name = "measure_circle")
	private String circle;

	@Column(name = "measure_leftangle")
	private double leftAngle;

	@Column(name = "measure_rightangle")
	private double rightAngle;

	@Column(name = "measure_theoheight")
	private int theoHeight;

	@Column(name = "measure_distance")
	private int distance;

	@Column(name = "measure_secnum")
	private int sectionNumber;

	@Column(name = "measure_baseortop")
	private String baseOrTop;

	@Column(name = "measure_sideazimuth")
	private int azimuth;

	/*@ManyToOne
	@JoinColumn(name = "measure_section_id")
	@JsonIgnore
	private Section section;*/

	/*@Column(name = "measure_section_id")
	private Long sectionId;*/

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "measure_building_id")
	@JsonIgnore
	private Building building;


	public Measurement() {

	}

	public Measurement(Long id, int number, int side, String circle, double leftangle,
					   double rightangle, int theoHeight, int distance, int sectionNumber,
					   String baseOrTop, Building building, Date date,
					   String contractor, int azimuth) {
		super();
		this.id = id;
		//this.objName = name;
		this.number = number;
		this.side = side;
		this.circle = circle;
		this.leftAngle = leftangle;
		this.rightAngle = rightangle;
		this.theoHeight = theoHeight;
		this.distance = distance;
		this.sectionNumber = sectionNumber;
		this.baseOrTop = baseOrTop;
		//this.sectionId = sectionId;
		this.building = building;
		this.date = date;
		this.contractor = contractor;
		this.azimuth = azimuth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public double getLeftAngle() {
		return leftAngle;
	}

	public void setLeftAngle(double leftangle) {
		this.leftAngle = leftangle;
	}

	public double getRightAngle() {
		return rightAngle;
	}

	public void setRightAngle(double rightangle) {
		this.rightAngle = rightangle;
	}

	public int getTheoHeight() {
		return theoHeight;
	}

	public void setTheoHeight(int theoHeight) {
		this.theoHeight = theoHeight;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getBaseOrTop() {
		return baseOrTop;
	}

	public void setBaseOrTop(String baseOrTop) {
		this.baseOrTop = baseOrTop;
	}

	/*public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}*/

	public Building getBuildingId() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public int getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(int azimuth) {
		this.azimuth = azimuth;
	}
	
	
}
