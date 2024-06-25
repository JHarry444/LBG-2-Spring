package com.qa.lbg.repos;

import com.qa.lbg.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepo extends JpaRepository<Cat, Integer> {
}
