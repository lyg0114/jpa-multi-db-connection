package com.waterleak.dao.reporting;

import com.waterleak.model.reporting.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
