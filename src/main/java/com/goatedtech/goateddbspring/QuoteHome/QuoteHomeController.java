package com.goatedtech.goateddbspring.QuoteAuto;

import com.goatedtech.goateddbspring.DBManager;
import com.goatedtech.goateddbspring.QuoteHome.QuoteHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller for home quote HTTP request mappings
 */
@Controller
public class QuoteHomeController {

    /**
     * Gets home quote view page request
     * @param model
     * @return
     */
    @GetMapping("/quotehome_view")
    public String quoteHomeList(Model model) {
        List<QuoteHome> quoteHomeList = DBManager.recordLibrary.getHomeList();
        model.addAttribute("listQuoteHome", quoteHomeList);
        return "quotehome_view";
    }

    /**
     * Gets home quote form page request
     * @param model
     * @return
     */
    @GetMapping("/quotehome_form")
    public String quoteHomeForm(Model model) {
        model.addAttribute("quoteHome", new QuoteHome());
        return "quotehome_form";
    }

    /**
     * Posts home quote add request
     * @param quoteHome
     * @return
     */
    @PostMapping("/quotehome_form/add")
    public String quoteHomeAdd(QuoteHome quoteHome) {
        DBManager.recordLibrary.addQuoteHome(quoteHome);
        DBManager.addQuoteHome(
                quoteHome.getId(),
                quoteHome.getHomeAge(),
                quoteHome.getHeatingType(),
                quoteHome.getDwellingType());
        return "redirect:/";
    }

}
