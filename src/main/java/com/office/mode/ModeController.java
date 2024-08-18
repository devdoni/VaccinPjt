package com.office.mode;

import com.office.mode.admin.AdminConfig;
import com.office.mode.user.UserConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;

@WebServlet("*.mod")
public class ModeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String CLASS_NAME = "[ModeController]";
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());

        String nextPage = null;

        switch (command) {
            case UserConfig.USER_MODE:
                System.out.println(CLASS_NAME.concat(UserConfig.USER_MODE));
                nextPage = Config.BASIC_USER_VIEW_PATH.concat("/index").concat(Config.SUFFIX);
                break;

            case AdminConfig.ADMIN_HOME:
                System.out.println(CLASS_NAME.concat(AdminConfig.ADMIN_HOME));
                nextPage = Config.BASIC_ADMIN_VIEW_PATH.concat("/index").concat(Config.SUFFIX);
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
