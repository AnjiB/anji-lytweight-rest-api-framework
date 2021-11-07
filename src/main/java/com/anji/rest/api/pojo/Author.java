package com.anji.rest.api.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Author POJO
 *   
 * @author anjiboddupally
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {

    @JsonProperty("username")
    private String username;
    
    @JsonProperty("image")
    private String image;
    
    @JsonProperty("following")
    private boolean following;
    
    @JsonProperty("bio")
    private String bio;
 
}
