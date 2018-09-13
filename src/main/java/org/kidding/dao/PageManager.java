package org.kidding.dao;

public class PageManager {
    public int pageSize = 10; // 페이지 당 글 개수
    public int totalPage;  // 총 페이지 개수
    public int totalCount; // 총 글 개수

    public int currentPage = 0;

    public PageManager(int pageSize, int totalPage, int totalCount,  int currentPage) {
        super();
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
    }


    public int getTotalPage()
    {
        return ( (totalCount - 1) / pageSize) + 1;
    }

    public int getStartPage()
    {
        return ( ( currentPage - 1 ) / totalPage) * totalPage + 1;
    }

    public int getEndPage()
    {
        return Math.min( getStartPage() + totalPage - 1 , getTotalPage() );
    }

    public boolean isPre()
    {
        return getStartPage() != 1;
    }

    public boolean isNext()
    {
        return getEndPage() < getTotalPage();
    }

    public int getWriting_Start()
    {
        return getWriting_End() - pageSize + 1;
    }

    public int getWriting_End()
    {
        return currentPage * pageSize;
    }

    public static void main(String[] args) {

        BoardDAO dao = new BoardDAO();
        int totalCount = dao.totalCount();

        // 여러가지 매개변수로 테스트 해보시기 바랍니다.
        PageManager pg = new PageManager(5, 5, 26 , 6 );
        // 총 글의 갯수는 select count(*) from board 하면 나오겠죠 ,
        //현재 보고 있는 페이지 번호는 Default 1, 그리고 밑에 페이징에서 링크 걸린 i가 현재 페이지가 됩니다.

        //Paging pg = new Paging(한 화면에 보여질 글 수 , 페이지 분할 수 , 총 글의 갯수  , 현재 보고 있는 페이지 번호  );

        System.out.println("총 페이지 개수 : "+pg.getTotalPage());
        System.out.println("페이지 시작 숫자  : "+pg.getStartPage());
        System.out.println("페이지 마지막 숫자  : "+pg.getEndPage());
        System.out.println("Pre 표시 여부  : "+pg.isPre());
        System.out.println("Next 표시 여부   : "+pg.isNext());
        System.out.println("글 범위 시작 번호   : "+pg.getWriting_Start());
        System.out.println("글 범위 끝 번호   : "+pg.getWriting_End());

        System.out.println("select * from board where no between "+pg.getWriting_Start()+" and "+pg.getWriting_End());
        // 이 셀렉트 결과를 화면에 뿌린 후에


        // 밑에서 페이징을 하면 되겠죠? 이거에 링크를 걸고 i가 현재 페이지 번호로서 링크가 걸리게 되겠죠?
        if(pg.isPre())
            System.out.print(" Pre ");
        for(int i = pg.getStartPage(); i <= pg.getEndPage(); i++)
        {
            System.out.print(" "+i+" ");
        }
        if(pg.isNext())
            System.out.print(" Next ");

        // 이런 페이징 클래스를 작성하여 사용하는 것이 여러모로 편리합니다. ~ ㅋㅋ

    }

}

// 총 게시글 개수
//   BoardDAO dao = new BoardDAO();
//    totalCount = dao.totalCount();

//한 페이지에 10개 보여준다고 가정

// 총 페이지 개수 >> getTotalPage
// totalPage = (totalCount-1/10) + 1

// 시작 페이지 번호 구하기>> getStartPage
//  ( ( currentPage - 1 ) / totalPage) * totalPage + 1

// 끝 페이지 번호 구하기
// Math.min( getStartPage() + totalPage - 1 , getTotalPage() )