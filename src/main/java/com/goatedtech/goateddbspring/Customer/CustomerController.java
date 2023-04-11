package com.goatedtech.goateddbspring.Customer;

import com.goatedtech.goateddbspring.RecordLibrary.RecordLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private RecordLibrary recordLibrary = new RecordLibrary();

    @GetMapping("/customers")
    public String showCustomersList(Model model) {
//        List<Customer> listCustomers = recordLibrary.getCustomerList();
//        model.addAttribute("listCustomers", listCustomers);
        String tstMsg = recordLibrary.tstMsg();
        model.addAttribute("tst_msg", tstMsg);
        return "customers";
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
