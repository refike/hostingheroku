package com.exercise.todoprojekt.application.Controllers;

import com.exercise.todoprojekt.application.Repository.model.Todo;
import com.exercise.todoprojekt.application.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/todo")
public class Todocontroller {

    private TodoRepository todoRepository;

    @Autowired
    public Todocontroller(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @GetMapping({"", "/", "/list"})
    public String list(Model model, @RequestParam(name = "isActive", required = false) Boolean isActive,
                                    @RequestParam(name = "desc", required = false) Long id,
                                    @RequestParam(value = "search", required = false) String search) {

        model.addAttribute("searchstring", search);

        if (id != null && todoRepository.findById(id).isPresent()) {
            model.addAttribute("desc", todoRepository.findById(id).get());
            model.addAttribute("todos", todoRepository.findAllByTitleContainsOrDescriptionContains(search, search));
            System.out.println("descnsearch");
            return "todolist";
        }

        if (search != null) {
            model.addAttribute("todos", todoRepository.findAllByTitleContainsOrDescriptionContains(search, search));
            System.out.println("search");
            return "todolist";
        }

        if (isActive != null && isActive) {
            model.addAttribute("todos", todoRepository.findByDone(!isActive));
            System.out.println("active");
        } else {
            model.addAttribute("todos", todoRepository.findAllByOrderByDateDesc());
            System.out.println("all");
        }
        return "todolist";
    }

    @GetMapping("/add")
    public String addTodo(Model model, @ModelAttribute(name = "mytodo") Todo todo) {
        model.addAttribute("mytodo", todo);
        return "createtodo";
    }

    @PostMapping("/add")
    public String postTodo(@ModelAttribute(name = "mytodo") Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todo/list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        todoRepository.deleteById(id);
        return "redirect:/todo/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable long id) {

        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            model.addAttribute("edittodo", optionalTodo.get());
        } else {
            model.addAttribute("edittodo", new Todo());
        }

        return "edit";
    }


    @PostMapping("/{id}/edit")
    public String postEdit(@ModelAttribute("edittodo") Todo todo) {
        todoRepository.save(todo);
        return "redirect:/todo/list";
    }


}