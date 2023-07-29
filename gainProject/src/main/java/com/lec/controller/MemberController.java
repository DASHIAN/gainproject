package com.lec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lec.domain.Member;
import com.lec.domain.PagingInfo;
import com.lec.service.MemberService;

@Controller
@SessionAttributes("pagingInfo")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	public PagingInfo pagingInfo = new PagingInfo();
	
	@GetMapping("getMemberList") // localhost:8088/getBoardList?curPage=1&rowSizePer=....
	public String getMemberList(Model model,
			@RequestParam(defaultValue = "0") int curPage,
			@RequestParam(defaultValue = "10") int rowSizePerPage,
			@RequestParam(defaultValue = "name") String searchType,
			@RequestParam(defaultValue = "") String searchWord) {
		
		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(searchType).ascending());
		Page<Member> pagedResult = memberService.getMemberList(pageable, searchType, searchWord);
		
		int totalRowCount  = pagedResult.getNumberOfElements();
		int totalPageCount = pagedResult.getTotalPages();
		int pageSize       = pagingInfo.getPageSize();
		int startPage      = curPage / pageSize * pageSize + 1;
		int endPage        = startPage + pageSize + 1;
		endPage = endPage > totalPageCount ? (totalPageCount > 0 ? totalPageCount : 1) : endPage;
		
		pagingInfo.setCurPage(curPage);
		pagingInfo.setTotalRowCount(totalRowCount);
		pagingInfo.setTotalPageCount(totalPageCount);
		pagingInfo.setStartPage(startPage);
		pagingInfo.setEndPage(endPage);
		pagingInfo.setSearchType(searchType);
		pagingInfo.setSearchWord(searchWord);
		model.addAttribute("pagingInfo", pagingInfo);
		
		model.addAttribute("pagedResult", pagedResult);
		model.addAttribute("pageable", pageable);
		model.addAttribute("cp", curPage);
		model.addAttribute("sp", startPage);
		model.addAttribute("ep", endPage);
		model.addAttribute("ps", pageSize);
		model.addAttribute("rp", rowSizePerPage);
		model.addAttribute("tp", totalPageCount);
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);
		
		return "member/getMemberList";
	}
	
	@GetMapping("insertMember")
	public String insertMemberForm(Member member) {
		return "member/insertMember";
	}
	
	@PostMapping("insertMember")
	public String insertMember(Member member) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}
		member.setRole(member.getRole() != null ? "ADMIN" : "USER");
		memberService.insertMember(member);
		
		return "redirect:getMemberList";
	}
	@GetMapping("registerMember")
	public String registerMemberForm(Member member) {
		return "member/registerMember";
	}
	
	@PostMapping("registerMember")
	public String registerMember(Member member) {
		
		if(member.getId() == null) {
			return "redirect:registerMember";
		}
		member.setRole(member.getRole() != null ? "ADMIN" : "USER");
		memberService.registerMember(member);
		
		return "redirect:registerSuccess";
	}
	@GetMapping("deleteMember")
	public String deleteMember(Member member) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}
		memberService.deleteMember(member);
		return "redirect:getMemberList";
	}
	@GetMapping("eraseMember")
	public String eraseMember(Member member) {
		
		if(member.getId() == null) {
			return "redirect:login";
		}
		memberService.eraseMember(member);
		return "redirect:login";
	}
	
	@GetMapping("updateMember")
	public String updateMember(Member member, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		model.addAttribute("member", memberService.getMember(member));
		return "member/updateMember";
	}
	@PostMapping("updateMember")
	public String updateMember(Member member) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		member.setRole(member.getRole() != null ? "ADMIN" : "USER");
		memberService.updateMember(member);
	      return "redirect:getMemberList?curPage=" + pagingInfo.getCurPage() + "&rowSizePerPage=" 
          + pagingInfo.getRowSizePerPage() + "&searchType=" + pagingInfo.getSearchType() 
          + "&searchWord=" + pagingInfo.getSearchWord();
	}
	@GetMapping("modifyMember")
	public String modifyMember(Member member, Model model) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		model.addAttribute("member", memberService.getMember(member));
		return "member/modifyMember";
	}
	
	@PostMapping("modifyMember")
	public String modifyMember(Member member) {
		if(member.getId() == null) {
			return "redirect:login";
		}
		member.setRole(member.getRole() != null ? "ADMIN" : "USER");
		memberService.modifyMember(member);
		
	      return "redirect:login";
	}
			// 아이디 중복 검사
			@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
			@ResponseBody
			public String memberIdChkPOST(String Id) throws Exception{
				
//				logger.info("memberIdChk() 진입");
				logger.info("memberIdChk() 진입");
				
				int result = memberService.idCheck(Id);
				
				logger.info("결과값 = " + result);
				
				if(result != 0) {
					return "fail";	// 중복 아이디가 존재
				} else {
					return "success";	// 중복 아이디 x
				}	
				
			} // memberIdChkPOST() 종료	
	
}








