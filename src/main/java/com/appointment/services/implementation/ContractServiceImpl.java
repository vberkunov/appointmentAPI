package com.appointment.services.implementation;

import com.appointment.entity.Contract;
import com.appointment.entity.Student;
import com.appointment.repositories.ContractRepository;
import com.appointment.repositories.StudentRepository;
import com.appointment.services.ContractService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final StudentRepository studentRepository;

    public ContractServiceImpl(ContractRepository contractRepository, StudentRepository studentRepository) {
        this.contractRepository = contractRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public void approve(Long id) throws NotFoundException {
        Student student = studentRepository.findById(id).orElse(null);

        if(student != null) {
            Contract contract = contractRepository.findByStudent(student);
            contract.setConfirmation(true);
        }
        else throw new NotFoundException("student not found!");


    }

    @Override
    public void decline(Long id) throws NotFoundException {

        Student student = studentRepository.findById(id).orElse(null);

        if(student != null) {
            Contract contract = contractRepository.findByStudent(student);
            contract.setConfirmation(false);
        }
        else throw new NotFoundException("student not found!");

    }

}
