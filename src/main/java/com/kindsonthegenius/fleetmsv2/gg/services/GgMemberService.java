package com.kindsonthegenius.fleetmsv2.gg.services;

import com.kindsonthegenius.fleetmsv2.gg.models.GgMember;
import com.kindsonthegenius.fleetmsv2.gg.models.GgMemeberStatus;
import com.kindsonthegenius.fleetmsv2.gg.repositories.GgMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GgMemberService {

    @Autowired
    private GgMemberRepository ggMemberRepository;

    // Method to save
    public void save(GgMember ggMember){

        ggMemberRepository.save(ggMember);

    }

    // Method to find all Good Governance Members
    public List<GgMember> findAll(){

        return ggMemberRepository.findAll();
    }
}
