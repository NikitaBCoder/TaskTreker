package com.nikitabalandin.TaskTreker.Repositories;

import com.nikitabalandin.TaskTreker.model.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskItemRepository extends  JpaRepository<TaskItem, Long> {
}
