package com.example.test3.controller;

import com.example.test3.entity.Building;
import com.example.test3.entity.Section;
import com.example.test3.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/test3")
public class MeasurementController {

    @Autowired
    private MeasurementService service;

    @GetMapping("/")
    public String index(Model model) {

        //model.addAttribute("buildings", service.getBuildingList());
        //System.out.println("id = " + service.getList().get(0).getId());
        return "index";
    }

    @GetMapping("/buildings")
    public ResponseEntity getBuildingList(Model model) {
        //model.addAttribute("buildings", service.getBuildingList());
        //System.out.println("id = " + service.getList().get(0).getId());
        List<Building> buildingList = service.getBuildingList();
        System.out.println(buildingList.get(0).getRegisterDate().toString());
        ResponseEntity responseEntity = new ResponseEntity(buildingList, HttpStatus.OK);
        System.out.println(responseEntity.getBody().toString());
        return buildingList != null
                ? new ResponseEntity(buildingList, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getBuildingById(@PathVariable(name = "id") Long id) {
        Building building = null;
        if(id != null && id > 0) {
            building = service.getBuilding(id);
        }
        return building != null
                ? new ResponseEntity(building, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Building> update(@PathVariable(name = "id") Long id, @RequestBody Building building) {
        if(building == null || id == null || id <= 0 || !checkBodyParameters(building)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(building.getName() + "; "
            + building.getAddress() + "; "
            + building.getRegisterDate() + "; "
            + building.getUserName()
        );
        building.getSections().forEach(section -> System.out.println(section.getNumber()));

        Building existingBuilding = service.getBuilding(id);
        if(existingBuilding == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(building.getName() != null) existingBuilding.setName(building.getName());
        if(building.getAddress() != null) existingBuilding.setAddress(building.getAddress());
        if(building.getUserName() != null) existingBuilding.setUserName(building.getUserName());
        if(building.getRegisterDate() != null) existingBuilding.setRegisterDate(building.getRegisterDate());
        if(building.getHeight() > 0) existingBuilding.setHeight(building.getHeight());
        if(building.getSections() != null) {
            Iterator<Section> itOldSections = existingBuilding.getSections().iterator();

            while (itOldSections.hasNext()) {
                Section oldSection = itOldSections.next();
                Iterator<Section> itNewSections = building.getSections().iterator();
                System.out.println(building.getSections().size());
                while (itNewSections.hasNext()) {
                    Section newSection = itNewSections.next();
                    if(oldSection.getNumber() == newSection.getNumber()) {
                        oldSection.setWidthBottom(newSection.getWidthBottom());
                        oldSection.setWidthTop(newSection.getWidthTop());
                        oldSection.setHeight(newSection.getHeight());
                        itNewSections.remove();
                        continue;
                    }
                }
            }
        }

        Building updatedBuilding = service.updateBuilding(existingBuilding);
        return updatedBuilding != null
                ? new ResponseEntity<>(updatedBuilding, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean checkBodyParameters(Building building) {
        String name = building.getName();
        if(name != null) {
            if(name.trim().isEmpty() || name.length() > 45) {
                return false;
            }
        }

        String address = building.getAddress();
        if(address != null) {
            if(address.isEmpty() || address.length() > 120) {
                return false;
            }
        }

        /*Date registerDate = building.getRegisterDate();
        if(registerDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthday);
            int year = calendar.get(Calendar.YEAR);
            if (year < 2000 || year > 3000 ) {
                return false;
            }
        }*/

        /*Integer exp = player.getExperience();
        if(exp != null) {
            if(exp < 0 || exp > 10000000) {
                return false;
            }
        }*/
        return true;
    }
}
