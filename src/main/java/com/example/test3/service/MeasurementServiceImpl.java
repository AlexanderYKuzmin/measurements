package com.example.test3.service;

import com.example.test3.entity.Building;
import com.example.test3.entity.Measurement;
import com.example.test3.entity.Result;
import com.example.test3.entity.Section;
import com.example.test3.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private BuildingRepository repository;

    @Override
    public List<Building> getBuildingList() {
        List<Building> buildingList = repository.findAll();
        System.out.println(buildingList.get(0).getRegisterDate().getClass().getName());
        Date sqlDate = buildingList.get(0).getRegisterDate();
        System.out.println(sqlDate.toString());
        return buildingList;
    }

    @Override
    public List<Section> geSectionList() {
        return null;
    }

    @Override
    public List<Measurement> getMeasurementList() {
        return null;
    }

    @Override
    public List<Result> getResultList() {
        return null;
    }

    @Override
    public Building getBuilding(Long id) {
        Optional<Building> optionalBuilding = repository.findById(id);
        return optionalBuilding.get();
    }

    @Override
    public void addBuilding(Building building) {

    }

    @Override
    public void deleteBuilding(Long id) {

    }

    @Override
    public Building updateBuilding(Building building) {
        return repository.saveAndFlush(building);
    }
}