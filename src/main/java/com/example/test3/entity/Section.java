package com.example.test3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sections")
public class Section {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "section_id")
	private Long id;
	
	@Column(name = "section_number")
	private int number;

	@Column(name = "section_widthbottom")
	private int widthBottom;

	@Column(name = "section_widthtop")
	private int widthTop;

	@Column(name = "section_height")
	private int height;

	@Column(name = "section_level")
	private int level;

	@Column(name = "section_objname")
	private String name;

	@ManyToOne
	@JoinColumn(name = "section_building_id")
	@JsonIgnore
	private Building building;
	//private int building_id;

	/*@OneToMany(mappedBy = "section")
	private Set<Measurement> measurements;*/

	
	public Section() {
		
	}

	public Section(
			Long id, int number, int widthBottom, int widthTop, int height, int level, String name, Building building) {
		//super();
		this.id = id;
		this.number = number;
		this.widthBottom = widthBottom;
		this.widthTop = widthTop;
		this.height = height;
		this.level = level;
		this.name = name;
		//this.building_id = building_id;
		this.building = building;
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

	public int getWidthBottom() {
		return widthBottom;
	}

	public void setWidthBottom(int widthBottom) {
		this.widthBottom = widthBottom;
	}

	public int getWidthTop() {
		return widthTop;
	}

	public void setWidthTop(int widthTop) {
		this.widthTop = widthTop;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
