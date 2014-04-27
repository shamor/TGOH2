package edu.ycp.cs.cs496.TGOH.pesist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.User;

public class RealDatabase implements IDatabase{
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String Username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addACourse(String username, String course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAllCourse(String Username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCourse(String username, String course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourse(String Username, String Course) {
		// TODO Auto-generated method stub
		
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					// Note that the 'id' column is an autoincrement primary key,
					// so SQLite will automatically assign an id when rows
					// are inserted.
					stmt = conn.prepareStatement(
							"create table items (" +
							"  id integer primary key autoincrement," +
							"  username varchar(30) unique," +
							"  firstname varchar(30) unique," +
							"  lastname varchar(30) unique," +
							"  password varchar(30) unique," +
							"  quantity integer," +
							
							")"
					);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement("insert into items (name, quantity) values (?, ?)");
					storeItemNoId(new User("Apples","Apples","Apples","Apples",true), stmt, 1);
					stmt.addBatch();
					storeItemNoId(new User("Oranges","Oranges","Oranges","Oranges",false), stmt, 1);
					stmt.addBatch();
					storeItemNoId(new User("Pomegranates","Pomegranates","Pomegranates","Pomegranates",true), stmt, 1);
					stmt.addBatch();
					
					stmt.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	protected void storeItemNoId(User user, PreparedStatement stmt, int index) throws SQLException {
		// Note that we are assuming that the Item does not have a valid id,
		// and so are not attempting to store the (invalid) id.
		// This is the preferred approach when inserting a new row into
		// a table in which a unique id is automatically generated.
		stmt.setString(index++, user.getUserName());
		stmt.setString(index++, user.getFirstName());
		stmt.setString(index++, user.getLastName());
		stmt.setString(index++, user.getPassword());
	}
	
	protected void loadItem(User item, ResultSet resultSet, int index) throws SQLException {
		item.setUserName(resultSet.getString(index++));
		item.setFirstName(resultSet.getString(index++));
		item.setLastName(resultSet.getString(index++));
		item.setPassword(resultSet.getString(index++));
	}
	
	public static void main(String[] args) {
		RealDatabase db = new RealDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		System.out.println("Loading initial data...");
		db.loadInitialData();
		System.out.println("Done!");
	}


}
