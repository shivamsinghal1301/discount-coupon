package com.task.shivam.repository;

import com.task.shivam.entity.coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface couponRepository extends JpaRepository<coupon, Long> {
    Optional<coupon> findByCode(String code);
}
