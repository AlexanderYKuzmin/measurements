package com.example.test3.model;

import com.example.test3.entity.Building;
import com.example.test3.entity.Measurement;
import com.example.test3.entity.Result;
import com.example.test3.entity.Section;

import java.util.ArrayList;
import java.util.List;

public class BuildingModel {
    private Building building;
    private List<Section> sections;
    private List<Measurement> measurements;
    private List<Result> results;

    public BuildingModel(
            Building building, List<Section> sections, List<Measurement> measurements, List<Result> results) {
        this.building = building;
        this.measurements = measurements;
        this.sections = sections;
        this.results = results;
    }

    public BuildingModel(Building building, List<Section> sections) {
        this.building = building;
        this.sections = sections;
        measurements = new ArrayList<>();
        results = new ArrayList<>();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
