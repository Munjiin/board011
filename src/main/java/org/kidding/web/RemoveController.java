package org.kidding.web;


import org.kidding.dao.BoardDAO;
import org.kidding.domain.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/remove")
public class RemoveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        System.out.println("-------------------------------------1");
        int bno = Integer.parseInt(req.getParameter("bno"));
        System.out.println("-----------------------------------2" );

        BoardDAO boardDAO = new BoardDAO();


        boardDAO.removeContent(bno);

        resp.sendRedirect("/list");
    }
}