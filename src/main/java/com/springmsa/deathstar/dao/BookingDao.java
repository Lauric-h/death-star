package com.springmsa.deathstar.dao;

import com.springmsa.deathstar.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {
    Booking findById(int id);
    List<Booking> findAllByEndDateIsAfterAndStartDateIsBefore(LocalDate startDate, LocalDate endDate);
}
