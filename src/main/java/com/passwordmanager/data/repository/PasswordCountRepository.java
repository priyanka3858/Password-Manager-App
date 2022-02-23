package com.passwordmanager.data.repository;
import com.passwordmanager.data.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PasswordCountRepository extends JpaRepository<Password, Integer> {
    @Query(value = "select user.id, user.email, user.username, Count(userId) " +
            "totalPassord from user inner join password on user.id = password.userId " +
            "group by user.id", nativeQuery = true)
    List<Password> getPasswordCount();

    @Query(value = "select user.id, user.email, user.username, Count(userId) totalPassord" +
            " from user inner join password on user.id = password.userId " +
            "where user.id = :userId group by user.id", nativeQuery = true)
    List<Password> getPasswordCountByUserId(@Param("userId") int userId);


    // find duplicate passwords (repeated used passwords by user)
    @Query(value = "select user.id, user.email, user.username, Count(userId) totalPassord" +
            " from user inner join password on user.id = password.userId " +
            "where user.id = :userId and password.password = :password group by user.id", nativeQuery = true)
    List<Password> getPasswordCountByUserIdAndPassword(@Param("userId") int userId, @Param("password") String password);

}

