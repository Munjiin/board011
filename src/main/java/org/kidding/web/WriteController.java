package org.kidding.web;


import org.kidding.dao.BoardDAO;
import org.kidding.domain.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/write")
public class WriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/write.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        BoardDAO boardDAO=new BoardDAO();

        System.out.println("-------------------------------------7");
        BoardVO vo = new BoardVO();
        String title = req.getParameter("title");
        String mname = req.getParameter("mname");
        String content = req.getParameter("content");
        System.out.println("-------------------------------------8");
        System.out.println(vo);

        vo.setTitle(title);
        vo.setMname(mname);
        vo.setContent(content);

        boardDAO.writeContent(vo);

        resp.sendRedirect("/list");
}
}