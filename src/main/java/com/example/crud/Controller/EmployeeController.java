package com.example.crud.Controller;

import com.example.crud.Model.Employee;
import com.example.crud.Service.EmployeeService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String getAllEmployees(Model model)
    {
        return viewPaginated(1, "firstName", "asc", model);
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model)
    {
        Employee employee  = new Employee();
        model.addAttribute("employee", employee);
        return "employee_form";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model)
    {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "employee_form";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") Long id)
    {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{id}")
    public String viewPaginated(@PathVariable(value = "id") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model)
    {

        int pageSize = 5;
        Page<Employee> employeesPage = employeeService.getAllEmployeePaginated(pageNo, pageSize,sortField,sortDir);
        List<Employee> employeeList = employeesPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", employeesPage.getTotalPages());
        model.addAttribute("totalItems", employeesPage.getNumberOfElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", employeeList);
        return "index";
    }
}
