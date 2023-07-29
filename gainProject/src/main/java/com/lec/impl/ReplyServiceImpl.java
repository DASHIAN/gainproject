package com.lec.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.ExamId;
import com.lec.domain.Reply;
import com.lec.persistence.ReplyRepository;
import com.lec.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{

	

	  @Autowired
	  private final ReplyRepository replyRepository;
	  
	// 댓글 조회
	@Override
	public List<Reply> replylist(Long seq) throws Exception {
	    // 게시글 번호에 해당하는 댓글 조회
	    List<Reply> replyList = replyRepository.findBySeq(seq);
	    // 검색 로직 등 추가 작업 수행
	    return replyList;
	}

	public Reply getReply(Long seq, int rno) throws Exception {
	    // 댓글 번호에 해당하는 특정 댓글 조회
	    Reply reply = replyRepository.findBySeqAndRno(seq, rno);
	    return reply;
	}

	@Override
	public void write(Long seq, String writer, String content, Date regDate) throws Exception {
	    replyRepository.write(seq, writer, content, regDate);
	}
	// 댓글수정
	@Override
	public void modifyReply(Long seq, int rno,  String writer, String content) throws Exception {
		replyRepository.updateReply(seq,rno,writer,content );
		
	}

	@Override
	public void deleteReply(Long seq, int rno) throws Exception {
		 replyRepository.deleteById( seq,rno);
		
	}

	@Override
	public void eraseReplyBySeq(Long seq) {
		 replyRepository.deleteByExamIdSeq(seq);
		
	}

	@Override
	public Long getReplyCountBySeq(Long seq) {
	    return replyRepository.countByExamId_Seq(seq);
	}
	
}
