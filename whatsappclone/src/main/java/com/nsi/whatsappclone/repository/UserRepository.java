package com.nsi.whatsappclone.repository;

import com.nsi.whatsappclone.model.User;
import com.nsi.whatsappclone.utils.UserConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : dim. 19 janvier 2025 4:03 p.m.
 **/
public interface UserRepository extends JpaRepository<User, String> {
    @Query(name = UserConstants.FIND_USER_BY_EMAIL)
    Optional<User> findByEmail(@RequestParam("email") String email);

    @Query(name = UserConstants.FIND_USER_BY_PUBLIC_ID)
    Optional<User> findByPublicId(@Param("userId") String senderId);

    @Query(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF)
    List<User> findAllUserByExceptSelf(@Param("publicId") String name);
}
