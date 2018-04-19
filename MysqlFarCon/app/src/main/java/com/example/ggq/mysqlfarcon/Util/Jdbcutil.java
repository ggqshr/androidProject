package com.example.ggq.mysqlfarcon.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbcutil {
    private String jdbcDriver = "com.mysql.jdbc.Driver"; //���ݿ���������
    private String strCon = "jdbc:mysql://127.0.0.1/ggq1?characterEncoding=utf8";
    private String username = "root";
    private String password = "123";

    private static Jdbcutil util = null;//��̬��Ա������֧�ֵ�̬ģʽ

    private static Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    /**
     * ��ȡjdbcutil����
     *
     * @return
     * @throws ClassNotFoundException
     */
    public static Jdbcutil createInstance() throws ClassNotFoundException {
        if (util == null) {
            util = new Jdbcutil();
            util.initDB();
        }
        return util;
    }


    public void initDB() throws ClassNotFoundException {
        //1����������
        Class.forName(jdbcDriver);
    }


    /**
     * �������ݿ�
     *
     * @throws SQLException
     */
    public void connectDB() throws SQLException {
        conn = DriverManager.getConnection(strCon, username, password);
    }


    /**
     * ��������
     *
     * @throws SQLException
     */

    public static void beginTran() throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * �ع�����
     *
     * @throws SQLException
     */
    public static void rollBack() throws SQLException {
        conn.rollback();
    }

    /**
     * �ύ����
     *
     * @throws SQLException
     */
    public static void commitTran() throws SQLException {
        conn.commit();
    }

    /**
     * �ر�����
     *
     * @throws SQLException
     */
    public void closeConn() {
        System.out.println("close ......");
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * ����PrepareStatement�����е�Sql���Ĳ���
     *
     * @throws SQLException
     */

    private void setPrepareStatementParams(String sql, Object[] params) throws SQLException {
        pstm = conn.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstm.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * ִ�в�ѯ
     *
     * @param sql    sql���
     * @param params �����б�
     * @return ����ResultSet���͵Ĳ�ѯ���
     * @throws SQLException
     */
    public ResultSet executeQuery(String sql, Object[] params)
            throws SQLException { // ִ�в�ѯ���ݿ�ӿ�

        util.setPrepareStatementParams(sql, params); // ������
        rs = pstm.executeQuery(); // ִ�в�ѯ����
        return rs;
    }

    /**
     * ִ�����ݵ���ɾ��
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql, Object[] params)
            throws SQLException // ִ���޷������ݵ����ݲ�ѯ������ֵ�Ǳ��ı���������ݿ�����
    {
        int result = -1;
        util.setPrepareStatementParams(sql, params); // ������
        pstm.executeUpdate(); // ִ�и���
        result = 1;
        return result;
    }

    /*
     * ִ�����ݵĲ���
     */
    public int executeInsert(String sql, Object[] params) throws SQLException {
        int result = -1;
        util.setPrepareStatementParams(sql, params);
        pstm.execute();
        result = 1;
        return result;
    }
}

	
	