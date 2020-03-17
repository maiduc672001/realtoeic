package vn.myclass.controller.admin;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.UserService;
import vn.myclass.core.service.impl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FromUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin-user-list.html"})
public class UserController extends HttpServlet {
    UserService service=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command= FromUtil.populate(UserCommand.class,req);
        UserDTO pojo=command.getPojo();
        if(command.getUrlType().equals(WebConstant.URL_LIST)){
            Map<String,Object> mapProperty=new HashMap<String, Object>();
            Object[] objects=service.findByproperty(mapProperty,command.getSortDirection(),command.getSortExpression(),command.getFirstItem(),command.getMaxPageItems());
            command.setListResult((List<UserDTO>) objects[1]);
            command.setTotalItems(Integer.parseInt(objects[0].toString()));
            req.setAttribute(WebConstant.LIST_ITEMS,command);
            RequestDispatcher rd=req.getRequestDispatcher("/views/admin/user/list.jsp");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
