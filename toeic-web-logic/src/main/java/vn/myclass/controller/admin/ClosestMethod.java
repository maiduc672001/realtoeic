package vn.myclass.controller.admin;

import vn.myclass.command.JqueryMethod;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FromUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin-jquery-method.html")
public class ClosestMethod extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JqueryMethod jqueryMethod= FromUtil.populate(JqueryMethod.class,req);
        if(jqueryMethod!=null){
            if(jqueryMethod.getUrlType().equals(WebConstant.URL_JQUERY)){
                RequestDispatcher rd=req.getRequestDispatcher("/views/admin/colestMethod.jsp");
                rd.forward(req,resp);
            }
        }


    }
}
