package com.passwordmanager.data.repository;
import com.passwordmanager.data.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface createTables extends JpaRepository<Password, Integer> {

    @Modifying
    @Query( value = "create table user (id uuid primary key, name varchar(255), username varchar(255), " +
            "hashed_Password varchar(255), profilePictureUrl varchar(255))", nativeQuery = true)
    void createUserTable();

    @Modifying
    @Query(value = "create table password (id uuid primary key, user_id uuid, username varchar(255), " +
            "password varchar(255), url varchar(255), website varchar(255))", nativeQuery = true)
    void createPasswordTable();

    @Modifying
    @Query( value = "create table user_roles (user_id uuid primary key, roles varchar(255))", nativeQuery = true)
    void createRoleTable();

    @Modifying
    @Query (value = "alter table password add foreign key (user_id) references user(id)", nativeQuery = true)
    void addPasswordForeignKey();
}




