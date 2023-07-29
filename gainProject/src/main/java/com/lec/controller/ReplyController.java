package com.lec.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lec.domain.Board;
import com.lec.domain.Member;
import com.lec.domain.PagingInfo;
import com.lec.domain.Reply;
import com.lec.service.BoardService;
import com.lec.service.ReplyService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
@SessionAttributes({"member", "pagingInfo"})
public class ReplyController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	@ModelAttribute("board")
	public Board setBoard() {
		return new Board();
	}
	
	

// 댓글 작성
	@PostMapping("/writeReply")
	public String postWrite(@ModelAttribute("board") Board board, @Param("seq") Long seq, @Param("writer") String writer, @Param("content") String content, @Param("reg_date") Date regDate, Model model) throws Exception {
		Reply reply = new Reply();
	    reply.setSeq(seq);
	    replyService.write(seq, writer, content, regDate);
	    return "redirect:/getBoard?seq=" + board.getSeq();
	}

	// 댓글 단일 조회 (수정 페이지)
	@GetMapping("/modifyReply")
	public String getModify(@ModelAttribute("board") Board board,@ModelAttribute("reply") Reply reply, @ModelAttribute("member") Member member, @Param("seq") Long seq, @Param("rno") int rno, Model model) throws Exception {
		if (member.getId() == null) {
	        return "redirect:/login";
	    }
		model.addAttribute("reply", replyService.getReply(seq, rno));
	    return "reply/modifyReply";
	}
	// 댓글 수정
	@PostMapping("/modifyReply")
	public String postModify(@ModelAttribute("board") Board board, Member member, @ModelAttribute("reply") Reply reply) throws Exception {
		if (member.getId() == null) {
			return "redirect:login";
		}
		
		replyService.modifyReply(reply.getSeq(), reply.getRno(), reply.getWriter(), reply.getContent());
		return "redirect:/getBoard?seq=" + board.getSeq();
	}
	// 댓글 삭제
	@GetMapping("/deleteReply")
	public String getDelete(@ModelAttribute("board") Board board, @ModelAttribute("reply") Reply reply, @ModelAttribute("member") Member member, @Param("seq") Long seq, @Param("rno") int rno, Model model) throws Exception {
		if (member.getId() == null) {
			return "redirect:login";
		}

		replyService.deleteReply(seq,rno);
	    return "redirect:/getBoard?seq=" + board.getSeq();
	}
	
}
