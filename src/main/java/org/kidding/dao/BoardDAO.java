package org.kidding.dao;

import org.kidding.domain.BoardVO;

import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    //addhit
    public void addHit(final int bno){
        final String sql = "update tbl_board set hit = hit + 1 where bno = ?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, bno);
                stmt.executeUpdate();
            }
        }.executeAll();
    }

    //totalcount
    public int totalCount(){
        final int[] totalCount = new int[1];
        final List<BoardVO> list = new ArrayList<BoardVO>();
        final String sql ="select count(*) from tbl_board";
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    totalCount[0] = rs.getInt(1);
                }
            }
        }.executeAll();
        return totalCount[0];
    }
    //pageList
    public List<BoardVO> pagelist(){
        final List<BoardVO> list = new ArrayList<BoardVO>();
        final String sql ="select /*+INDEX_DESC(tbl_board pk_board) */ bno, title, mname, regdate from tbl_board";
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    BoardVO vo = new BoardVO();
                    vo.setBno(rs.getInt(1));
                    vo.setTitle(rs.getString(2));
                    vo.setMname(rs.getString(3));
                    vo.setRegdate(rs.getDate(4));
                    list.add(vo);
                }
            }
        }.executeAll();
        return list;
    }

    //List
    public List<BoardVO> listContent(final int page){

        final List<BoardVO> list = new ArrayList<BoardVO>();
        final String sql ="select B.rn, B.bno, B.title, B.mname, B.hit, B.regdate" +
                " from (select /*+index_desc(A.bno)*/ " +
                "rownum as rn, A.bno, A.title, A.mname, A.hit, A.regdate " +
                "from( select bno, title, mname, hit, regdate " +
                "from tbl_board order by bno desc) A" +
                " where rownum <= ( ? * 10 ) + 10) B" +
                " where B.rn >= ?*10 + 1";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, page);
                stmt.setInt(2, page);
                rs = stmt.executeQuery();

                while(rs.next()){
                    BoardVO vo = new BoardVO();
                    vo.setBno(rs.getInt(2));
                    vo.setTitle(rs.getString(3));
                    vo.setMname(rs.getString(4));
                    vo.setHit(rs.getInt(5));
                    vo.setRegdate(rs.getDate(6));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }


    //read
    public BoardVO readContent(final int bno){
        final String sql = "select * from tbl_board where bno=?";
        final BoardVO vo=new BoardVO();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, bno);
                rs=stmt.executeQuery();

                while(rs.next()){
                    vo.setBno(rs.getInt("bno"));
                    vo.setMname(rs.getString("mname"));
                    vo.setTitle(rs.getString("title"));
                    vo.setContent(rs.getString("content"));
                    vo.setHit(rs.getInt("hit"));
                    vo.setRegdate(rs.getDate("regdate"));
                }
            }
        }.executeAll();
        return vo;
    }
    //write
    public void writeContent(final BoardVO vo){
        final String sql = "insert into tbl_board (bno, mname, title, content)\n" +
                "values (seq_board.nextval, ?, ?, ?)";


        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setString(1, vo.getMname());
                stmt.setString(2,vo.getTitle());
                stmt.setString(3,vo.getContent());
                stmt.executeUpdate();
            }
        }.executeAll();
    }

    //modify
    public void modifyContent(final BoardVO vo){

        final String sql = "update tbl_board set title =?, content =? where bno =?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setString(1, vo.getTitle());
                stmt.setString(2, vo.getContent());
                stmt.setInt(3, vo.getBno());
                stmt.executeUpdate();
            }
        }.executeAll();
    }
    //remove
    public void removeContent(final int bno){
        final String sql = "delete from tbl_board where bno = ?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, bno);
                stmt.executeUpdate();
            }
        }.executeAll();
    }

}
