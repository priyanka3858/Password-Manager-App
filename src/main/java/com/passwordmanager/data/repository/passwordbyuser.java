package com.passwordmanager.data.repository;

import com.passwordmanager.data.entity.Password;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface passwordbyuser {
    @Query("select user.email, user.username, password.website, password.password from user " +
            "inner join password on user.id = password.userId; ")
    List<Password> search(@Param("searchTerm") String searchTerm);
}



