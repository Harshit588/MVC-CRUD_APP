package com.pwskills.harshit.factory;

import com.pwskills.harshit.Repository.CrudAppRepoImpl;
import com.pwskills.harshit.Repository.ICrudAppRepo;

public class CrudRepoFactory {

	private static ICrudAppRepo repo = null;

	private CrudRepoFactory() {
	}

	public static ICrudAppRepo getCrudRepoObj() {

		if (repo == null) {
			repo = new CrudAppRepoImpl();
		}

		return repo;
	}
}