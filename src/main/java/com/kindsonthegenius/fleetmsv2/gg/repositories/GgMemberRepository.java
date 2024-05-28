package com.kindsonthegenius.fleetmsv2.gg.repositories;


import com.kindsonthegenius.fleetmsv2.gg.models.GgMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GgMemberRepository extends JpaRepository<GgMember, Integer> {
}
