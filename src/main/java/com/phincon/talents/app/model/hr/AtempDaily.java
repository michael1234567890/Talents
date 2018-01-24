package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name="hr_atempdaily")
public class AtempDaily extends AbstractEntity {
	
	@Column(name = "accepted_ovt_in")
	private Double acceptedOvertimeIn;
	
	@Column(name = "accepted_ovt_out")
	private Double acceptedOvertimeOut;
	
	@Column(name = "accepted_ovt_fromday")
	private Double acceptedOvertimeOutFromDay;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_in_time")
	private Date actualInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_in_time_gmt")
	private Date actualInTimeGmt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_out_time")
	private Date actualOutTime;
	
	@Column(name = "card_no", length = 50)
	private String cardNo;
	
	@Column(name = "compare_field", length = 255)
	private String compareField;
	
	@Column(name = "day_type", length = 15)
	private String dayType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_in_time")
	private Date editInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_in_time_lpad")
	private Date editInTimeLpad;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_out_time")
	private Date editOutTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edit_out_time_lpad")
	private Date editOutTimeLpad;
	
	@Column(name = "employee_no", length = 50)
	private String employeeNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_ovt_limit")
	private Date endOvertimeLimit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_range_in")
	private Date endRangeIn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_range_out")
	private Date endRangeOut;
	
	@Column(name = "index_ovt_in")
	private Double indexOvertimeIn;
	
	@Column(name = "index_ovt_out")
	private Double indexOvertimeOut;
	
	@Column(name = "index_ovt_out_1")
	private Double indexOvertimeOut1;
	
	@Column(name = "index_ovt_out_2")
	private Double indexOvertimeOut2;
	
	@Column(name = "index_ovt_out_3")
	private Double indexOvertimeOut3;
	
	@Column(name = "index_ovt_out_4")
	private Double indexOvertimeOut4;
	
	@Column(name = "index_ovt_out_fromday")
	private Double indexOvertimeOutFromday;
	
