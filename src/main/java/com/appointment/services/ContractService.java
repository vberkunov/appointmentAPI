package com.appointment.services;

import com.appointment.entity.Contract;
import javassist.NotFoundException;

import java.util.List;


public interface ContractService {
    List<Contract> getAllContracts();

    void approve(Long id) throws NotFoundException;
    void decline(Long id) throws NotFoundException;
}
