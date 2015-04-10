package blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �V���v���ȃR�l�N�V�����Ǘ��N���X
 *
 * @author Shinji Miyamoto
 */
public class ConnectionManager {

    /**
     * JDBC�h���C�o�[�̃N���X��
     */
    final static String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * �f�[�^�x�[�X��URL
     */
    final static String URL = "jdbc:mysql://localhost/mydb";


    /**
     * �f�[�^�x�[�X�̃��[�U
     */
    final static String USER = "root";

    /**
     * �f�[�^�x�[�X�̃p�X���[�h
     */
    final static String PASS = "root";

    /**
     * Conncection���擾���܂��B
     */
    public static Connection getConnection()
            throws SQLException {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(
                    "fail to class load : "
                            + e.getMessage());
        }

        Connection con = DriverManager.getConnection(URL,
                USER, PASS);

        return con;
    }




    /**
     * �ڑ��m�F�e�X�g
     */
    public static void main(String[] args)
            throws SQLException {

        Connection con = getConnection();
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("select * from account");
        while(rs.next()){
        	String s = "NAME=" + rs.getString("NAME")
        		+ ",MONEY=" + rs.getString("MONEY")
        		;
        	System.out.println(s);
        }
        smt.close();
        con.close();
        System.out.println("END");
    }
}