package amaro.backend.challenge.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SimilarProductFinderResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

public class SimilarProductFinderResponse implements Comparable<SimilarProductFinderResponse> {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("similarity")
	private BigDecimal similarity = null;

	public SimilarProductFinderResponse id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * The product ID
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "The product ID")

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimilarProductFinderResponse name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * The product name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "The product name")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SimilarProductFinderResponse similarity(BigDecimal similarity) {
		this.similarity = similarity;
		return this;
	}

	/**
	 * Get similarity
	 * 
	 * @return similarity
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public BigDecimal getSimilarity() {
		return similarity;
	}

	public void setSimilarity(BigDecimal similarity) {
		this.similarity = similarity;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SimilarProductFinderResponse similarProductFinderResponse = (SimilarProductFinderResponse) o;
		return Objects.equals(this.id, similarProductFinderResponse.id)
				&& Objects.equals(this.name, similarProductFinderResponse.name)
				&& Objects.equals(this.similarity, similarProductFinderResponse.similarity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, similarity);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SimilarProductFinderResponse {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    similarity: ").append(toIndentedString(similarity)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
	
	@Override
	public int compareTo(SimilarProductFinderResponse o) {
		return Comparator.comparing(SimilarProductFinderResponse::getSimilarity)
				.thenComparing(SimilarProductFinderResponse::getId)
			.compare(o, this);
	}
	
}
