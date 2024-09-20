package com.pwskills.harshit.Service;

import com.pwskills.harshit.DataObject.CrudAppDto;

public interface ICrudAppService {

	public String insertRecord(CrudAppDto crudDto);

	public CrudAppDto readRecord(Integer sid);

	public String deleteRecord(Integer i);

	public String updateRecord(CrudAppDto dto);

}