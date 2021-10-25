package com.springmsa.deathstar.services;

import com.springmsa.deathstar.dao.BookingDao;
import com.springmsa.deathstar.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class DateValidator {
//    @Autowired
//    BookingDao bookingDao;
//
//    public boolean checkIfDatesOverlap(LocalDate startDate, LocalDate endDate) {
//        List<Booking> bookingList = bookingDao.findAll();

//        for (Booking booking : bookingList) {
//            if (startDate.isBefore(booking.getStartDate()) &&
//                    (endDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())) &&
//                    (startDate.isAfter(booking.getStartDate()) && endDate.isBefore(booking.getEndDate())) &&
//                    (startDate.isAfter(booking.getStartDate()) && endDate.isAfter(booking.getEndDate())) &&
//                    (startDate.isBefore(booking.getEndDate()) && endDate.isAfter(booking.getEndDate()))
//            )
//            {
//                return true;
//            }
//        }
//
//        for (Booking booking : bookingList) {
//
//        }

//
//        return false;
//    }

}
