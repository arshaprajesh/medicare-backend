package com.example.globalMedicare.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

import com.example.globalMedicare.model.Doctor;
import com.example.globalMedicare.repository.DoctorRepo;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {


    private static final Logger log = LoggerFactory.getLogger(DoctorService.class);
    @Autowired
    private DoctorRepo doctorRepo;

    // static cache map
    public static Map<String, List<Doctor>> doctorcache = new HashMap<>();


    @PostConstruct
    public void loadDoctorCache() {
        log.info("Loading doctor cache from database...");

        try {
            //get datas from database
            List<Doctor> doctorList = doctorRepo.findAll();
            log.info("Fetched {} doctors from DB", doctorList.size());
            System.out.println("doctorList: "+ doctorList.toString());

            //get types/iterate the types and add in set
            Set<String> types = new HashSet<>();
            for(Doctor doctor : doctorList){
                types.add(doctor.getType());

            }
            log.info("Doctor types found: {}", types);
            //convert set to string
            for(String type : types){
            System.out.println("type :"+type);

            List<Doctor> doctorListByType = new ArrayList<>();

            for(Doctor doctor : doctorList){
                if(doctor.getType().equals(type)){
                     doctorListByType.add(doctor);

                }
            }
                //finally add type and list of doctors in doctorcache
                doctorcache.put(type, doctorListByType);
                log.info("Cached {} doctors for type '{}'", doctorListByType.size(), type);
            }
            log.info("Doctor cache loaded successfully: {}", doctorcache);

        } catch (Exception e) {
            log.error("Error loading doctor cache: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }


    }

    // Return only types 
    public Set<String> getAllTypes() {
        log.info("Fetching all doctor types from cache");
        try {
            Set<String> types = doctorcache.keySet(); log.info("Doctor types returned: {}", types);
            System.out.println("type object :"+types);
            return types;
        } catch (Exception e) {
            log.error("Error fetching doctor types: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctor types");
        }


    }

    // Return doctors for a given type 
    public List<Doctor> getDoctorsByType(String type) {
        log.info("Fetching doctors for type '{}'", type);
        try {
            List<Doctor> doctors = doctorcache.get(type);
            if (doctors == null || doctors.isEmpty()) {
                log.warn("No doctors found for type '{}'", type);
            } else {
                log.info("Found {} doctors for type '{}'", doctors.size(), type);
            }
            return doctors;

        } catch (Exception e) {
            log.error("Error fetching doctors for type '{}': {}", type, e.getMessage(), e);
            throw new RuntimeException("Failed to fetch doctors by type");
        }


    }

    //for printing text data
   @Override 
    public String toString() {
       try {
           StringBuilder sb = new StringBuilder("DoctorService Cache Summary:\n");
           for (Map.Entry<String, List<Doctor>> entry : doctorcache.entrySet()) {
               sb.append("Type: ").append(entry.getKey())
               .append(" | Count: ").append(entry.getValue().size())
               .append("\n");
           }
           return sb.toString();
       } catch (Exception e) {
           log.error("Error generating cache summary: {}", e.getMessage(), e);
           throw new RuntimeException("Failed to generate cache summary");
       }
   }

    
}

