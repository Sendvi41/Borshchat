package com.sendvi41.borshchatbackend.services;


import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService {

    @Autowired
    ConsultantRepository consultantRepository;

    public boolean validate(Consultant consultant) {
        if(consultantRepository.validate(consultant.getName(),consultant.getPassword())!=null)
        {
            return true;
        }
        return false;
    }


}
