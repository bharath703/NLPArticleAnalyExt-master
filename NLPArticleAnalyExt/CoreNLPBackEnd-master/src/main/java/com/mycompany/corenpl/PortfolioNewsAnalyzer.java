/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.corenpl;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author Bharath
 */
public class PortfolioNewsAnalyzer {
    private HashSet<String> portfolio;
    private static final String modelPath = "edu\\stanford\\nlp\\models\\pos-tagger\\english-left3words\\english-left3words-distsim.tagger";
    private MaxentTagger tagger;

    public PortfolioNewsAnalyzer() {
        portfolio = new HashSet<String>();
        tagger = new MaxentTagger(modelPath);
    }

    public void addPortfolioCompany(List<String> companies) {
        portfolio.addAll(companies);
    }

    public Response analyzeArticle(String urlString) throws
            IOException,
            SAXException,
            BoilerpipeProcessingException {
        System.out.println("Url string: " + urlString);
        String articleText = extractFromUrl(urlString);
        System.out.println("Article Text: " + articleText);
        String tagged = tagPos(articleText);
        System.out.println("Tagged Text: " + tagged);
        HashSet<String> properNounsSet = PortfolioNewsAnalyzer.extractProperNouns(tagged);
        Response response = new Response(arePortfolioCompaniesMentioned(properNounsSet), 
                articleText, getCompaniesPresent(properNounsSet));
        return response;
    }
    
    public List<String> getCompaniesPresent(HashSet<String> articleProperNouns) {
        List<String> companies = new ArrayList<>();
        Iterator iter = portfolio.iterator();
        while (iter.hasNext()) {
            String ele = iter.next().toString();
            if (articleProperNouns.contains(ele)) {
                System.out.println(ele);
                companies.add(ele);
            }
        }
        return companies;
    }

    public boolean arePortfolioCompaniesMentioned(HashSet<String> articleProperNouns) {
        return !Collections.disjoint(articleProperNouns, portfolio);
    }

    public static HashSet<String> extractProperNouns(String taggedOutput) {
        HashSet<String> propNounSet = new HashSet<String>();
        String[] split = taggedOutput.split(" ");
        List<String> propNounList = new ArrayList<String>();
        for (String token : split) {
            String[] splitTokens = token.split("_");
            if (splitTokens.length < 2) {
                continue;
            }
            if (splitTokens[1].equals("NNP")) {
                propNounList.add(splitTokens[0]);
            } else {
                addProperNounToSet(propNounSet, propNounList);
            }
        }
        addProperNounToSet(propNounSet, propNounList);
        return propNounSet;
    }

    private static void addProperNounToSet(HashSet<String> propNounSet, List<String> propNounList) {
        if (!propNounList.isEmpty()) {
            propNounSet.add(StringUtils.join(propNounList, " "));
            propNounList.clear();
        }
    }

    public String tagPos(String input) {
        return tagger.tagString(input);
    }

    public static String extractFromUrl(String userUrl) throws
            IOException,
            SAXException,
            BoilerpipeProcessingException {
        final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(userUrl));
        System.out.println("htmlDoc: " + htmlDoc.toString());
        final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
        return CommonExtractors.ARTICLE_EXTRACTOR.getText(doc);
    }
}