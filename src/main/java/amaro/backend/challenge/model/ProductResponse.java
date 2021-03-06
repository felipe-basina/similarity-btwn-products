package amaro.backend.challenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ProductResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-26T01:08:13.280Z")

public class ProductResponse implements Comparable<ProductResponse> {
	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("tags")
	@Valid
	private List<String> tags = null;

	@JsonProperty("tagsVector")
	@Valid
	private List<Integer> tagsVector = null;

	public ProductResponse id(Long id) {
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

	public ProductResponse name(String name) {
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

	public ProductResponse tags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public ProductResponse addTagsItem(String tagsItem) {
		if (this.tags == null) {
			this.tags = new ArrayList<String>();
		}
		this.tags.add(tagsItem);
		return this;
	}

	/**
	 * Get tags
	 * 
	 * @return tags
	 **/
	@ApiModelProperty(example = "[\"neutro\",\"veludo\",\"couro\",\"basics\",\"festa\",\"workwear\",\"inverno\",\"boho\",\"estampas\",\"balada\",\"colorido\",\"casual\",\"liso\",\"moderno\",\"passeio\",\"metal\",\"viagem\",\"delicado\",\"descolado\",\"elastano\"]", value = "")

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public ProductResponse tagsVector(List<Integer> tagsVector) {
		this.tagsVector = tagsVector;
		return this;
	}

	public ProductResponse addTagsVectorItem(Integer tagsVectorItem) {
		if (this.tagsVector == null) {
			this.tagsVector = new ArrayList<Integer>();
		}
		this.tagsVector.add(tagsVectorItem);
		return this;
	}

	/**
	 * Get tagsVector
	 * 
	 * @return tagsVector
	 **/
	@ApiModelProperty(example = "[0,1]", value = "")

	public List<Integer> getTagsVector() {
		return tagsVector;
	}

	public void setTagsVector(List<Integer> tagsVector) {
		this.tagsVector = tagsVector;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ProductResponse productResponse = (ProductResponse) o;
		return Objects.equals(this.id, productResponse.id) && Objects.equals(this.name, productResponse.name)
				&& Objects.equals(this.tags, productResponse.tags)
				&& Objects.equals(this.tagsVector, productResponse.tagsVector);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, tags, tagsVector);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ProductResponse {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    tagsVector: ").append(toIndentedString(tagsVector)).append("\n");
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
	public int compareTo(ProductResponse productResponse) {
		return this.id.compareTo(productResponse.getId());
	}

}
