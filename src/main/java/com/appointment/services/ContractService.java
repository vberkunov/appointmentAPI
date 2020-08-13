package com.appointment.services;

import com.appointment.entity.Contract;
import com.appointment.entity.Lesson;
import com.appointment.entity.Student;
import javassist.NotFoundException;

import java.util.List;


public interface ContractService {
    List<Contract> getAllContracts();

    void approve(Long id) throws NotFoundException;
    void decline(Long id) throws NotFoundException;

    void createContract(Student student, Lesson lesson);

    Contract findById(Long id);

    void delete(Contract contract);
}
