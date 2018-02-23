package com.phincon.talents.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.app.model.hr.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{
	@Query("select p from Employee p where UPPER(p.firstName) like UPPER(?1) or " +
            "UPPER(p.lastName) like UPPER(?1)")
    List search(String term);
	
	
	 @Query
	 Employee findByOfficeMail(String email);
	 
	 @Query
	 List<Employee> findByIdIn(List<Long> ids);
	 
	 @Modifying
	 @Query("UPDATE Employee set maritalStatus=:maritalStatus, maritalStatusDataApproval=:maritalStatusDataApproval,changeMaritalStatus=:changeMaritalStatus, needSync=:needSync where id=:employeeId")
	 void updateMaritalStatus(@Param("employeeId") Long employeeId, @Param("maritalStatus") String maritalStatus,@Param("maritalStatusDataApproval") Long maritalStatusDataApproval,@Param("changeMaritalStatus") String changeMaritalStatus,@Param("needSync") Boolean needSync);
	 
	 @Modifying
	 @Query("UPDATE Employee set npwpNo=:npwpNo, npwpStatusDataApproval=:npwpStatusDataApproval, changeNPWP=:changeNPWP, needSync=:needSync where id=:employeeId")
	 void updateNPWP(@Param("employeeId") Long employeeId, @Param("npwpNo") String npwpNo, @Param("npwpStatusDataApproval") Long npwpStatusDataApproval, @Param("changeNPWP") String changeNPWP, @Param("needSync") Boolean needSync);
	 
	 @Modifying
	 @Query("UPDATE Employee set nircNo=:nircNo, nircStatusDataApproval=:nircStatusDataApproval, changeNIRCNO=:changeNIRCNO, needSync=:needSync where id=:employeeId")
	 void updateNIRCNO(@Param("employeeId") Long employeeId, @Param("nircNo") String nircNo, @Param("nircStatusDataApproval") Long nircStatusDataApproval, @Param("changeNIRCNO") String changeNIRCNO, @Param("needSync") Boolean needSync);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee set ktpName=:ktpName, ktpnameStatusDataApproval=:ktpnameStatusDataApproval, changeKTPNAME=:changeKTPNAME, needSync=:needSync where id=:employeeId")
	 void updateKTPNAME(@Param("employeeId") Long employeeId, @Param("ktpName") String ktpName, @Param("ktpnameStatusDataApproval") Long ktpnameStatusDataApproval, @Param("changeKTPNAME") String changeKTPNAME, @Param("needSync") Boolean needSync);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee set familyCardNo=:familyCardNo, familyCardNoStatusDataApproval=:familyCardNoStatusDataApproval, changeFamilyCardNo=:changeFamilyCardNo, needSync=:needSync where id=:employeeId")
	 void updateFamilyCardNo(@Param("employeeId") Long employeeId, @Param("familyCardNo") String familyCardNo, @Param("familyCardNoStatusDataApproval") Long familyCardNoStatusDataApproval, @Param("changeFamilyCardNo") String changeFamilyCardNo, @Param("needSync") Boolean needSync);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee  set maritalStatusDataApproval=null,changeMaritalStatus=null, needSync=false where id=:employeeId")
	 void rejectedMaritalStatus(@Param("employeeId") Long employeeId);
	 
	 @Modifying
	 @Query("UPDATE Employee set npwpStatusDataApproval=null, changeNPWP=null, needSync=false where id=:employeeId")
	 void rejectedNPWP(@Param("employeeId") Long employeeId);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee set nircStatusDataApproval=null, changeNIRCNO=null, needSync=false where id=:employeeId")
	 void rejectedNIRCNO(@Param("employeeId") Long employeeId);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee set ktpnameStatusDataApproval=null, changeKTPNAME=null, needSync=false where id=:employeeId")
	 void rejectedKTPNAME(@Param("employeeId") Long employeeId);
	 

	 @Modifying
	 @Query("UPDATE Employee set familyCardNoStatusDataApproval=null, changeFamilyCardNo=null, needSync=false where id=:employeeId")
	 void rejectedFamilyCardNo(@Param("employeeId") Long employeeId);

	 
	 @Modifying
	 @Query("UPDATE Employee set  changeMaritalStatus=:changeMaritalStatus, maritalStatusDataApproval=:maritalStatusDataApproval where id=:employeeId")
	 void requestMaritalStatus(@Param("changeMaritalStatus") String changeMaritalStatus, @Param("maritalStatusDataApproval") Long maritalStatusDataApproval, @Param("employeeId") Long employeeId);
	 
	 @Modifying
	 @Query("UPDATE Employee set changeNPWP=:changeNPWP, npwpStatusDataApproval=:npwpStatusDataApproval where id=:employeeId")
	 void requestNPWP(@Param("changeNPWP") String changeNPWP, @Param("npwpStatusDataApproval") Long npwpStatusDataApproval, @Param("employeeId") Long employeeId);
	 
	 
	 @Modifying
	 @Query("UPDATE Employee set changeNIRCNO=:changeNIRCNO, nircStatusDataApproval=:nircStatusDataApproval where id=:employeeId")
	 void requestNIRCNO(@Param("changeNIRCNO") String changeNIRCNO, @Param("nircStatusDataApproval") Long nircStatusDataApproval, @Param("employeeId") Long employeeId);
	 
	 @Modifying
	 @Query("UPDATE Employee set changeKTPNAME=:changeKTPNAME, ktpnameStatusDataApproval=:ktpnameStatusDataApproval where id=:employeeId")
	 void requestKTPNAME(@Param("changeKTPNAME") String changeKTPNAME, @Param("ktpnameStatusDataApproval") Long ktpnameStatusDataApproval, @Param("employeeId") Long employeeId);
	 
	 @Modifying
	 @Query("UPDATE Employee set changeFamilyCardNo=:changefamilyCardNo, familyCardNoStatusDataApproval=:familyCardNoStatusDataApproval where id=:employeeId")
	 void requestFamilyCardNo(@Param("changefamilyCardNo") String familyCardNo, @Param("familyCardNoStatusDataApproval") Long familyCardNoStatusDataApproval, @Param("employeeId") Long employeeId);
		 
}
