package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_attendance")
public class Attendance extends AbstractEntity {

	@Temporal(TemporalType.DATE)
	@Column(name = "today")
	private Date today;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "punch_in_utc_time")
	private Date punchInUtcTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "punch_in_user_time")
	private Date punchInUserTime;

	@Column(name = "punch_in_note", length = 100)
	private String punchInNote;

	@Column(name = "punch_in_time_offset")
	private Integer punchInTimeOffset = 0;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "punch_out_utc_time")
	private Date punchOutUtcTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "punch_out_user_time")
	private Date punchOutUserTime;

	@Column(name = "punch_Out_note", length = 100)
	private String punchOutNote;

	@Column(name = "punch_out_time_offset")
	private Integer punchOutTimeOffset = 0;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
//	private Employee employee;
	
	@Column(name = "employee_id")
	private Long employee;


	@Column(name = "company_id")
	private Long company;

	@Column(name = "comment")
	private String comment;
	
	@Column(name = "longitude_punch_in", length=30)
	private String longitudePunchIn;
	
	@Column(name = "latitude_punch_in", length=30)
	private String latitudePunchIn;
	
	@Column(name = "longitude_punch_out", length=30)
	private String longitudePunchOut;
	
	@Column(name = "latitude_punch_out", length=30)
	private String latitudePunchOut;

	public Date getPunchInUtcTime() {
		return punchInUtcTime;
	}

	public void setPunchInUtcTime(Date punchInUtcTime) {
		this.punchInUtcTime = punchInUtcTime;
	}

	public Date getPunchInUserTime() {
		return punchInUserTime;
	}

	public void setPunchInUserTime(Date punchInUserTime) {
		this.punchInUserTime = punchInUserTime;
	}

	public String getPunchInNote() {
		return punchInNote;
	}

	public void setPunchInNote(String punchInNote) {
		this.punchInNote = punchInNote;
	}

	public Integer getPunchInTimeOffset() {
		return punchInTimeOffset;
	}

	public void setPunchInTimeOffset(Integer punchInTimeOffset) {
		this.punchInTimeOffset = punchInTimeOffset;
	}

	public Date getPunchOutUtcTime() {
		return punchOutUtcTime;
	}

	public void setPunchOutUtcTime(Date punchOutUtcTime) {
		this.punchOutUtcTime = punchOutUtcTime;
	}

	public Date getPunchOutUserTime() {
		return punchOutUserTime;
	}

	public void setPunchOutUserTime(Date punchOutUserTime) {
		this.punchOutUserTime = punchOutUserTime;
	}

	public String getPunchOutNote() {
		return punchOutNote;
	}

	public void setPunchOutNote(String punchOutNote) {
		this.punchOutNote = punchOutNote;
	}

	public Integer getPunchOutTimeOffset() {
		return punchOutTimeOffset;
	}

	public void setPunchOutTimeOffset(Integer punchOutTimeOffset) {
		this.punchOutTimeOffset = punchOutTimeOffset;
	}
	
	

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getToday() {
		return today;
	}

	public String getLongitudePunchIn() {
		return longitudePunchIn;
	}

	public void setLongitudePunchIn(String longitudePunchIn) {
		this.longitudePunchIn = longitudePunchIn;
	}

	public String getLatitudePunchIn() {
		return latitudePunchIn;
	}

	public void setLatitudePunchIn(String latitudePunchIn) {
		this.latitudePunchIn = latitudePunchIn;
	}

	public String getLongitudePunchOut() {
		return longitudePunchOut;
	}

	public void setLongitudePunchOut(String longitudePunchOut) {
		this.longitudePunchOut = longitudePunchOut;
	}

	public String getLatitudePunchOut() {
		return latitudePunchOut;
	}

	public void setLatitudePunchOut(String latitudePunchOut) {
		this.latitudePunchOut = latitudePunchOut;
	}

	public void setToday(Date today) {
		this.today = today;
	}


	

}
