package org.kidding.web;




import org.kidding.dao.BoardDAO;
import org.kidding.domain.BoardVO;
import org.kidding.domain.PageVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/list")
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardDAO dao = new BoardDAO();
        String page1 = req.getParameter("page");
        int page = -1;

        if (page1 == null) {
            page = 0;

        } else{
            page = Integer.parseInt(page1)-1;
        }

        System.out.println("----------------------------------------------page: " + page);


        //해당 페이지 리스트
        List<BoardVO> boardList = dao.listContent(page);

        req.setAttribute("boardList", boardList);
        req.setAttribute("page", page + 1);

        //페이지 전체 길이
        List<BoardVO> pageList = dao.pagelist();

        int listLength = (int) Math.ceil((float)pageList.size()/10);
        req.setAttribute("listLength", listLength);

        //페이지 계산
        //페이지 계산
        PageVO pageVO = new PageVO(boardList);
        System.out.println("page----------------------"+page);
        int endPage = pageVO.getendPage(page+1);
        int startPage = pageVO.getstartPage(page+1);
        int beforePage = pageVO.getBeforePage(page+1);
        int afterPage = pageVO.getAfterPage(page+1);
        req.setAttribute("endPage", endPage);
        req.setAttribute("startPage",startPage);
        req.setAttribute("beforePage", beforePage);
        req.setAttribute("afterPage", afterPage);

        //dao.totalCount()가 전체 게시글 개수 구한 거야.; 4시 14분에 보낸 쿼리문 참고
        int totalCount = dao.totalCount();
        int totalPage = ((totalCount-1)/10) + 1;

        req.setAttribute("totalPage", totalPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("startPage",startPage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/list.jsp");
        dispatcher.forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int bno = Integer.parseInt(req.getParameter("bno"));
        BoardDAO dao = new BoardDAO();

        dao.removeContent(bno);

        resp.sendRedirect("/list");
    }

}
