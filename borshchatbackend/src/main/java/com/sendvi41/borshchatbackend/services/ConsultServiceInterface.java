package com.sendvi41.borshchatbackend.services;

import com.sendvi41.borshchatbackend.entities.Consultant;
import com.sendvi41.borshchatbackend.exceptions.ServiceResourceNotFoundException;

public interface ConsultServiceInterface {

    boolean validate(Consultant consultant);

    Consultant getConsultant(Consultant consultant) throws ServiceResourceNotFoundException;
}
