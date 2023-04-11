package com.goatedtech.goateddbspring.QuoteAuto;

import com.goatedtech.goateddbspring.DBManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuoteAutoController {

    @GetMapping("/quoteauto_view")
    public String quoteAutoList(Model model) {
        List<QuoteAuto> quoteAutoList = DBManager.recordLibrary.getAutoList();
        model.addAttribute("listQuoteAuto", quoteAutoList);
        return "quoteauto_view";
    }

    @GetMapping("/quoteauto_form")
    public String quoteAutoForm(Model model) {
        model.addAttribute("quoteAuto", new QuoteAuto());
        return "quoteauto_form";
    }

    @PostMapping("/quoteauto_form/add")
    public String quoteAutoAdd(QuoteAuto quoteAuto) {
        DBManager.recordLibrary.addQuoteAuto(quoteAuto);
        DBManager.addQuoteAuto(
                quoteAuto.getId(),
                quoteAuto.getCarValue(),
                quoteAuto.getDriverAge(),
                quoteAuto.getVehicleAge(),
                quoteAuto.getAccidents(),
                quoteAuto.getLocation());
        return "redirect:/";
    }

}
