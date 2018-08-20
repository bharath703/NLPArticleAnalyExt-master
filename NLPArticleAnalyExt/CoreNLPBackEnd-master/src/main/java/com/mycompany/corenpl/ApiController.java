/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.corenpl;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

/**
 *
 * @author Bharath
 */
@RestController
public class ApiController {
    @RequestMapping("/analyze")
    public Response greeting(@RequestParam(value = "url", defaultValue = "") String url, 
           @RequestParam(value = "company") ArrayList<String> company ) {
        PortfolioNewsAnalyzer porfolioAnalyzer = new PortfolioNewsAnalyzer();
        try {
            porfolioAnalyzer.addPortfolioCompany(company);
            return porfolioAnalyzer.analyzeArticle(url);
        } catch (IOException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BoilerpipeProcessingException ex) {
            Logger.getLogger(ApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response(false, "", Collections.EMPTY_LIST);
    }
}
