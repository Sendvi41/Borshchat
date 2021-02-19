package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

public interface ConsultServiceInterface {

    public boolean validate(Consultant consultant);

    public Consultant getConsultant(Consultant consultant) throws ServiceResourceNotFoundException;
}
