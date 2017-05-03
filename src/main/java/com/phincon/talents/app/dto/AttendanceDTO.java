package com.phincon.talents.app.dto;

public class AttendanceDTO {
	private String punchInUserTime;
	private String punchInNote;
	private String punchOutUserTime;
	private String punchOutNote;
	private String longitudePunchIn;
	private String latitudePunchIn;
	private String longitudePunchOut;
	private String latitudePunchOut;
	private String mode;

	public String getPunchInUserTime() {
		return punchInUserTime;
	}

	public void setPunchInUserTime(String punchInUserTime) {
		this.punchInUserTime = punchInUserTime;
	}

	public String getPunchInNote() {
		return punchInNote;
	}

	public void setPunchInNote(String punchInNote) {
		this.punchInNote = punchInNote;
	}

	public String getPunchOutUserTime() {
		return punchOutUserTime;
	}

	public void setPunchOutUserTime(String punchOutUserTime) {
		this.punchOutUserTime = punchOutUserTime;
	}

	public String getPunchOutNote() {
		return punchOutNote;
	}

	public void setPunchOutNote(String punchOutNote) {
		this.punchOutNote = punchOutNote;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}



}
