package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.CitizenPlan;
import java.util.List;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
    
    @Query("select distinct c.planName from CitizenPlan c")
    List<String> getPlanNames();

    @Query("select distinct c.planStatus from CitizenPlan c")
    List<String> getPlanStatus();
}
