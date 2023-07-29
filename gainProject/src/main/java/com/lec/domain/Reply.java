package com.lec.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@IdClass(ExamId.class)
public class Reply {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Long seq;

	@Id
	@Column(insertable = false, updatable = false)
	private int rno;

	@Column(updatable = false)
	private String writer;

	private String content;

	@Column(name = "reg_date", insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date regDate;
	
	@EmbeddedId
    private ExamId examId;
	
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "seq", insertable = false, updatable = false)	
//	private Board board;

}
