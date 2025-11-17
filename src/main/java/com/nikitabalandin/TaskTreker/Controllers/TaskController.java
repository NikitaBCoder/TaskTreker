package com.nikitabalandin.TaskTreker.Controllers;


import com.nikitabalandin.TaskTreker.TaskStatus;
import com.nikitabalandin.TaskTreker.model.TaskItem;
import com.nikitabalandin.TaskTreker.Repositories.TaskItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController implements CommandLineRunner {

    private final TaskItemRepository taskItemRepository;

    public TaskController(TaskItemRepository taskItemRepository) {
        this.taskItemRepository = taskItemRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<TaskItem> allTask = taskItemRepository.findAll();
        model.addAttribute("taskItems", allTask);
        model.addAttribute("newTask", new TaskItem());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute TaskItem taskItem) {

        taskItem.setStatus(String.valueOf(TaskStatus.To_Do));
        taskItemRepository.save(taskItem);

        return "redirect:/";
    }
    @PostMapping("/changeInProgress/{id}")
    public String changeInProgress(@PathVariable("id") Long id) {
        Optional<TaskItem> Optionaltask = taskItemRepository.findById(id);
        TaskItem task = Optionaltask.get();
        task.setStatus(String.valueOf(TaskStatus.IN_PROGRESS));
        taskItemRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/changeDone/{id}")
    public String changeDone(@PathVariable("id") Long id) {
        Optional<TaskItem> Optionaltask = taskItemRepository.findById(id);
        TaskItem task = Optionaltask.get();
        task.setStatus(String.valueOf(TaskStatus.Done));
        taskItemRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTaskItem(@PathVariable("id") Long id) {
        taskItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/removeAll")
    public String removeAllTaskItem() {
        taskItemRepository.deleteAll();
        return "redirect:/";
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
