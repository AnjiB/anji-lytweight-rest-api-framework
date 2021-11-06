package com.anji.rest.api.pojo;

import java.util.List;

import com.anji.rest.api.asserts.ArticlesResponseAssert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Articles Response POJO
 *  
 * @author boddupally.anji
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticlesResponse {

    @JsonProperty("articles")
    private List<Article> articles = null;
    
    @JsonProperty("articlesCount")
    private int articlesCount;
    
    @JsonProperty("errors")
	private ErrorsJson errors;
  
   
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }
    
    public ErrorsJson getErrors() {
		return errors;
	}

	public void setErrors(ErrorsJson errors) {
		this.errors = errors;
	}
    
    public ArticlesResponseAssert assertThat() {
    	return new ArticlesResponseAssert(this);
    }
}
