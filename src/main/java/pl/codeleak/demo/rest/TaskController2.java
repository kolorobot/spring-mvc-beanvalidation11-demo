package pl.codeleak.demo.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("rest")
public class TaskController2 {

    @RequestMapping(value = "task2", method = RequestMethod.POST)
    public Task createTask(@Valid @RequestBody Task task) {
        return task;
    }
}
