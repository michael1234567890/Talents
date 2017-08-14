package com.phincon.talents.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phincon.talents.app.model.hr.TMBalance;

@Service
public class TMBalanceService {
	
	public TMBalance getByModuleCategoryTypeFromList(List<TMBalance> listBalance, String module, String category, String type){
		if(listBalance!=null && listBalance.size() > 0) {
			module = module.toLowerCase();
			category = category.toLowerCase();
			type = type.toLowerCase();
			for (TMBalance tmBalance : listBalance) {
				if(tmBalance.getModule().toLowerCase().equals(module) && tmBalance.getType().toLowerCase().equals(type) && tmBalance.getCategoryType().toLowerCase().equals(type))
					return tmBalance;
			}
		}
		return null;
	}
	
	public TMBalance getByModuleTypeFromList(List<TMBalance> listBalance, String module,  String type){
		if(listBalance!=null && listBalance.size() > 0) {
			module = module.toLowerCase();
			type = type.toLowerCase();
			for (TMBalance tmBalance : listBalance) {
				if(tmBalance.getModule().toLowerCase().equals(module) && tmBalance.getType().toLowerCase().equals(type))
					return tmBalance;
			}
		}
		return null;
	}

	
}
