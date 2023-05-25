package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.entity.Student;
import ra.academy.model.service.IStudentService;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("/")
    public String home(Model model) {
        List<Student> list = studentService.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Student ts = studentService.findById(Long.valueOf(id));
        model.addAttribute("student", ts);
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("student") Student s, Model model) {
        studentService.update(s);
        List<Student> list = studentService.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") Student s) {
        studentService.save(s);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer idDel) {
        studentService.deleteById(Long.valueOf(idDel));
        return "redirect:/";
    }
}
