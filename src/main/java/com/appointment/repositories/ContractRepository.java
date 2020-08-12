package com.appointment.repositories;

import com.appointment.entity.Contract;
import com.appointment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    List<Contract> findAll();


    Contract findByStudent(Student student);
}
