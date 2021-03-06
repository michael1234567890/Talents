package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_request_type")
public class RequestType extends AbstractEntity {

	@Column(name = "module", length = 100)
	private String module;

	@Column(name = "category_type", length = 100)
	private String categoryType;

	@Column(name = "category_type_ext_id", length = 100)
	private String categoryTypeExtId;

	@Column(name = "type", length = 100)
	private String type;

	@Column(name = "remark", length = 255)
	private String remark;

	@Column(name = "grade")
	private Integer grade;
	
	@Column(name = "grade_start")
	private Integer gradeStart;
	
	@Column(name = "grade_end")
	private Integer gradeEnd;

	@Column(name = "position", length = 2000)
	private String position;
	
	@Column(name = "job_title", length = 1000)
	private String jobTitle;
	
	@Column(name = "type_label", length = 255)
	private String typeLabel;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;

	
	@Column(name = "company_id")
	private Long company;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "request_cat_type_id")
//	private RequestCategoryType requestCategoryType;

	@Column(name = "request_cat_type_id")
	private Long requestCategoryType;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "show_attachment")
	private Boolean showAttachment;
	
	@Column(name = "required_attachment")
	private Boolean requiredAttachment;
	
	@Column(name="max_num_of_days")
	private Integer maxNumOfDays;
	
	@Column(name = "need_balance")
	private Boolean needBalance;
	
	@Column(name="can_back_date")
	private Boolean canBackDate;
	
	@Column(name="can_future_date")
	private Boolean canFutureDate;
	
	@Column(name="limit_future_date")
	private Integer limitFutureDate; // day, 0 = unlimited
	
	@Column(name="limit_back_date")
	private Integer limitBackDate; //day,  0 = unlimited
	
	@Column(name="gender", length=20)
	private String gender; //day,  0 = unlimited
	
	@Column(name="flag_overtime")
	private Boolean flagOvertime;
	
	@Column(name="default_max_minutes_per_month")
	private Integer defaultMaxMinutesPerMonth; //day,  0 = unlimited
	
	@Column(name="max_minutes_per_month_jobtitle")
	private Integer maxMinutesPerMonthJobtitle; //day,  0 = unlimited
	
	@Column(name="cannot_request_today")
	private Boolean cannotRequestToday;
	
	@Column(name="limit_day_of_month")
	private Integer limitDayOfMonth; // batas tanggal submit transaksi per bulan ex: 5, untuk request date bulan kemarin hanya bs di masukan sampai tanggal 5 bulan ini 
	
	@Column(name="is_calendar_date")
	private Boolean isCalendarDate;
	
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getRequestCategoryType() {
		return requestCategoryType;
	}

	public void setRequestCategoryType(Long requestCategoryType) {
		this.requestCategoryType = requestCategoryType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getNeedBalance() {
		return needBalance;
	}

	public void setNeedBalance(Boolean needBalance) {
		this.needBalance = needBalance;
	}

	public Boolean isCanBackDate() {
		return canBackDate;
	}

	public void setCanBackDate(Boolean canBackDate) {
		this.canBackDate = canBackDate;
	}

	public Boolean isCanFutureDate() {
		return canFutureDate;
	}

	public void setCanFutureDate(Boolean canFutureDate) {
		this.canFutureDate = canFutureDate;
	}

	public Integer getLimitFutureDate() {
		return limitFutureDate;
	}

	public void setLimitFutureDate(Integer limitFutureDate) {
		this.limitFutureDate = limitFutureDate;
	}

	public Integer getLimitBackDate() {
		return limitBackDate;
	}

	public void setLimitBackDate(Integer limitBackDate) {
		this.limitBackDate = limitBackDate;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public Boolean getShowAttachment() {
		return showAttachment;
	}

	public void setShowAttachment(Boolean showAttachment) {
		this.showAttachment = showAttachment;
	}

	public Boolean getRequiredAttachment() {
		return requiredAttachment;
	}

	public void setRequiredAttachment(Boolean requiredAttachment) {
		this.requiredAttachment = requiredAttachment;
	}

	public Integer getMaxNumOfDays() {
		return maxNumOfDays;
	}

	public void setMaxNumOfDays(Integer maxNumOfDays) {
		this.maxNumOfDays = maxNumOfDays;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getGradeEnd() {
		return gradeEnd;
	}

	public void setGradeEnd(Integer gradeEnd) {
		this.gradeEnd = gradeEnd;
	}

	public Integer getGradeStart() {
		return gradeStart;
	}

	public void setGradeStart(Integer gradeStart) {
		this.gradeStart = gradeStart;
	}

	public Boolean getFlagOvertime() {
		return flagOvertime;
	}

	public void setFlagOvertime(Boolean flagOvertime) {
		this.flagOvertime = flagOvertime;
	}

	public Integer getDefaultMaxMinutesPerMonth() {
		return defaultMaxMinutesPerMonth;
	}

	public void setDefaultMaxMinutesPerMonth(Integer defaultMaxMinutesPerMonth) {
		this.defaultMaxMinutesPerMonth = defaultMaxMinutesPerMonth;
	}

	public Integer getMaxMinutesPerMonthJobtitle() {
		return maxMinutesPerMonthJobtitle;
	}

	public void setMaxMinutesPerMonthJobtitle(Integer maxMinutesPerMonthJobtitle) {
		this.maxMinutesPerMonthJobtitle = maxMinutesPerMonthJobtitle;
	}

	public Boolean getCannotRequestToday() {
		return cannotRequestToday;
	}

	public void setCannotRequestToday(Boolean cannotRequestToday) {
		this.cannotRequestToday = cannotRequestToday;
	}

	public Integer getLimitDayOfMonth() {
		return limitDayOfMonth;
	}

	public void setLimitDayOfMonth(Integer limitDayOfMonth) {
		this.limitDayOfMonth = limitDayOfMonth;
	}

	public Boolean getIsCalendarDate() {
		return isCalendarDate;
	}

	public void setIsCalendarDate(Boolean isCalendarDate) {
		this.isCalendarDate = isCalendarDate;
	}

	
	

}
