package com.pwskills.harshit.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pwskills.harshit.DataObject.CrudAppBo;
import com.pwskills.harshit.Utility.JdbcUtil;

public class CrudAppRepoImpl implements ICrudAppRepo {

	private static Connection connection = null;

	private static PreparedStatement insertPstmt = null;;
	private static PreparedStatement readPstmt = null;;
	private static PreparedStatement deletePstmt = null;;
	private static PreparedStatement updatePstmt = null;;

	private static final String INSER_SQL_QUERY = "insert into student(`sname`,`sage`,`saddress`,`status`) values(?,?,?,?) ";
	private static final String READ_SQL_QUERY = "select sid,sname,sage,saddress from student where sid =?";
	private static final String DELETE_SQL_QUERY = "delete from student where sid =?";
	private static final String UPDATE_SQL_QUERY = "update student set sname=?,sage=?,saddress=?,status=? where sid =?";

	static {
		try {
			connection = JdbcUtil.GetDbConnection();

			if (connection != null) {
				insertPstmt = connection.prepareStatement(INSER_SQL_QUERY);
			}
			if (connection != null) {
				readPstmt = connection.prepareStatement(READ_SQL_QUERY);
			}
			if (connection != null) {
				deletePstmt = connection.prepareStatement(DELETE_SQL_QUERY);
			}
			if (connection != null) {
				updatePstmt = connection.prepareStatement(UPDATE_SQL_QUERY);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insertRecord(CrudAppBo crudAppBo) {

		String status = "";

		try {
			if (insertPstmt != null) {
				insertPstmt.setString(1, crudAppBo.getSname());
				insertPstmt.setInt(2, crudAppBo.getSage());
				insertPstmt.setString(3, crudAppBo.getSaddress());
				insertPstmt.setString(4, crudAppBo.getStutas());

				int update = insertPstmt.executeUpdate();
				if (update == 1) {
					status = "Success";
				}
			}
		} catch (SQLException se) {

			int errorCode = se.getErrorCode();

			if (errorCode == 1064) {
				status = "some error in db operation";
			} else if (errorCode == 2006) {
				status = "connection lost at dbside";
			} else if (errorCode == 1045) {
				status = "acess denied for the user wrong credentialsm";
			} else if (errorCode == 1044) {
				status = "access denied for the user to database";
			} else {
				status = "Some Error at database side";
			}

		} catch (Exception e) {
			status = "Some Unknown Exception ";
			return status;
		}

		return status;
	}

	@Override
	public CrudAppBo readrecord(Integer sid) {

		CrudAppBo bo = null;

		try {
			if (readPstmt != null) {
				readPstmt.setInt(1, sid);

				ResultSet resultSet = readPstmt.executeQuery();
				if (resultSet != null) {

					if (resultSet.next()) {
						bo = new CrudAppBo();
						bo.setSid(resultSet.getInt(1));
						bo.setSname(resultSet.getString(2));
						bo.setSage(resultSet.getInt(3));
						bo.setSaddress(resultSet.getString(4));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bo;
	}

	@Override
	public String deleteRecord(Integer id) {
		String status = "";

		CrudAppBo bo = readrecord(id);
		if (bo != null) {

			if (deletePstmt != null) {

				try {
					deletePstmt.setInt(1, id);
					deletePstmt.executeUpdate();
					status = "success";

				} catch (SQLException e) {
					status = "\nSome Problem Accured At DB Side..";
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("\nRecord Not Found For " + id + " try Again");
		}

		return status;
	}

	@Override
	public String updateRecord(CrudAppBo appBo) {

		String status = "";
		try {

			if (updatePstmt != null) {
				System.out.println(appBo.getSid());
				System.out.println(appBo.getSname());
				System.out.println(appBo.getSaddress());
				System.out.println(appBo.getSage());

				updatePstmt.setString(1, appBo.getSname());
				updatePstmt.setInt(2, appBo.getSage());
				updatePstmt.setString(3, appBo.getSaddress());
				updatePstmt.setString(4, appBo.getStutas());

				updatePstmt.setInt(5, appBo.getSid());
				
				updatePstmt.executeUpdate();
				status = "success";
			}
		} catch (SQLException se) {
			status = "Some Probelem Accured At DB Side...";
			se.printStackTrace();
		}

		return status;
	}
}
