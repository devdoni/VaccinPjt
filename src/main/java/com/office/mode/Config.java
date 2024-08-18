package com.office.mode;

public class Config {


    // COMMAND
    public static final String ENCODING = "UTF-8";
    // view path
    public final static String BASIC_VIEW_PATH = "res/view";
    public final static String BASIC_ADMIN_VIEW_PATH = BASIC_VIEW_PATH + "/admin";
    public final static String BASIC_USER_VIEW_PATH = BASIC_VIEW_PATH + "/user";

    // view file suffix
    public final static String SUFFIX = ".jsp";

    // DB INFO.
    public final static String DB_DRIVER_NAME 	= "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL 			= "jdbc:mysql://localhost:3306/db_vaccin";
    public final static String DB_USER_ID 		= "root";
    public final static String DB_USER_PW 		= "1234";

    // loginedID in session
    public final static String USER_LOGINED_SESSION_ID 	= "signinedUserID";
    public final static String ADMIN_LOGINED_SESSION_ID = "signinedAdminID";
}
