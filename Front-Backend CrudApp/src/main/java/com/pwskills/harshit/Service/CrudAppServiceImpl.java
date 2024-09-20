package com.pwskills.harshit.Service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.pwskills.harshit.DataObject.CrudAppBo;
import com.pwskills.harshit.DataObject.CrudAppDto;
import com.pwskills.harshit.Repository.ICrudAppRepo;
import com.pwskills.harshit.factory.CrudRepoFactory;

public class CrudAppServiceImpl implements ICrudAppService {

	private static ICrudAppRepo repo = CrudRepoFactory.getCrudRepoObj();

	public String insertRecord(CrudAppDto crudDto) {

		CrudAppBo crudAppBo = new CrudAppBo();

		crudAppBo.setSname(crudDto.getSname());
		crudAppBo.setSaddress(crudDto.getSaddress());
		crudAppBo.setSage(crudDto.getSage());

		// Extra Logic to get The Stutas by their Age==
		if (crudAppBo.getSage() > 60) {
			crudAppBo.setStutas("Senior Citizen...");
		} else {
			crudAppBo.setStutas("Working Person...");
		}

		return repo.insertRecord(crudAppBo);
	}

	@Override
	public CrudAppDto readRecord(Integer sid) {

		CrudAppBo bo = null;
		CrudAppDto dto = null;

		bo = repo.readrecord(sid);
		if (bo == null)
			return dto;
		else {
			try {
				dto = new CrudAppDto();
				BeanUtils.copyProperties(dto, bo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Override
	public String deleteRecord(Integer id) {
		return repo.deleteRecord(id);
	}

	@Override
	public String updateRecord(CrudAppDto dto) {

		CrudAppBo appBo = new CrudAppBo();
		appBo.setSid(dto.getSid());
		appBo.setSname(dto.getSname());
		appBo.setSage(dto.getSage());
		appBo.setSaddress(dto.getSaddress());

		// Extra Logic to get The Stutas by their Age==
		if (appBo.getSage() > 60) {
			appBo.setStutas("Senior Citizen...");
		} else {
			appBo.setStutas("Working Person...");
		}

		return repo.updateRecord(appBo);
	}
}