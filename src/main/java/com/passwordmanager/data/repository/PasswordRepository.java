

package com.passwordmanager.data.repository;

import com.passwordmanager.data.entity.Password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Integer> {

    @Query("select c from Password c " +
            "where lower(c.url) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.website) like lower(concat('%', :searchTerm, '%'))")
    List<Password> search(@Param("searchTerm") String searchTerm);
}