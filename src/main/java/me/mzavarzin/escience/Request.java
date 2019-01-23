package me.mzavarzin.escience;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Request  {
  
  @Schema(description = "The uuid of the experiment request")
 /**
   * The uuid of the experiment request  
  **/
  private UUID id = null;
  
  @Schema(description = "The uuid of the experiment request")
 /**
   * The uuid of the experiment request  
  **/
  private String name = null;
  
  @Schema(description = "UUID of the initiator of the request")
 /**
   * UUID of the initiator of the request  
  **/
  private UUID userId = null;
  
  @Schema(description = "UUID of the Container associated")
 /**
   * UUID of the Container associated  
  **/
  private UUID containerId = null;
  
  @Schema(description = "UUID of the Dataset associated")
 /**
   * UUID of the Dataset associated  
  **/
  private UUID datasetId = null;
  
  @Schema(description = "UUID of the Container associated")
 /**
   * UUID of the Container associated  
  **/
  private UUID jobId = null;
  
  @Schema(description = "UUID of the Result associated")
 /**
   * UUID of the Result associated  
  **/
  private UUID resultId = null;
  public enum StatusEnum {
    CREATED("created"),
    RUNNING("running"),
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }
    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }  
  @Schema(description = "The status of the experiment request")
 /**
   * The status of the experiment request  
  **/
  private StatusEnum status = null;
 /**
   * The uuid of the experiment request
   * @return id
  **/
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Request id(UUID id) {
    this.id = id;
    return this;
  }

 /**
   * The uuid of the experiment request
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Request name(String name) {
    this.name = name;
    return this;
  }

 /**
   * UUID of the initiator of the request
   * @return userId
  **/
  @JsonProperty("userId")
  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Request userId(UUID userId) {
    this.userId = userId;
    return this;
  }

 /**
   * UUID of the Container associated
   * @return containerId
  **/
  @JsonProperty("containerId")
  public UUID getContainerId() {
    return containerId;
  }

  public void setContainerId(UUID containerId) {
    this.containerId = containerId;
  }

  public Request containerId(UUID containerId) {
    this.containerId = containerId;
    return this;
  }

 /**
   * UUID of the Dataset associated
   * @return datasetId
  **/
  @JsonProperty("datasetId")
  public UUID getDatasetId() {
    return datasetId;
  }

  public void setDatasetId(UUID datasetId) {
    this.datasetId = datasetId;
  }

  public Request datasetId(UUID datasetId) {
    this.datasetId = datasetId;
    return this;
  }

 /**
   * UUID of the Container associated
   * @return jobId
  **/
  @JsonProperty("jobId")
  public UUID getJobId() {
    return jobId;
  }

  public void setJobId(UUID jobId) {
    this.jobId = jobId;
  }

  public Request jobId(UUID jobId) {
    this.jobId = jobId;
    return this;
  }

 /**
   * UUID of the Result associated
   * @return resultId
  **/
  @JsonProperty("resultId")
  public UUID getResultId() {
    return resultId;
  }

  public void setResultId(UUID resultId) {
    this.resultId = resultId;
  }

  public Request resultId(UUID resultId) {
    this.resultId = resultId;
    return this;
  }

 /**
   * The status of the experiment request
   * @return status
  **/
  @JsonProperty("status")
  public String getStatus() {
    if (status == null) {
      return null;
    }
    return status.getValue();
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public Request status(StatusEnum status) {
    this.status = status;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Request {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    containerId: ").append(toIndentedString(containerId)).append("\n");
    sb.append("    datasetId: ").append(toIndentedString(datasetId)).append("\n");
    sb.append("    jobId: ").append(toIndentedString(jobId)).append("\n");
    sb.append("    resultId: ").append(toIndentedString(resultId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
