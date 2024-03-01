package com.sec.mspringsec.repository;

import com.sec.mspringsec.model.SecUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecUserRepository extends JpaRepository<SecUser,Integer> {

    List<SecUser> findByEmail(String email);
}
