package edu.ycp.cs.cs496.TGOH.pesist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ycp.cs.cs496.TGOH.temp.Courses;
import edu.ycp.cs.cs496.TGOH.temp.Registration;
import edu.ycp.cs.cs496.TGOH.temp.User;

public class RealDatabase implements IDatabase{
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	@Override
	public void addUser(final User user) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into users (username, firstname, lastname, password, type) values (?, ?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					storeUserNoId(user, stmt, 1);

					// Attempt to insert the item
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Users");
					}
					
					user.setId(generatedKeys.getInt(1));
					System.out.println("New item has id " + user.getId());
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public boolean deleteUser(final User user) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("Delete from users where users.username = ?");
					stmt.setString(1, user.getUserName());
					
					stmt.executeQuery().deleteRow();
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public User getUser(final String Username) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select users.* from users where users.username = ?");
					stmt.setString(1, Username);
					
					resultSet = stmt.executeQuery();
					
					if (!resultSet.next()) {
						// No such item
						return null;
					}
					
					User user = new User();
					loadUser(user, resultSet, 1);
					return user;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Courses getCourse(final int coursename) {
		return executeTransaction(new Transaction<Courses>() {
			@Override
			public Courses execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select courses.* from courses where courses.id = ?");
					stmt.setInt(1, coursename);
					
					resultSet = stmt.executeQuery();
					
					if (!resultSet.next()) {
						// No such item
						return null;
					}
					
					Courses course = new Courses();
					loadCourse(course, resultSet, 1);
					return course;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public void deleteCourse(int Coursename) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Courses[] getCoursefromUser(int user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCourse(final Courses course) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into courses (coursename) values (?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					storeCourseNoId(course, stmt, 1);

					// Attempt to insert the item
					stmt.executeUpdate();

					// Determine the auto-generated id
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Item");
					}
					
					course.setId(generatedKeys.getInt(1));
					System.out.println("New item has id " + course.getId());
					
					return true;
				} finally {
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
		
	}

	@Override
	public List<Courses> getAllCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registration registerUserForCourse(int user, int course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void RemovingUserFromCourse(int user, int course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registration findUserForCourse(User user, Courses course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registration AcceptingUserforCourse(User user, Courses course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User[] getPendingUserforCourse(int course) {
		// TODO Auto-generated method stub
		return null;
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
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
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
				PreparedStatement stmt2 = null;
				
				try {
					// Note that the 'id' column is an autoincrement primary key,
					// so SQLite will automatically assign an id when rows
					// are inserted.
					stmt = conn.prepareStatement(
							"create table users (" +
							"  id integer primary key not null generated always as identity," +
							"  username varchar(30) unique," +
							"  firstname varchar(30) unique," +
							"  lastname varchar(30) unique," +
							"  password varchar(30) unique," +
							"  type boolean not null " +
							")"

					);
					
					stmt.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table courses (" +
							"  id integer primary key not null generated always as identity," +
							"  coursename varchar(10) unique" +
							")"
					);
					
					stmt2.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}	
	
	public void loadInitialUserData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement("insert into users (username, firstname, lastname, password, type) values (?, ?, ?, ?, ?)");
					storeUserNoId(new User("Apples","Apples","Apples","Apples", true), stmt, 1);
					stmt.addBatch();
					storeUserNoId(new User("Oranges","Oranges","Oranges","Oranges",false), stmt, 1);
					stmt.addBatch();
					storeUserNoId(new User("Pomegranates","Pomegranates","Pomegranates","Pomegranates",true), stmt, 1);
					stmt.addBatch();
					
					stmt.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void loadCourseInitialUserData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement("insert into courses (coursename) values (?)");
					storeCourseNoId(new Courses("CS360"), stmt, 1);
					stmt.addBatch();
					storeCourseNoId(new Courses("CS101"), stmt, 1);
					stmt.addBatch();
					storeCourseNoId(new Courses("CS201"), stmt, 1);
					stmt.addBatch();
					
					stmt.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	protected void storeUserNoId(User user, PreparedStatement stmt, int index) throws SQLException {
		// Note that we are assuming that the Item does not have a valid id,
		// and so are not attempting to store the (invalid) id.
		// This is the preferred approach when inserting a new row into
		// a table in which a unique id is automatically generated.
		stmt.setString(index++, user.getUserName());
		stmt.setString(index++, user.getFirstName());
		stmt.setString(index++, user.getLastName());
		stmt.setString(index++, user.getPassword());
		stmt.setBoolean(index++, user.getType());
	}
	
	protected void storeCourseNoId(Courses course, PreparedStatement stmt, int index) throws SQLException {
		// Note that we are assuming that the Item does not have a valid id,
		// and so are not attempting to store the (invalid) id.
		// This is the preferred approach when inserting a new row into
		// a table in which a unique id is automatically generated.
		stmt.setString(index++, course.getCourse());
	}
	
	protected void loadUser(User item, ResultSet resultSet, int index) throws SQLException {
		item.setId(resultSet.getInt(index++));
		item.setUserName(resultSet.getString(index++));
		item.setFirstName(resultSet.getString(index++));
		item.setLastName(resultSet.getString(index++));
		item.setPassword(resultSet.getString(index++));
		item.setType(resultSet.getBoolean(index++));
	}
	
	protected void loadCourse(Courses item, ResultSet resultSet, int index) throws SQLException {
		item.setId(resultSet.getInt(index++));
		item.setCourse(resultSet.getString(index++));
	}
	
	public static void main(String[] args) {
		RealDatabase db = new RealDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		System.out.println("Loading initial data...");
		db.loadInitialUserData();
		db.loadCourseInitialUserData();
		System.out.println("Done!");
	}

	@Override
	public Courses getCourseByName(String coursename) {
		// TODO Auto-generated method stub
		return null;
	}
}
