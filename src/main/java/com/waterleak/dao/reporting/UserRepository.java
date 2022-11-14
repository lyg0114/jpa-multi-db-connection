package com.waterleak.dao.reporting;

import com.waterleak.model.reporting.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : iyeong-gyo
 * @package : com.waterleak.waterleak.dao
 * @since : 2022/11/12
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
