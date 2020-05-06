package com.jokenpo.game.repository;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Modifying
    @Query("DELETE FROM Action WHERE source = :tool OR target = :tool")
    void deleteActionByTool(@Param("tool") Tool tool);

}
