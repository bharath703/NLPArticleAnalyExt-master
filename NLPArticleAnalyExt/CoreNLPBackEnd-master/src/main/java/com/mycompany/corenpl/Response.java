/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.corenpl;

import java.util.List;

/**
 *
 * @author Bharath
 */
public class Response {

    private boolean success;
    private String articleText;
    private List<String> companiesPresent;
    
    public Response(boolean success, String articleText, List<String> companiesPresent) {
        this.success = success;
        this.articleText = articleText;
        this.companiesPresent = companiesPresent;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getArticleText() {
        return articleText;
    }
    
    public void setSuccess(boolean  success) {
        this.success = success;
    }
    
    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }
    
    public List<String> getCompaniesPresent() {
        return companiesPresent;
    }
}
