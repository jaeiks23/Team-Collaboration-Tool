package DTO;

import java.sql.Date;

public class BoardDTO {

   int no_Num;
   int visit_count;
   String writer_id;
   String title;
   String content;
   String sendId;
   String Ofile;
   String Nfile; 
   Date BoardDate;
   

    public int getNo_Num() {
    	return no_Num;
	}
	public void setNo_Num(int no_Num) {
		this.no_Num = no_Num;
	}
	public int getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(int visit_count) {
		this.visit_count = visit_count;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getSendId() {
      return sendId;
   }
   public void setSendId(String sendId) {
      this.sendId = sendId;
   }
   public String getOfile() {
      return Ofile;
   }
   public void setOfile(String ofile) {
      Ofile = ofile;
   }
   public String getNfile() {
      return Nfile;
   }
   public void setNfile(String nfile) {
      Nfile = nfile;
   }
   public Date getBoardDate() {
      return BoardDate;
   }
   public void setBoardDate(Date BoardDate) {
      this.BoardDate = BoardDate;
   }
}
