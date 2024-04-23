package com.example.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("FROM Message WHERE posted_by = :userId")
    List<Message> findByPostedBy(@Param("userId") Integer userId);

    @Query("SELECT m FROM Message m WHERE m.posted_by = :accountId")
    List<Message> findByPostedBy(@Param("accountId") int postedBy);

    @Query("From Message WHERE message_id = :messageId")
    Optional<Message> findMessageById(@Param("messageId") Integer message_id);

    @Query("From Message WHERE message_id = :messageId")
    List<Message> findMessageByIdAgain(@Param("messageId") Integer message_id);
}
