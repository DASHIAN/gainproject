package com.lec.persistence;

import com.lec.domain.ExamId;
import com.lec.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	@Transactional
	List<Reply> findBySeq(@Param("seq")Long seq);
    
    // 단일댓글수정
	@Transactional
	Reply findBySeqAndRno(@Param("seq") Long seq, @Param("rno") int rno);
	
    @Modifying
    @Transactional
    @Query("update Reply r set r.writer = :writer, r.content = :content where  r.seq = :seq and r.rno = :rno")
    void updateReply(@Param("seq") Long seq, @Param("rno") int rno, @Param("writer") String writer, @Param("content") String content);

    @Modifying
    @Transactional
    @Query("delete from Reply r where r.seq = :seq and r.rno = :rno")
    //void deleteReply(@Param("seq") Long seq,@Param("rno") int rno);
    void deleteById(@Param("seq") Long seq,@Param("rno") int rno);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Reply(seq, writer, content, reg_date) VALUES (:seq, :writer, :content, :reg_date)", nativeQuery = true)
    void write(@Param("seq") Long seq, @Param("writer") String writer, @Param("content") String content, @Param("reg_date") Date regDate);
    
    @Transactional
    @Modifying
	void deleteByExamIdSeq(Long seq);
    
    @Transactional(readOnly = true)
    Long countByExamId_Seq(Long seq);



	

	

	






	
}

