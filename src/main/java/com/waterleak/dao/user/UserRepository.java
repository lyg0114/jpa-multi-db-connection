package com.waterleak.dao.user;

import com.waterleak.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak.waterleak.dao
 * @since : 2022/11/12
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
