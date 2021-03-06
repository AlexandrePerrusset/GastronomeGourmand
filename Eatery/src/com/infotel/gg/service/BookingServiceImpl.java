
package com.infotel.gg.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotel.gg.DTO.BookingDTO;
import com.infotel.gg.DTO.BookingReportDTO;
import com.infotel.gg.dao.BookingDAO;
import com.infotel.gg.dao.BookingReportDAO;
import com.infotel.gg.dao.CustomerDAO;
import com.infotel.gg.dao.EateryDAO;
import com.infotel.gg.dao.EateryManagerDAO;
import com.infotel.gg.dao.ReviewDAO;
import com.infotel.gg.exception.GGourmandException;
import com.infotel.gg.model.Booking;
import com.infotel.gg.model.BookingReport;
import com.infotel.gg.model.Review;


@Service("BookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingDAO bookingDao;
	
	@Autowired
	private BookingReportDAO bookingReportDao;
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Autowired @Qualifier("hibernate")
	private EateryDAO eateryDAO;

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private EateryManagerDAO eMDAO;
	
	private final static Logger log = LogManager.getLogger(BookingServiceImpl.class);

	
	@Override
	public void saveBooking(BookingDTO booking) throws GGourmandException {
		Booking b = new Booking(booking.getDateTime(), Integer.parseInt(booking.getNumberOfPeople()), eateryDAO.read(booking.getEateryId()), customerDAO.read(booking.getCustomerId()));
		
		try {
			bookingDao.create(b);	
		}
		catch(Exception e){
			throw new GGourmandException();
		}
	}
	
	@Override
	public void saveBookingRest(BookingDTO booking) throws GGourmandException {
		Booking b = new Booking(Calendar.getInstance(), 2, eateryDAO.read(booking.getEateryId()), customerDAO.read(booking.getCustomerId()));
		
		try {
			bookingDao.create(b);	
		}
		catch(Exception e){
			throw new GGourmandException();
		}
	}
	

	
	@Override
	public Booking findBookingById(int id) throws GGourmandException {
		return bookingDao.read(id);
	}

	
	@Override
	public List<BookingDTO> findBookingsByCustomer(String username) throws GGourmandException {
		List<BookingDTO> result = new ArrayList<BookingDTO>();
		List<Booking> listBooking = bookingDao.findByCustomer(username);
		listBooking.stream().forEach(b -> result.add(transform(b)));
		return result;
	}

	
	@Override
	public List<BookingDTO> findBookingsByEatery(int eateryId) throws GGourmandException {
		List<BookingDTO> result = new ArrayList<BookingDTO>();
		List<Booking> listBooking = bookingDao.findByEateryId(eateryId);
		listBooking.stream().forEach(b -> result.add(transform(b)));
		return result;
	}
	
	public List<BookingDTO> findBookingsByEateryWithoutReport(int eateryId) throws GGourmandException {
		List<BookingDTO> result = new ArrayList<BookingDTO>();
		List<Booking> listBooking = bookingDao.findByEateryIdWithoutReport(eateryId);
		listBooking.stream().forEach(b -> result.add(transform(b)));
		return result;
	}

	
	@Override
	public void saveBookingReport(BookingReportDTO bookingReport) throws GGourmandException {
		Calendar c = Calendar.getInstance();
		BookingReport br = new BookingReport(c, bookingReport.isFulfilled(),bookingReport.getComment(), bookingReport.getTakingAmount(), bookingReport.getTakingAmount()*5/100, eMDAO.read(bookingReport.geteMId()), bookingDao.read(bookingReport.getBookingId()) );
		try {
			bookingReportDao.create(br);
		}
		catch(Exception e){
			throw new GGourmandException();
		}
	}
	
	public List<BookingReportDTO> findBookingReportByManager(String managerName) {
		List<BookingReportDTO> reportsDto = new ArrayList<BookingReportDTO>();
		List<BookingReport> reports = bookingReportDao.listBookReportByManager(managerName);
		reports.stream().forEach(r -> reportsDto.add(transformReport(r)));
		return reportsDto;
	}
	
	private BookingReportDTO transformReport(BookingReport bookingReport){
		BookingReportDTO bookingReportDto = new BookingReportDTO();
		bookingReportDto.setBookingId(bookingReport.getId());
		bookingReportDto.setComment(bookingReport.getComment());
		bookingReportDto.setDate(bookingReport.getDate());
		bookingReportDto.setTakingAmount(bookingReport.getTakingAmount());
		bookingReportDto.setDueAmount(bookingReport.getDueAmount());
		bookingReportDto.setFulfilled(bookingReport.isFulfilled());
		
		return bookingReportDto;
	}
	
	private BookingDTO transform(Booking booking) {
		BookingDTO bookingDto = new BookingDTO();
		bookingDto.setId(Integer.toString(booking.getId()));
		bookingDto.setDateTime(booking.getDateTime());	
		bookingDto.setNumberOfPeople(Integer.toString(booking.getNbOfCustomer()));	
		bookingDto.setFirstName(booking.getCustomer().getFirstName());
		bookingDto.setLastName(booking.getCustomer().getLastName());
		bookingDto.setCustomerId(booking.getCustomer().getUsername());
		bookingDto.setEateryId(booking.getEatery().getId());
		bookingDto.setEateryName(booking.getEatery().getName());
		bookingDto.setPostcode(booking.getEatery().getAddress().getPostCode());
		bookingDto.setStreet(booking.getEatery().getAddress().getStreet());
		bookingDto.setCityName(booking.getEatery().getAddress().getCity().getName());
		Review bookreview= reviewDAO.findByBookingId(booking.getId());
		if(bookreview==null){
			bookingDto.setComment(false);
		}
		else{
			bookingDto.setComment(true);
			bookingDto.setRating(bookreview.getRating());
			bookingDto.setCommenttext(bookreview.getComment());
		}
		
		return bookingDto;
	}

}
