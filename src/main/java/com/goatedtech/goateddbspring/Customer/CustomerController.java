package com.goatedtech.goateddbspring.Customer;

import com.goatedtech.goateddbspring.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class CustomerController {

    @GetMapping("/customer_view")
    public String customerList(Model model) {
        List<Customer> customerList = DBManager.recordLibrary.getCustomerList();
        model.addAttribute("listCustomers", customerList);
        return "customer_view";
    }

    @GetMapping("/customer_form")
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    @GetMapping("/customer_update")
    public String customerUpdate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_update";
    }

    @PostMapping("/customer_form/post")
    public String customerAdd(Customer customer) {
        DBManager.recordLibrary.addCustomer(customer);
        DBManager.addCustomer(
                customer.getId(),
                customer.getNameLast(),
                customer.getNameFirst());
        return "redirect:/";
    }

    @PutMapping("/customer_update/put")
    public String customerUpdate(Customer customer) {
        DBManager.updateCustomer(
                customer.getId(),
                customer.getNameLast(),
                customer.getNameFirst()
        );
        return "redirect:/";
    }

    @DeleteMapping("/customer_deleteall")
    public String customerDeleteAll() {
        DBManager.deleteAllCustomers();
        return "redirect:/";
    }

//    @GetMapping("/authors/new")
//    public String showNewAuthorForm(Model model) {
//        model.addAttribute("author", new Author());
//
//        return "new_author";
//    }
//
//    @PostMapping("/authors/save")
//    public String saveAuthor(Author author) {
//        service.save(author);
//        return "redirect:/authors";
//    }



}