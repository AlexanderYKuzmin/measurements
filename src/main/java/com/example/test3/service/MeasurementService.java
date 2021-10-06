package com.example.test3.service;

import com.example.test3.entity.Building;
import com.example.test3.entity.Measurement;
import com.example.test3.entity.Result;
import com.example.test3.entity.Section;

import java.util.List;

public interface MeasurementService {
    List<Building> getBuildingList();
    List<Section> geSectionList();
    List<Measurement> getMeasurementList();
    List<Result> getResultList();
    Building getBuilding(Long id);
    void addBuilding(Building building);
    void deleteBuilding(Long id);
    Building updateBuilding(Building building);
}
