package com.lec.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@EntityListeners(BoardListeners.class)
@Getter
@Setter
@ToString
@Entity
public class Board {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq", insertable = false, updatable = false)
	private Long seq;
	
	
	private String title;
	
	@Column(updatable = false)
	private String writer;
	
	private String content;
	
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date createDate;	
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint default 0")
	private Long cnt;
	
	private String fileName;
	
	@Transient
	private MultipartFile uploadFile;	
	
	//@Transient
	//@Column(columnDefinition = "integer default 0", nullable = true)
	private Long board_ref;
	
	//@Transient
	//@Column(columnDefinition = "integer default 0", nullable = true)
	private Long board_lev;
	
	//@Transient
	//@Column(columnDefinition = "integer default 0", nullable = true)
	private Long board_seq;
//	
//	@OneToMany(mappedBy = "board",  cascade = CascadeType.REMOVE) 
//	 private List<Reply> replies = new ArrayList<Reply>();
//
//	@Builder
//	public Board (String title, String writer, String content, Date createDate, Long cnt, String fileName,
//			MultipartFile uploadFile, Long board_ref, Long board_lev, Long board_seq, List<Reply> replies) {
//		this.seq = seq;
//		this.title = title;
//		this.writer = writer;
//		this.content = content;
//		this.createDate = createDate;
//		this.cnt = cnt;
//		this.fileName = fileName;
//		this.uploadFile = uploadFile;
//		this.board_ref = board_ref;
//		this.board_lev = board_lev;
//		this.board_seq = board_seq;
//		this.replies = replies;
//	}
//
//	public Board() {
//		// TODO Auto-generated constructor stub
//	}

	@Column(name = "reply_count", insertable = false, updatable = false)
	private Long replyCount;
	public void setReplyCount(long replyCount) {
		this.replyCount = replyCount;
		
	}

}

