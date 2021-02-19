package com.sendvi41.borshchatbackend.services;


import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;
import com.sendvi41.borshchatbackend.repositories.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultantService implements ConsultServiceInterface {

    @Autowired
    ConsultantRepository consultantRepository;

    @Transactional
    public boolean validate(Consultant consultant) {
        if (consultantRepository.validate(consultant.getName(), consultant.getPassword()) != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public Consultant getConsultant(Consultant consultant) throws ServiceResourceNotFoundException{
        if (consultantRepository.validate(consultant.getName(), consultant.getPassword()) != null) {
            return consultantRepository.validate(consultant.getName(), consultant.getPassword());
        }else{
            throw new ServiceResourceNotFoundException("Don't find user with fields" +
                    ""+consultant.getName()+ consultant.getPassword());
        }
    }

}
