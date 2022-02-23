package com.passwordmanager.data.repository;
import com.passwordmanager.data.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InsertTableRepository extends JpaRepository<Password, Integer> {

    @Modifying
    @Query(value = "insert into user (name, username, hashed_Password, roles, profilePictureUrl) values " +
            "(:name, :username, :hashedPassword, :roles, :profilePictureUrl)", nativeQuery = true)
    void insertUser(@Param("name") String name, @Param("username") String username, @Param("hashedPassword")
            String hashedPassword, @Param("roles") String roles, @Param("profilePictureUrl") String profilePictureUrl);

    @Modifying
    @Query( value = "insert into password (user_id, username, password, url, website) " +
            "values (:user_id, :username, :password, :url, :website)", nativeQuery = true)
    void insertPassword(@Param("user_id") String user_id, @Param("username") String username, @Param("password")
            String password, @Param("url") String url);
}






