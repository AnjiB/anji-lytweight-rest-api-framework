package com.anji.rest.api.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Article POJO
 * 
 * @author boddupally.anji
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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

	@JsonProperty("tagList")
	private List<String> tagList = null;

	@JsonProperty("favorited")
	private boolean favorited;

	@JsonProperty("favoritesCount")
	private int favoritesCount;

	@JsonProperty("author")
	private Author author;

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public Boolean getFavorited() {
		return favorited;
	}

	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}

	public Integer getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(Integer favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public static class Builder {

		private String articleTitle;

		private String articleDescription;

		private String articleBody;

		private List<String> articleTags;

		public Builder withArticleTitle(String artTitle) {
			this.articleTitle = artTitle;
			return this;
		}

		public Builder withArticleBody(String artBody) {
			this.articleBody = artBody;
			return this;
		}

		public Builder withArticleDescription(String articleDescription) {
			this.articleDescription = articleDescription;
			return this;
		}

		public Builder withArticleTags(String... tags) {
			if (tags.length > 0) {
				this.articleTags = Arrays.asList(tags);
			}
			return this;
		}

		public Article build() {
			Article article = new Article();
			article.setTitle(this.articleTitle);
			article.setDescription(this.articleDescription);
			article.setBody(this.articleBody);
			article.setTagList(this.articleTags);
			return article;
		}
	}

}
