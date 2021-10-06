package com.example.test3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="results")
public class Result {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "result_id")
	private Long id;
	//
	@Column(name = "averageKL")
	private Double averageKL;

	@Column(name = "averageKR")
	private Double averageKR;

	@Column(name = "averageKLKR")
	private Double averageKLKR;

	@Column(name = "shift_deg")
	private Double shiftDegree;

	@Column(name = "shift_mm")
	private Integer shiftMm;

	@Column(name = "tan_alfa")
	private Double tanAlfa;

	@Column(name = "dist_to_sec")
	private Integer distanceToSec;

	@Column(name = "delta_dis")
	private Integer distanceDelta;

	@Column(name = "beta_average")
	private Double betaAverage;

	@Column(name = "beta_i")
	private Double betaI;

	@Column(name = "delta_beta")
	private Double betaDelta;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "result_building_id")
	@JsonIgnore
	private Building building;

	/*@ManyToOne
	@JoinColumn(name = "result_section_id")
	private Section section;

	@OneToOne
	@JoinColumn(name = "result_measure_id")
	private Measurement measurement;*/
	
	public Result() {

	}

	public Result(Long id, double averageKL, double averageKR, double averageKLKR, double shiftDegree,
			int shiftMm, double tanAlfa, int distanceToSec, int distanceDelta, double betaAverage, double betaI,
			double betaDelta, Building building) {
		this.id = id;
		this.averageKL = averageKL;
		this.averageKR = averageKR;
		this.averageKLKR = averageKLKR;
		this.shiftDegree = shiftDegree;
		this.shiftMm = shiftMm;
		this.tanAlfa = tanAlfa;
		this.distanceToSec = distanceToSec;
		this.distanceDelta = distanceDelta;
		this.betaAverage = betaAverage;
		this.betaI = betaI;
		this.betaDelta = betaDelta;
		this.building = building;
		//this.section = section;
		//this.measurement = measurement;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAverageKL() {
		return averageKL;
	}

	public void setAverageKL(Double averageKL) {
		this.averageKL = averageKL;
	}

	public Double getAverageKR() {
		return averageKR;
	}

	public void setAverageKR(Double averageKR) {
		this.averageKR = averageKR;
	}

	public Double getAverageKLKR() {
		return averageKLKR;
	}

	public void setAverageKLKR(Double averageKLKR) {
		this.averageKLKR = averageKLKR;
	}

	public Double getShiftDegree() {
		return shiftDegree;
	}

	public void setShiftDegree(Double shiftDegree) {
		this.shiftDegree = shiftDegree;
	}

	public Integer getShiftMm() {
		return shiftMm;
	}

	public void setShiftMm(Integer shiftMm) {
		this.shiftMm = shiftMm;
	}

	public Double getTanAlfa() {
		return tanAlfa;
	}

	public void setTanAlfa(Double tanAlfa) {
		this.tanAlfa = tanAlfa;
	}

	public Integer getDistanceToSec() {
		return distanceToSec;
	}

	public void setDistanceToSec(Integer distanceToSec) {
		this.distanceToSec = distanceToSec;
	}

	public Integer getDistanceDelta() {
		return distanceDelta;
	}

	public void setDistanceDelta(Integer distanceDelta) {
		this.distanceDelta = distanceDelta;
	}

	public Double getBetaAverage() {
		return betaAverage;
	}

	public void setBetaAverage(Double betaAverage) {
		this.betaAverage = betaAverage;
	}

	public Double getBetaI() {
		return betaI;
	}

	public void setBetaI(Double betaI) {
		this.betaI = betaI;
	}

	public Double getBetaDelta() {
		return betaDelta;
	}

	public void setBetaDelta(Double betaDelta) {
		this.betaDelta = betaDelta;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuildingID(Building building) {
		this.building = building;
	}

	/*public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}*/
	
	
	
	
}
