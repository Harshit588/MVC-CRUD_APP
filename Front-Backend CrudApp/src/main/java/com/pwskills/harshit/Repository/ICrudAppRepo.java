package com.pwskills.harshit.Repository;

import com.pwskills.harshit.DataObject.CrudAppBo;

public interface ICrudAppRepo {
	public String insertRecord(CrudAppBo crudAppBo);

	public CrudAppBo readrecord(Integer sid);

	public String deleteRecord(Integer id);

	public String updateRecord(CrudAppBo appBo);
}
