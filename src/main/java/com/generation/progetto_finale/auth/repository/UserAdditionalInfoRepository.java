package com.generation.progetto_finale.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.progetto_finale.auth.model.UserAdditionalInfo;

public interface UserAdditionalInfoRepository extends JpaRepository<UserAdditionalInfo, Integer>
{
    UserAdditionalInfo findByUserId(Integer userId);
}
