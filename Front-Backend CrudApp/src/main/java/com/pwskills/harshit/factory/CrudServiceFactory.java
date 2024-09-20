package com.pwskills.harshit.factory;

import com.pwskills.harshit.Service.CrudAppServiceImpl;
import com.pwskills.harshit.Service.ICrudAppService;

public class CrudServiceFactory {

	private static ICrudAppService service = null;

	private CrudServiceFactory() {
	}

	public static ICrudAppService getCrudServiceObj() {

		if (service == null) {
			service = new CrudAppServiceImpl();
		}

		return service;

	}
}
