package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuideLineCommand;
import vn.myclass.core.dto.ListenGuideLineDTO;
import vn.myclass.core.service.ListenGuideLineService;
import vn.myclass.core.service.impl.ListenGuideLineServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.ListenGuideLineBeanUtil;
import vn.myclass.core.web.utils.RequestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuideLineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuideLineCommand lineCommand=new ListenGuideLineCommand();
        ListenGuideLineService service=new ListenGuideLineServiceImpl();
        /*List<ListenGuideLineDTO> listenGuideLineDTOS=new ArrayList<ListenGuideLineDTO>();
        ListenGuideLineDTO dto1=new ListenGuideLineDTO();
       dto1.setTitle("Bai hd nghe 1");
        dto1.setContent("Noi dung bai hd nghe 1");
        ListenGuideLineDTO dto2=new ListenGuideLineDTO();
        dto1.setTitle("Bai hd nghe 2");
        dto1.setContent("Noi dung bai hd nghe 2");
        ListenGuideLineDTO dto3=new ListenGuideLineDTO();
        dto1.setTitle("Bai hd nghe 3");
        dto1.setContent("Noi dung bai hd nghe 3");
        listenGuideLineDTOS.add(dto1);
        listenGuideLineDTOS.add(dto2);
        listenGuideLineDTOS.add(dto3);*/
        /*lineCommand.setListResult(listenGuideLineDTOS);
        lineCommand.setMaxPageItems(1);// số items trong 1 trang
        lineCommand.setTotalItems(listenGuideLineDTOS.size());
        req.setAttribute(WebConstant.LIST_ITEMS,lineCommand);*/
lineCommand.setMaxPageItems(2);
       /* RequestUtils.initSearchBean(req,lineCommand);

        Object[] objects=service.findListenGuideLineByProperties(null,lineCommand.getSortDirection(),lineCommand.getSortExpression(),null,lineCommand.getFirstItem(),lineCommand.getMaxPageItems());
        lineCommand.setListResult((List<ListenGuideLineDTO>) objects[1]);
        lineCommand.setTotalItems(Integer.parseInt(objects[0].toString()));
        req.setAttribute(WebConstant.LIST_ITEMS,lineCommand);*/
        RequestDispatcher rd=req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
