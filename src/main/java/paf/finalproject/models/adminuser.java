package paf.finalproject.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class adminuser {
    private String username;
	private String password;

	public void setUsername(String username) { this.username = username; }
	public String getUsername() { return this.username; }

	public void setPassword(String password) { this.password = password; }
	public String getPassword() { return this.password; }

	public static adminuser populate(SqlRowSet rs) {
		final adminuser u = new adminuser();
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		return u;
	}
}
