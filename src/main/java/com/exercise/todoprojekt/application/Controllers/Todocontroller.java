package com.exercise.todoprojekt.application.Controllers;

import com.exercise.todoprojekt.application.Repository.AssigneeRepository;
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

    private final TodoRepository todoRepository;
    private final AssigneeRepository assigneeRepository;

    @Autowired
    public Todocontroller(TodoRepository todoRepository, AssigneeRepository assigneeRepository) {
        this.todoRepository = todoRepository;
        this.assigneeRepository = assigneeRepository;
    }


    @GetMapping(value = {"", "/", "/list"})
    public String list(Model model, @RequestParam(name = "isActive", required = false) Boolean isActive,
                       @RequestParam(name = "desc", required = false) Long id,
                       @RequestParam(value = "search", required = false) String search) {

        model.addAttribute("searchstring", search); //ezmi?

        if (id != null && search.equals("null") && todoRepository.findById(id).isPresent()) {
            model.addAttribute("desc", todoRepository.findById(id).get());
            model.addAttribute("todos", todoRepository.findAllByOrderByDateDesc());
            System.out.println("descnullsearch");
            return "todolist";
        }

        if (id != null && todoRepository.findById(id).isPresent()) {
            model.addAttribute("desc", todoRepository.findById(id).get());
            model.addAttribute("todos", todoRepository.findAllByTitleContainsOrDescriptionContains(search, search)); //miért kell megismételni ezket többször is? illetve erre az IF részre mi szükség ?
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

    @GetMapping(value = "/add")
    public String addTodo(Model model) {
        model.addAttribute("assigneess", assigneeRepository.findAll());
        model.addAttribute("mytodo", new Todo());
        return "createtodo";
    }

    @PostMapping("/add")
    public String postTodo(@ModelAttribute(name = "mytodo") Todo todo, @ModelAttribute("assige") String name) {
        todo.setAssignee(assigneeRepository.findByName(name)); // ez nem kell nekem ha a create-be nem állítok ilyet
        todoRepository.save(todo);
        return "redirect:/todo/list";
    }

    @GetMapping(value = "{id}/delete")
//    public String delete(@PathVariable long id) {
//        todoRepository.deleteById(id);
//        return "redirect:/todo/list";

    public String deleteTodo(@PathVariable Long id) {
        if (todoRepository.findById(id).isPresent()) {
            todoRepository.delete(todoRepository.findById(id).get());
        }
        return "redirect:/todo/list";
    }

    @GetMapping(value = "{id}/edit")
//
//        Optional<Todo> optionalTodo = todoRepository.findById(id);
//        if (optionalTodo.isPresent()) {
//            model.addAttribute("edittodo", optionalTodo.get());
//        } else {
//            model.addAttribute("edittodo", new Todo());
//        }
//
//        return "edit";
    public String edit(Model model, @PathVariable long id) {
        //     Optional<Todo> optionalTodo = todoRepository.findById(id);
        //     if (optionalTodo.isPresent()) {
        model.addAttribute("assignees", assigneeRepository.findAll());
        if (todoRepository.findById(id).isPresent()) {
            model.addAttribute("edittodo", todoRepository.findById(id).get());
        }
        return "edit";
    }


    @PostMapping(value = "{id}/edit")
//    public String postEdit(@ModelAttribute("edittodo") Todo todo) {
//        todoRepository.save(todo);
//        return "redirect:/todo/list";
//    }
    public String editTodoPost(@ModelAttribute("edittodo") Todo todo, @ModelAttribute("assig") String name) {
        todo.setAssignee(assigneeRepository.findByName(name));
        todoRepository.save(todo);
        return "redirect:/todo/list";
    }

}