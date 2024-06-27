package com.qa.lbg.repos;

import com.qa.lbg.domain.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepo extends JpaRepository<Toy, Integer> {
}