	@Column(name = "index_ovt_out_fromday_formula")
	private Double indexOvertimeOutFromdayFormula;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "in_time")
	private Date inTime;
	
	@Column(name = "in_time_extra")
	private Integer inTimeExtra;
	
	@Column(name = "in_time_extra_formula")
	private Integer inTimeExtraFormula;
	
	@Column(name = "late_in_time")
	private Integer lateInTime;
	
	@Column(name = "location_in", length = 50)
	private String locationIn;
	
	@Column(name = "location_out", length = 50)
	private String locationOut;
	
	@Column(name = "machine_no", length = 50)
	private String machineNo;
	
	@Column(name = "machine_terminal", length = 50)
	private String machineTerminal;
	
	@Column(name = "meal_ovt")
	private Double mealOvertime;
	
	@Column(name = "meal_ovt_hd")
	private Double mealOvertimeHd;
	
	@Column(name = "mtswp")
	private Boolean MTSWP;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "out_end_time_min")
	private Date outEndTimeMin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "out_end_time_max")
	private Date outEndTimeMax;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "out_end")
	private Date outTime;
	
	@Column(name = "out_time_extra")
	private Integer outTimeExtra;
	
	@Column(name = "out_time_extra_formula")
	private Integer outTimeExtraFormula;
	
	@Column(name = "out_time_extra_ovt")
	private Integer outTimeExtraOvertime;
	
	@Column(name = "out_time_auto_in")
	private Double overtimeAutoIn;
	
	@Column(name = "out_time_auto_out")
	private Double overtimeAutoOut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ovt_break_end")
	private Date overtimeBreakEnd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ovt_break_start")
	private Date overtimeBreakStart;
	
	@Column(name = "ovt_factor_code", length = 50)
	private String overtimeFactorCode;
	
	@Column(name = "ovt_out_time")
	private Integer overtimeOutTime;
	
	@Column(name = "plcpt")
	private Boolean PLCPT;
	
	@Column(name = "process_flag")
	private Boolean processFlag;
	
	@Column(name = "ptswp")
	private Boolean PTSWP;
	
	@Column(name = "quick_out_time")
	private Integer quickOutTime;
	
	@Column(name = "reason", length = 255)
	private String reason;
	
	@Column(name = "remark", length = 255)
	private String remark;
	
	@Column(name = "request_ovt_in")
	private Integer requestOvertimeIn;
	
	@Column(name = "request_ovt_out")
	private Integer requestOvertimeOut;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shift_break_end")
	private Date shiftBreakEnd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shift_break_start")
	private Date shiftBreakStart;
	
	@Column(name = "shift_code", length = 50)
	private String shiftCode;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shift_ovt_end")
	private Date shiftOvertimeEnd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shift_ovt_start")
	private Date shiftOvertimeStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shift_ovt_limit")
	private Date startOvertimeLimit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_range_in")
	private Date startRangeIn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_range_out")
	private Date startRangeOut;
	
	@Column(name = "tlhdr")
	private Boolean TLHDR;
	
	@Column(name = "total_ovt_day")
	private Double totalOvertimeDay;
	
	@Column(name = "total_ovt_index")
	private Double totalOvertimeIndex;
	
	@Column(name = "total_ovt_index_formula")
	private Double totalOvertimeIndexFormula;
	
	@Column(name = "transaction_code", length = 50)
	private String transactionCode;
	
	@Column(name = "transport_ovt")
	private Double transportOvertime;
	
	@Column(name = "transport_ovt_hd")
	private Double transportOvertimeHd;
	
	@Column(name = "transaction_ref_no", length = 50)
	private String transactionRefNo;
	
	@Column(name = "edit_reason", length = 255)
	private String editReason;
	
	@Column(name = "edit_remark", length = 255)
	private String editRemark;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "work_date")
	private Date workDate;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sync_date")
	private Date syncDate;

	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name="ack_sync")
	private Boolean ackSync;

	@Column(name = "is_locked")
	private Boolean isLocked;
	
	public Boolean getAckSync() {
		return ackSync;
	}

	public void setAckSync(Boolean ackSync) {
		this.ackSync = ackSync;
	}
	
	
	public Double getAcceptedOvertimeIn() {
		return acceptedOvertimeIn;
	}

	public void setAcceptedOvertimeIn(Double acceptedOvertimeIn) {
		this.acceptedOvertimeIn = acceptedOvertimeIn;
	}

	public Double getAcceptedOvertimeOut() {
		return acceptedOvertimeOut;
	}

	public void setAcceptedOvertimeOut(Double acceptedOvertimeOut) {
		this.acceptedOvertimeOut = acceptedOvertimeOut;
	}

	public Double getAcceptedOvertimeOutFromDay() {
		return acceptedOvertimeOutFromDay;
	}

	public void setAcceptedOvertimeOutFromDay(Double acceptedOvertimeOutFromDay) {
		this.acceptedOvertimeOutFromDay = acceptedOvertimeOutFromDay;
	}

	public Date getActualInTime() {
		return actualInTime;
	}

	public void setActualInTime(Date actualInTime) {
		this.actualInTime = actualInTime;
	}

	public Date getActualInTimeGmt() {
		return actualInTimeGmt;
	}

	public void setActualInTimeGmt(Date actualInTimeGmt) {
		this.actualInTimeGmt = actualInTimeGmt;
	}

	public Date getActualOutTime() {
		return actualOutTime;
	}

	public void setActualOutTime(Date actualOutTime) {
		this.actualOutTime = actualOutTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCompareField() {
		return compareField;
	}

	public void setCompareField(String compareField) {
		this.compareField = compareField;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public Date getEditInTime() {
		return editInTime;
	}

	public void setEditInTime(Date editInTime) {
		this.editInTime = editInTime;
	}

	public Date getEditInTimeLpad() {
		return editInTimeLpad;
	}

	public void setEditInTimeLpad(Date editInTimeLpad) {
		this.editInTimeLpad = editInTimeLpad;
	}

	public Date getEditOutTime() {
		return editOutTime;
	}

	public void setEditOutTime(Date editOutTime) {
		this.editOutTime = editOutTime;
	}

	public Date getEditOutTimeLpad() {
		return editOutTimeLpad;
	}

	public void setEditOutTimeLpad(Date editOutTimeLpad) {
		this.editOutTimeLpad = editOutTimeLpad;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Date getEndOvertimeLimit() {
		return endOvertimeLimit;
	}

	public void setEndOvertimeLimit(Date endOvertimeLimit) {
		this.endOvertimeLimit = endOvertimeLimit;
	}

	public Date getEndRangeIn() {
		return endRangeIn;
	}

	public void setEndRangeIn(Date endRangeIn) {
		this.endRangeIn = endRangeIn;
	}

	public Date getEndRangeOut() {
		return endRangeOut;
	}

	public void setEndRangeOut(Date endRangeOut) {
		this.endRangeOut = endRangeOut;
	}

	public Double getIndexOvertimeIn() {
		return indexOvertimeIn;
	}

	public void setIndexOvertimeIn(Double indexOvertimeIn) {
		this.indexOvertimeIn = indexOvertimeIn;
	}

	public Double getIndexOvertimeOut() {
		return indexOvertimeOut;
	}

	public void setIndexOvertimeOut(Double indexOvertimeOut) {
		this.indexOvertimeOut = indexOvertimeOut;
	}

	public Double getIndexOvertimeOut1() {
		return indexOvertimeOut1;
	}

	public void setIndexOvertimeOut1(Double indexOvertimeOut1) {
		this.indexOvertimeOut1 = indexOvertimeOut1;
	}

	public Double getIndexOvertimeOut2() {
		return indexOvertimeOut2;
	}

	public void setIndexOvertimeOut2(Double indexOvertimeOut2) {
		this.indexOvertimeOut2 = indexOvertimeOut2;
	}

	public Double getIndexOvertimeOut3() {
		return indexOvertimeOut3;
	}

	public void setIndexOvertimeOut3(Double indexOvertimeOut3) {
		this.indexOvertimeOut3 = indexOvertimeOut3;
	}

	public Double getIndexOvertimeOut4() {
		return indexOvertimeOut4;
	}

	public void setIndexOvertimeOut4(Double indexOvertimeOut4) {
		this.indexOvertimeOut4 = indexOvertimeOut4;
	}

	public Double getIndexOvertimeOutFromday() {
		return indexOvertimeOutFromday;
	}

	public void setIndexOvertimeOutFromday(Double indexOvertimeOutFromday) {
		this.indexOvertimeOutFromday = indexOvertimeOutFromday;
	}

	public Double getIndexOvertimeOutFromdayFormula() {
		return indexOvertimeOutFromdayFormula;
	}

	public void setIndexOvertimeOutFromdayFormula(Double indexOvertimeOutFromdayFormula) {
		this.indexOvertimeOutFromdayFormula = indexOvertimeOutFromdayFormula;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Integer getInTimeExtra() {
		return inTimeExtra;
	}

	public void setInTimeExtra(Integer inTimeExtra) {
		this.inTimeExtra = inTimeExtra;
	}

	public Integer getInTimeExtraFormula() {
		return inTimeExtraFormula;
	}

	public void setInTimeExtraFormula(Integer inTimeExtraFormula) {
		this.inTimeExtraFormula = inTimeExtraFormula;
	}

	public Integer getLateInTime() {
		return lateInTime;
	}

	public void setLateInTime(Integer lateInTime) {
		this.lateInTime = lateInTime;
	}

	public String getLocationIn() {
		return locationIn;
	}

	public void setLocationIn(String locationIn) {
		this.locationIn = locationIn;
	}

	public String getLocationOut() {
		return locationOut;
	}

	public void setLocationOut(String locationOut) {
		this.locationOut = locationOut;
	}

	public String getMachineNo() {
		return machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	public String getMachineTerminal() {
		return machineTerminal;
	}

	public void setMachineTerminal(String machineTerminal) {
		this.machineTerminal = machineTerminal;
	}

	public Double getMealOvertime() {
		return mealOvertime;
	}

	public void setMealOvertime(Double mealOvertime) {
		this.mealOvertime = mealOvertime;
	}

	public Double getMealOvertimeHd() {
		return mealOvertimeHd;
	}

	public void setMealOvertimeHd(Double mealOvertimeHd) {
		this.mealOvertimeHd = mealOvertimeHd;
	}

	public Boolean getMTSWP() {
		return MTSWP;
	}

	public void setMTSWP(Boolean mTSWP) {
		MTSWP = mTSWP;
	}

	public Date getOutEndTimeMin() {
		return outEndTimeMin;
	}

	public void setOutEndTimeMin(Date outEndTimeMin) {
		this.outEndTimeMin = outEndTimeMin;
	}

	public Date getOutEndTimeMax() {
		return outEndTimeMax;
	}

	public void setOutEndTimeMax(Date outEndTimeMax) {
		this.outEndTimeMax = outEndTimeMax;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Integer getOutTimeExtra() {
		return outTimeExtra;
	}

	public void setOutTimeExtra(Integer outTimeExtra) {
		this.outTimeExtra = outTimeExtra;
	}

	public Integer getOutTimeExtraFormula() {
		return outTimeExtraFormula;
	}

	public void setOutTimeExtraFormula(Integer outTimeExtraFormula) {
		this.outTimeExtraFormula = outTimeExtraFormula;
	}

	public Integer getOutTimeExtraOvertime() {
		return outTimeExtraOvertime;
	}

	public void setOutTimeExtraOvertime(Integer outTimeExtraOvertime) {
		this.outTimeExtraOvertime = outTimeExtraOvertime;
	}

	public Double getOvertimeAutoIn() {
		return overtimeAutoIn;
	}

	public void setOvertimeAutoIn(Double overtimeAutoIn) {
		this.overtimeAutoIn = overtimeAutoIn;
	}

	public Double getOvertimeAutoOut() {
		return overtimeAutoOut;
	}

	public void setOvertimeAutoOut(Double overtimeAutoOut) {
		this.overtimeAutoOut = overtimeAutoOut;
	}

	public Date getOvertimeBreakEnd() {
		return overtimeBreakEnd;
	}

	public void setOvertimeBreakEnd(Date overtimeBreakEnd) {
		this.overtimeBreakEnd = overtimeBreakEnd;
	}

	public Date getOvertimeBreakStart() {
		return overtimeBreakStart;
	}

	public void setOvertimeBreakStart(Date overtimeBreakStart) {
		this.overtimeBreakStart = overtimeBreakStart;
	}

	public String getOvertimeFactorCode() {
		return overtimeFactorCode;
	}

	public void setOvertimeFactorCode(String overtimeFactorCode) {
		this.overtimeFactorCode = overtimeFactorCode;
	}

	public Integer getOvertimeOutTime() {
		return overtimeOutTime;
	}

	public void setOvertimeOutTime(Integer overtimeOutTime) {
		this.overtimeOutTime = overtimeOutTime;
	}

	public Boolean getPLCPT() {
		return PLCPT;
	}

	public void setPLCPT(Boolean pLCPT) {
		PLCPT = pLCPT;
	}

	public Boolean getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(Boolean processFlag) {
		this.processFlag = processFlag;
	}

	public Boolean getPTSWP() {
		return PTSWP;
	}

	public void setPTSWP(Boolean pTSWP) {
		PTSWP = pTSWP;
	}

	public Integer getQuickOutTime() {
		return quickOutTime;
	}

	public void setQuickOutTime(Integer quickOutTime) {
		this.quickOutTime = quickOutTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRequestOvertimeIn() {
		return requestOvertimeIn;
	}

	public void setRequestOvertimeIn(Integer requestOvertimeIn) {
		this.requestOvertimeIn = requestOvertimeIn;
	}

	public Integer getRequestOvertimeOut() {
		return requestOvertimeOut;
	}

	public void setRequestOvertimeOut(Integer requestOvertimeOut) {
		this.requestOvertimeOut = requestOvertimeOut;
	}

	public Date getShiftBreakEnd() {
		return shiftBreakEnd;
	}

	public void setShiftBreakEnd(Date shiftBreakEnd) {
		this.shiftBreakEnd = shiftBreakEnd;
	}

	public Date getShiftBreakStart() {
		return shiftBreakStart;
	}

	public void setShiftBreakStart(Date shiftBreakStart) {
		this.shiftBreakStart = shiftBreakStart;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public Date getShiftOvertimeEnd() {
		return shiftOvertimeEnd;
	}

	public void setShiftOvertimeEnd(Date shiftOvertimeEnd) {
		this.shiftOvertimeEnd = shiftOvertimeEnd;
	}

	public Date getShiftOvertimeStart() {
		return shiftOvertimeStart;
	}

	public void setShiftOvertimeStart(Date shiftOvertimeStart) {
		this.shiftOvertimeStart = shiftOvertimeStart;
	}

	public Date getStartOvertimeLimit() {
		return startOvertimeLimit;
	}

	public void setStartOvertimeLimit(Date startOvertimeLimit) {
		this.startOvertimeLimit = startOvertimeLimit;
	}

	public Date getStartRangeIn() {
		return startRangeIn;
	}

	public void setStartRangeIn(Date startRangeIn) {
		this.startRangeIn = startRangeIn;
	}

	public Date getStartRangeOut() {
		return startRangeOut;
	}

	public void setStartRangeOut(Date startRangeOut) {
		this.startRangeOut = startRangeOut;
	}

	public Boolean getTLHDR() {
		return TLHDR;
	}

	public void setTLHDR(Boolean tLHDR) {
		TLHDR = tLHDR;
	}

	public Double getTotalOvertimeDay() {
		return totalOvertimeDay;
	}

	public void setTotalOvertimeDay(Double totalOvertimeDay) {
		this.totalOvertimeDay = totalOvertimeDay;
	}

	public Double getTotalOvertimeIndex() {
		return totalOvertimeIndex;
	}

	public void setTotalOvertimeIndex(Double totalOvertimeIndex) {
		this.totalOvertimeIndex = totalOvertimeIndex;
	}

	public Double getTotalOvertimeIndexFormula() {
		return totalOvertimeIndexFormula;
	}

	public void setTotalOvertimeIndexFormula(Double totalOvertimeIndexFormula) {
		this.totalOvertimeIndexFormula = totalOvertimeIndexFormula;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public Double getTransportOvertime() {
		return transportOvertime;
	}

	public void setTransportOvertime(Double transportOvertime) {
		this.transportOvertime = transportOvertime;
	}

	public Double getTransportOvertimeHd() {
		return transportOvertimeHd;
	}

	public void setTransportOvertimeHd(Double transportOvertimeHd) {
		this.transportOvertimeHd = transportOvertimeHd;
	}

	public String getTransactionRefNo() {
		return transactionRefNo;
	}

	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getEditReason() {
		return editReason;
	}

	public void setEditReason(String editReason) {
		this.editReason = editReason;
	}

	public String getEditRemark() {
		return editRemark;
	}

	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}
	
	
	
	
	
}
