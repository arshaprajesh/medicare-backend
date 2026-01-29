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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    // static cache map
    public static Map<String, List<Doctor>> doctorcache = new HashMap<>();
     
    
    @PostConstruct
    public void loadDoctorCache() { 


        //get datas from database
        List<Doctor> doctorList = doctorRepo.findAll();
        System.out.println("doctorList: "+ doctorList.toString());
        
        //get types/iterate the types and add in set
        Set<String> types = new HashSet<>();
        for(Doctor doctor : doctorList){
            types.add(doctor.getType());

        }

       
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
        
        }
       
        
        System.out.println("doctorcache: "+doctorcache);
        
        

    }

    // Return only types 
    public Set<String> getAllTypes() { 
        System.out.println("type object :"+doctorcache.keySet());
        return doctorcache.keySet();
        
 
    }

    // Return doctors for a given type 
    public List<Doctor> getDoctorsByType(String type) {
        System.out.println("doctor by type: "+doctorcache.get(type));

        //Map doctorcache = new HashMap<>();
       // doctorcache.get(type);

        return doctorcache.get(type);


        }

    //for printing text data
   @Override 
    public String toString() {
        StringBuilder sb = new StringBuilder("DoctorService Cache Summary:\n");
        for (Map.Entry<String, List<Doctor>> entry : doctorcache.entrySet()) {
            sb.append("Type: ").append(entry.getKey())
            .append(" | Count: ").append(entry.getValue().size())
            .append("\n");
        }
        return sb.toString();
    }

    
}

