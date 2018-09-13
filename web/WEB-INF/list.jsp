<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-12
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">자유게시판</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    자유롭게 누구나 글을 쓸 수 있습니다.
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>등록일자</th>
                            <th>조회수</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${boardList}" var="board">
                            <tr class="odd gradeX">
                                <td>${board.bno}</td>
                                <td><a href="read?bno=${board.bno}&page=${page}"> ${board.title} </a></td>
                                <td>${board.mname}</td>
                                <td>${board.hit}</td>
                                <td class="center">${board.regdate}</td>
                            </tr>
                            </c:forEach>

                            </tbody>
                    </table>

                        <p class="text-center">
                            <a href="/write"><button type="button" class="btn btn-primary btn-lg btn-block">글쓰기</button></a></p>
                        <div class="text-center">
                            <br>
                            <%--<c:forEach var="i" begin="1" end="${listLength}">--%>
                                <%--<a href="list?page=${i}"><button>${i}</button></a>--%>
                            <%--</c:forEach>--%>

                            <c:choose>
                                <c:when test="${startPage ne 1}">
                                    <a href="list?page=${beforePage }"><button>이전 페이지</button></a>
                                </c:when>
                            </c:choose>
                            <%--<a href="list?page=${page - 10}"><button>이전 페이지</button></a>--%>

                            <c:forEach var="i" begin="${startPage}" end="${endPage}">   <%-- i는 startPage부터 endPage까지--%>
                                <a href="list?page=${i}"><button>${i}</button></a>
                            </c:forEach>

                            <c:choose>
                                <c:when test="${endPage ne totalPage }">
                                    <a href="list?page=${afterPage}"><button>다음페이지</button></a>
                                </c:when>
                            </c:choose>
                            <%--<a href="list?page=${page + 10}"><button>다음페이지</button></a>--%>
                        </div>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

