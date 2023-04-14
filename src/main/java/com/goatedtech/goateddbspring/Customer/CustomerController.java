package com.goatedtech.goateddbspring.Customer;

import com.goatedtech.goateddbspring.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * Controller for customer HTTP requests
 */
@Controller
public class CustomerController {

    /**
     * Gets customer view page
     * @param model
     * @return
     */
    @GetMapping("/customer_view")
    public String customerList(Model model) {
        List<Customer> customerList = DBManager.recordLibrary.getCustomerList();
        model.addAttribute("listCustomers", customerList);
        return "customer_view";
    }

    /**
     * Gets customer form page
     * @param model
     * @return
     */
    @GetMapping("/customer_form")
    public String customerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    /**
     * Gets customer update page
     * @param model
     * @return
     */
    @GetMapping("/customer_update")
    public String customerUpdate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_update";
    }

    /**
     * Posts customer form submission
     * @param customer
     * @return
     */
    @PostMapping("/customer_form/post")
    public String customerAdd(Customer customer) {
        DBManager.recordLibrary.addCustomer(customer);
        DBManager.addCustomer(
                customer.getId(),
                customer.getNameLast(),
                customer.getNameFirst());
        return "redirect:/";
    }

    /**
     * Posts customer update submission
     * @param customer
     * @return
     */
    @PutMapping("/customer_update/put")
    public String customerUpdate(Customer customer) {
        DBManager.updateCustomer(
                customer.getId(),
                customer.getNameLast(),
                customer.getNameFirst()
        );
        return "redirect:/";
    }

    /**
     * Deletes customer submission
     * @return
     */
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
