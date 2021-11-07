package com.anji.rest.api.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * Article POJO
 * 
 * @author anjiboddupally
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class Article {

	@JsonProperty("slug")
	private String slug;

	@JsonProperty("title")
	private String title;

	@JsonProperty("description")
	private String description;

	@JsonProperty("body")
	private String body;

	@JsonProperty("createdAt")
	private String createdAt;

	@JsonProperty("updatedAt")
	private String updatedAt;

	@Singular("tag")
	@JsonProperty("tagList")
	private List<String> tagList;

	@JsonProperty("favorited")
	private boolean favorited;

	@JsonProperty("favoritesCount")
	private int favoritesCount;

	@JsonProperty("author")
	private Author author;

}
