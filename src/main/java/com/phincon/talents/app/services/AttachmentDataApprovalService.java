package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.AttachmentDataApprovalRepository;
import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AttachmentDataApprovalService {

	@Autowired
	AttachmentDataApprovalRepository attachmentDataApprovalRepository;

	@Transactional
	public AttachmentDataApproval findById(Long id) {
		return attachmentDataApprovalRepository.findOne(id);
	}
	
	@Transactional
	public List<AttachmentDataApproval> findByDataApproval(Long id) {
		return attachmentDataApprovalRepository.findByDataApproval(id);
	}


	@Transactional
	public void save(AttachmentDataApproval obj) {
		attachmentDataApprovalRepository.save(obj);
	}

}
