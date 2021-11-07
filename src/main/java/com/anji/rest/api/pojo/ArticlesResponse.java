package com.anji.rest.api.pojo;

import java.util.List;

import com.anji.rest.api.asserts.ArticlesResponseAssert;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Articles Response POJO
 *  
 * @author anjiboddupally
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticlesResponse {

    @JsonProperty("articles")
    private List<Article> articles;
    
    @JsonProperty("articlesCount")
    private int articlesCount;
    
    @JsonProperty("errors")
	private ErrorsJson errors;
  
   
    public ArticlesResponseAssert assertThat() {
    	return new ArticlesResponseAssert(this);
    }
}
