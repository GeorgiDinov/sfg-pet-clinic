package com.georgidinov.sfgpetclinic.services.springdatajpa;

import com.georgidinov.sfgpetclinic.model.Visit;
import com.georgidinov.sfgpetclinic.repositories.VisitRepository;
import com.georgidinov.sfgpetclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {


    //== fields ==
    private final VisitRepository visitRepository;


    //== constructors ==
    @Autowired
    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }//end of constructor


    //== public methods ==
    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        this.visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {
        return this.visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return this.visitRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        this.visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        this.visitRepository.deleteById(aLong);
    }

}//end of class VisitSDJpaService
