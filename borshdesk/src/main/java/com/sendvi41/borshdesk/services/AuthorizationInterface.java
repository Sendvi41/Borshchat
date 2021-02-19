package com.sendvi41.borshdesk.services;

import com.sendvi41.borshdesk.dto.Consultant;

public interface AuthorizationInterface {

    Boolean checkLoginAndPassword(Consultant consultant);

    Consultant getConsultant(Consultant consultant);
}
