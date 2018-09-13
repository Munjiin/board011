package org.kidding.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardVO {

    private int bno, rownum;
    private String title, content, mname;
    private Date regdate, updatedate;
    private int hit;

}