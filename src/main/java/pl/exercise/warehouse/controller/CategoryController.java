package pl.exercise.warehouse.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.exercise.warehouse.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryServicel;
}
