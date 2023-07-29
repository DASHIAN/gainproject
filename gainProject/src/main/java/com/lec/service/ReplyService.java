package com.lec.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.ExamId;
import com.lec.domain.Reply;

@Service
public interface ReplyService {
	// 댓글 조회
	List<Reply> replylist(Long seq) throws Exception;
	
	// 단일 댓글 조회
	Reply getReply(Long seq, int rno) throws Exception;

	// 댓글 작성
	void write(Long seq, String writer, String content, Date regDate) throws Exception;

	// 댓글 수정
	void modifyReply(Long seq, int rno, String writer, String content) throws Exception;

	// 단일 댓글삭제
	void deleteReply(Long seq, int rno) throws Exception;

	// 게시글 댓글 삭제
	void eraseReplyBySeq(Long seq);
	
	Long getReplyCountBySeq(Long seq);

	

	

	






}
