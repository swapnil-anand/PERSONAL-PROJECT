package com.nse.listingcompliance.lcdebtannouncementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * FileDisplayDetails
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-11-08T14:49:24.151503300+05:30[Asia/Calcutta]")
public class FileDisplayDetails {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("version")
  private BigDecimal version;

  @JsonProperty("linkedToClarification")
  private Boolean linkedToClarification = false;

  @JsonProperty("fileType")
  private String fileType;

  public FileDisplayDetails id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public FileDisplayDetails name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FileDisplayDetails version(BigDecimal version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getVersion() {
    return version;
  }

  public void setVersion(BigDecimal version) {
    this.version = version;
  }

  public FileDisplayDetails linkedToClarification(Boolean linkedToClarification) {
    this.linkedToClarification = linkedToClarification;
    return this;
  }

  /**
   * Get linkedToClarification
   * @return linkedToClarification
  */
  @ApiModelProperty(value = "")


  public Boolean getLinkedToClarification() {
    return linkedToClarification;
  }

  public void setLinkedToClarification(Boolean linkedToClarification) {
    this.linkedToClarification = linkedToClarification;
  }

  public FileDisplayDetails fileType(String fileType) {
    this.fileType = fileType;
    return this;
  }

  /**
   * Get fileType
   * @return fileType
  */
  @ApiModelProperty(value = "")


  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileDisplayDetails fileDisplayDetails = (FileDisplayDetails) o;
    return Objects.equals(this.id, fileDisplayDetails.id) &&
        Objects.equals(this.name, fileDisplayDetails.name) &&
        Objects.equals(this.version, fileDisplayDetails.version) &&
        Objects.equals(this.linkedToClarification, fileDisplayDetails.linkedToClarification) &&
        Objects.equals(this.fileType, fileDisplayDetails.fileType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, version, linkedToClarification, fileType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileDisplayDetails {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    linkedToClarification: ").append(toIndentedString(linkedToClarification)).append("\n");
    sb.append("    fileType: ").append(toIndentedString(fileType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

