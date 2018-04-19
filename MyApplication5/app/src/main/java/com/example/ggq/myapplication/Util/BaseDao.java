package com.example.ggq.myapplication.Util;

import java.sql.SQLException;

public class BaseDao {

    public Jdbcutil util = null;

    public BaseDao() {
        try {
            util = Jdbcutil.createInstance();
            util.connectDB();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() {
        //util.closeConn();
        try {
            super.finalize();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}